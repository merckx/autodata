package yanislav.com.autodata.api;

import android.util.Base64;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yanislav.com.autodata.events.BrandLoadedEvent;
import yanislav.com.autodata.events.CarDetailsLoadedEvent;
import yanislav.com.autodata.events.CarListLoadedEvent;
import yanislav.com.autodata.events.ImagesInfoDataLoadedEvent;
import yanislav.com.autodata.events.ModelsLoadedEvent;
import yanislav.com.autodata.events.SubModelsLoadedEvent;
import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.model.CarListData;
import yanislav.com.autodata.model.CarListInfoData;
import yanislav.com.autodata.model.DetailsData;
import yanislav.com.autodata.model.DetailsInfoData;
import yanislav.com.autodata.model.ImageHolder;
import yanislav.com.autodata.model.ImagesData;
import yanislav.com.autodata.model.ImagesInfoData;
import yanislav.com.autodata.model.Model;
import yanislav.com.autodata.model.Submodel;
import yanislav.com.autodata.api.deserializers.CarListDataDeserializer;
import yanislav.com.autodata.api.deserializers.DetailsDataDeserializer;
import yanislav.com.autodata.api.deserializers.ImagesDataDeserializer;

/**
 * Created by yani on 20.2.2017 г..
 */

public class Api {

    public static final String BASE_URL = "http://www.auto-data.net";
    public static final String BASE_SERVICE_ENDPOINT = BASE_URL + "/app/";

    private static final Api INSTANCE = new Api();
    private static AutoDataService service;

    public static Api getInstance()
    {
        return INSTANCE;
    }

    private Api()
    {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder();
        okHttpBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        okHttpBuilder.addInterceptor(new Interceptor()
        {
            public Response intercept(Interceptor.Chain chain)
                    throws IOException
            {
                Response resp = chain.proceed(chain.request());
                String body = resp.body().string();
                body = new String(Base64.decode(body.substring(17, body.length()), 0));
                body = new String(Base64.decode(body.substring(14, body.length()), 0));
                ResponseBody newBody = ResponseBody.create(MediaType.parse("application/json; charset=UTF-8"), body);
                return resp.newBuilder().body(newBody).build();
            }
        });

        GsonBuilder localGsonBuilder = new GsonBuilder();
        localGsonBuilder.registerTypeAdapter(DetailsData.class, new DetailsDataDeserializer());
        localGsonBuilder.registerTypeAdapter(CarListData.class, new CarListDataDeserializer());
        localGsonBuilder.registerTypeAdapter(ImagesData.class, new ImagesDataDeserializer());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(localGsonBuilder.create()))
                .client(okHttpBuilder.build())

                .build();
        service = retrofit.create(AutoDataService.class);
    }

    public void loadBrands()
    {
        service.getBrands("en")
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<Brand>>>()
                {
                    @Override
                    public ObservableSource<? extends List<Brand>> apply(Throwable throwable) throws Exception
                    {
                        return null;
                    }
                })
               .subscribe(new DisposableObserver<List<Brand>>()
                {
                    @Override
                    public void onNext(List<Brand> brands)
                    {
                        Log.i("NEXT", brands.get(0).getName());
                        EventBus.getDefault().post(new BrandLoadedEvent(brands));
                    }

                    @Override
                    public void onError(Throwable throwable)
                    {
                        Log.e("BRANDS", "ERROR", throwable);
                    }

                    @Override
                    public void onComplete()
                    {
                        Log.i("COMPLETE", "ASD");

                    }
                });
    }

    public void loadModels(final Brand brand) {
        service.getModels(brand.getId(), "en").enqueue(
                new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, retrofit2.Response<List<Model>> response) {
                        List<Model> modelList = response.body();
                        for(Model model : modelList)
                        {
                            model.setBrand(brand.getName());
                        }
                        EventBus.getDefault().post(new ModelsLoadedEvent(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {

                    }
                }
        );
    }


    public void loadSubModels(final Model model)
    {
        service.getSubModels(model.getId(), "en").enqueue(new Callback<List<Submodel>>() {
            @Override
            public void onResponse(Call<List<Submodel>> call, retrofit2.Response<List<Submodel>> response) {
                List<Submodel> submodelList = response.body();
                for(Submodel submodel : submodelList)
                {
                    submodel.setBrand(model.getBrand());
                }
                EventBus.getDefault().post(new SubModelsLoadedEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<Submodel>> call, Throwable t) {

            }
        });
    }


    public void loadCarList(final Submodel subModel)
    {
        service.getListCars(subModel.getId(), "en").enqueue(new Callback<CarListData>() {
            @Override
            public void onResponse(Call<CarListData> call, retrofit2.Response<CarListData> response) {
                List<CarListInfoData> result = new ArrayList<CarListInfoData>();
                int i = 0;
                CarListInfoData carListInfoData;
                while (i < response.body().getData().keySet().size() - 1)
                {
                    carListInfoData = new CarListInfoData();
                    if (((Map)response.body().getData().get("" + i)).get("v1") != null)
                    {
                        carListInfoData.setName((response.body().getData().get("" + i)).get("v1"));
                        carListInfoData.setYears((response.body().getData().get("" + i)).get("v2"));
                        carListInfoData.setBrand(subModel.getBrand());
                        carListInfoData.setModel(subModel.getModel());
                        carListInfoData.setId(Integer.parseInt((response.body().getData().get("" + i)).get("id")));
                        result.add(carListInfoData);
                    }
                    i += 1;
                }

                Map<String, String> images = response.body().getData().get("im");
                List<ImageHolder> imagesList = new ArrayList<>();
                if (null != images)
                {
                    String imageId;
                    Iterator iterator = images.keySet().iterator();
                    while (iterator.hasNext())
                    {
                        imageId = (String)iterator.next();
                        ImageHolder localImageHolder = new ImageHolder();
                        localImageHolder.setId(Integer.parseInt(imageId));
                        localImageHolder.setUrl(images.get(imageId));
                        imagesList.add(localImageHolder);
                    }
                }
                Log.i("QUEUE RESPONSE", "onResponse: " + result.size() + " " + imagesList.size());
                EventBus.getDefault().post(new CarListLoadedEvent(imagesList, result));
            }


            @Override
            public void onFailure(Call<CarListData> call, Throwable t) {
                Log.e("QUE ERR", "onFailure: ", t);
            }
        });
    }

    public void loadCarDetails(CarListInfoData carListInfoData)
    {
        service.getDetails(carListInfoData.getId(), "en").enqueue(new Callback<DetailsData>() {
            @Override
            public void onResponse(Call<DetailsData> call, retrofit2.Response<DetailsData> response) {
                List<DetailsInfoData> result  = new ArrayList();
                int i = 0;
                while (i < response.body().getData().keySet().size() - 1)
                {
                    DetailsInfoData detailsInfoData = new DetailsInfoData();
                    detailsInfoData.setKey((response.body().getData().get("" + i)).get("p"));
                    detailsInfoData.setValue((response.body().getData().get("" + i)).get("v"));
                    result.add(detailsInfoData);
                    i += 1;
                }

                Map<String, String> images = response.body().getData().get("im");
                List<ImageHolder> imageHolderList = new ArrayList<ImageHolder>();
                if (images != null)
                {
                    Iterator iterator = images.keySet().iterator();
                    while (iterator.hasNext())
                    {
                        String imageId = (String)iterator.next();
                        ImageHolder localImageHolder = new ImageHolder();
                        localImageHolder.setId(Integer.parseInt(imageId));
                        localImageHolder.setUrl(images.get(imageId));
                        imageHolderList.add(localImageHolder);
                    }
                }
                EventBus.getDefault().post(new CarDetailsLoadedEvent(imageHolderList, result));
            }

            @Override
            public void onFailure(Call<DetailsData> call, Throwable t) {

            }
        });

    }

    public void loadImages(final ImageHolder imageHolder)
    {
        service.getImages(imageHolder.getId(), "en").enqueue(new Callback<ImagesData>() {
            @Override
            public void onResponse(Call<ImagesData> call, retrofit2.Response<ImagesData> response) {
                ImagesData imagesData = response.body();
                List<ImagesInfoData> result =  new ArrayList<ImagesInfoData>();
                Iterator<String> iterator = imagesData.getData().keySet().iterator();
                while(iterator.hasNext())
                {
                    String key = iterator.next();
                    Map<String, String> localMap = imagesData.getData().get(key);
                    ImagesInfoData localImagesInfoData = new ImagesInfoData();
                    localImagesInfoData.setBig(localMap.get("b"));
                    localImagesInfoData.setCopyRight(localMap.get("c"));
                    localImagesInfoData.setSmall(localMap.get("s"));

                    result.add(localImagesInfoData);
                }

                EventBus.getDefault().post(new ImagesInfoDataLoadedEvent(result));
            }

            @Override
            public void onFailure(Call<ImagesData> call, Throwable t) {
                Log.e("QUE ERR", "onFailure: ", t);
            }
        });
    }
}

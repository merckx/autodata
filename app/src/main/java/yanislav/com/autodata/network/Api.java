package yanislav.com.autodata.network;

import android.util.Base64;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
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
import yanislav.com.autodata.network.deserializers.CarListDataDeserializer;
import yanislav.com.autodata.network.deserializers.DetailsDataDeserializer;
import yanislav.com.autodata.network.deserializers.ImagesDataDeserializer;

/**
 * Created by yani on 20.2.2017 г..
 */

public class Api {

    public static final String BASE_URL = "http://www.auto-data.net";
    public static final String BASE_SERVICE_ENDPOINT = BASE_URL + "/app/";

    private static final Api INSTANCE = new Api();
    private static AutodataService service;

    public static Api getInstance()
    {
        return INSTANCE;
    }

    private Api()
    {
        OkHttpClient.Builder paramContext = new OkHttpClient().newBuilder();
        paramContext.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        paramContext.addInterceptor(new Interceptor()
        {
            public Response intercept(Interceptor.Chain paramAnonymousChain)
                    throws IOException
            {
                Response resp = paramAnonymousChain.proceed(paramAnonymousChain.request());
                Object localObject = resp.body().string();
                localObject = new String(Base64.decode(((String)localObject).substring(17, ((String)localObject).length()), 0));
                localObject = new String(Base64.decode(((String)localObject).substring(14, ((String)localObject).length()), 0));
                localObject = ResponseBody.create(MediaType.parse("application/json; charset=UTF-8"), (String)localObject);
                return resp.newBuilder().body((ResponseBody)localObject).build();
            }
        });
        GsonBuilder localGsonBuilder = new GsonBuilder();
        localGsonBuilder.registerTypeAdapter(DetailsData.class, new DetailsDataDeserializer());
        localGsonBuilder.registerTypeAdapter(CarListData.class, new CarListDataDeserializer());
        localGsonBuilder.registerTypeAdapter(ImagesData.class, new ImagesDataDeserializer());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(localGsonBuilder.create())).client(paramContext.build()).build();
        service = ((AutodataService)retrofit.create(AutodataService.class));
    }

    public void loadBrands()
    {
         service.getBrands("en").enqueue(
                 new Callback<List<Brand>>() {
                     @Override
                     public void onResponse(Call<List<Brand>> call, retrofit2.Response<List<Brand>> response) {
                         EventBus.getDefault().post(new BrandLoadedEvent(response.body()));
                     }

                     @Override
                     public void onFailure(Call<List<Brand>> call, Throwable t) {

                     }
                 }
         );
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
                CarListInfoData localObject;
                while (i < response.body().getData().keySet().size() - 1)
                {
                    localObject = new CarListInfoData();
                    if (((Map)response.body().getData().get("" + i)).get("v1") != null)
                    {
                        localObject.setName((String)((Map)response.body().getData().get("" + i)).get("v1"));
                        localObject.setYears((String)((Map)response.body().getData().get("" + i)).get("v2"));
                        localObject.setBrand(subModel.getBrand());
                        localObject.setModel(subModel.getModel());
                        localObject.setId(Integer.parseInt((String)((Map)response.body().getData().get("" + i)).get("id")));
                        result.add(localObject);
                    }
                    if (i == 0)
                    {
//                        ListCarsFragment.this.firstText.setText((CharSequence)((Map)response.body().getData().get("" + i)).get("p1"));
//                        ListCarsFragment.this.yearsText.setText((CharSequence)((Map)response.body().getData().get("" + i)).get("p2"));
                    }
                    i += 1;
                }

                Map images = (Map)response.body().getData().get("im");
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
                        localImageHolder.setUrl((String)images.get(imageId));
                        imagesList.add(localImageHolder);
//                        ListCarsFragment.this.caroselAdapter.getImages().add(localImageHolder);
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
//                DetailsFragment.this.adapter.getDetailsInfoDatas().clear();
                List<DetailsInfoData> result  = new ArrayList();
                int i = 0;
//                Object localObject;
                while (i < response.body().getData().keySet().size() - 1)
                {
                    DetailsInfoData detailsInfoData = new DetailsInfoData();
                    detailsInfoData.setKey((response.body().getData().get("" + i)).get("p"));
                    detailsInfoData.setValue((response.body().getData().get("" + i)).get("v"));
                    if (i == 0)
                    {
//                        DetailsFragment.this.title.setText(Html.fromHtml((String)((Map)response.body().getData().get("" + i)).get("t")));
//                        DetailsFragment.this.getMainActivity().getSupportActionBar().setSubtitle(DetailsFragment.this.title.getText());
                    }
                    result.add(detailsInfoData);
                    i += 1;
                }
//                DetailsFragment.this.adapter.getDetailsInfoDatas().addAll(paramAnonymousCall);
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
//                        DetailsFragment.this.caroselAdapter.getImages().add(localImageHolder);
                    }
                }
                EventBus.getDefault().post(new CarDetailsLoadedEvent(imageHolderList, result));
//                DetailsFragment.this.caroselAdapter.notifyDataSetChanged();
//                DetailsFragment.this.adapter.notifyDataSetChanged();
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
//                    if (str.equals(FullscreenImageFragment.this.id + "")) {
//                        paramAnonymousCall = localImagesInfoData;
//                    }
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

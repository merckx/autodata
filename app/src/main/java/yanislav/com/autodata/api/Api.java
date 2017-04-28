package yanislav.com.autodata.api;

import android.util.Base64;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import network.NetworkUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yanislav.com.autodata.AutoDataApp;
import yanislav.com.autodata.api.deserializers.CarListDataDeserializer;
import yanislav.com.autodata.api.deserializers.DetailsDataDeserializer;
import yanislav.com.autodata.api.deserializers.ImagesDataDeserializer;
import yanislav.com.autodata.events.BrandLoadedEvent;
import yanislav.com.autodata.events.CarDetailsLoadedEvent;
import yanislav.com.autodata.events.CarListLoadedEvent;
import yanislav.com.autodata.events.ImagesInfoDataLoadedEvent;
import yanislav.com.autodata.events.ModelsLoadedEvent;
import yanislav.com.autodata.events.SubModelsLoadedEvent;
import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.model.CarListData;
import yanislav.com.autodata.model.CarListInfoData;
import yanislav.com.autodata.model.CarListInfoHolder;
import yanislav.com.autodata.model.DetailsData;
import yanislav.com.autodata.model.DetailsInfoData;
import yanislav.com.autodata.model.DetailsInfoHolder;
import yanislav.com.autodata.model.ImageHolder;
import yanislav.com.autodata.model.ImagesData;
import yanislav.com.autodata.model.ImagesInfoData;
import yanislav.com.autodata.model.Model;
import yanislav.com.autodata.model.Submodel;
import yanislav.com.autodata.utils.NoNetworkConnectionException;

/**
 * Created by yani on 20.2.2017 г..
 */

public class Api
{

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
        okHttpBuilder.addInterceptor(chain ->
        {
            Response resp = chain.proceed(chain.request());
            String body = resp.body().string();
            body = new String(Base64.decode(body.substring(17, body.length()), 0));
            body = new String(Base64.decode(body.substring(14, body.length()), 0));
            ResponseBody newBody = ResponseBody.create(MediaType.parse("application/json; charset=UTF-8"), body);
            return resp.newBuilder().body(newBody).build();
        });

        okHttpBuilder.addInterceptor(chain ->
        {
            if (!NetworkUtil.isNetworkConnected(AutoDataApp.getINSTANCE().getContext()))
            {
                throw new NoNetworkConnectionException();
            }
            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        });

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DetailsData.class, new DetailsDataDeserializer());
        gsonBuilder.registerTypeAdapter(CarListData.class, new CarListDataDeserializer());
        gsonBuilder.registerTypeAdapter(ImagesData.class, new ImagesDataDeserializer());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .client(okHttpBuilder.build())
                .build();

        service = retrofit.create(AutoDataService.class);
    }


    public void loadBrands()
    {
        service.getBrands("en")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Brand>>()
                {
                    @Override
                    public void onNext(List<Brand> brands)
                    {
                        Log.i("NEXT", brands.get(0).getName());
                        Log.i("THREADRX", Thread.currentThread().getName());
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

    public void loadModels(final Brand brand)
    {
        service.getModels(brand.getId(), "en")
                .subscribeOn(Schedulers.io())
                .map(models ->
                {
                    for (Model model : models)
                    {
                        model.setBrand(brand.getName());
                    }
                    return models;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Model>>()
                {
                    @Override
                    public void onNext(List<Model> models)
                    {
                        Log.i("NEXT", models.get(0).getName());
                        Log.i("THREADRX", Thread.currentThread().getName());
//                    EventBus.getDefault().post(new BrandLoadedEvent(brands));
                        EventBus.getDefault().post(new ModelsLoadedEvent(models));
                    }

                    @Override
                    public void onError(Throwable throwable)
                    {
                        Log.e("BRANDS", "ERROR", throwable);
                        //handleError
                    }

                    @Override
                    public void onComplete()
                    {
                        Log.i("COMPLETE", "ASD");

                    }
                });
    }


    public void loadSubModels(final Model model)
    {
        service.getSubModels(model.getId(), "en")
                .subscribeOn(Schedulers.io())
                .map(submodels ->
                {
                    for (Submodel submodel : submodels)
                    {
                        submodel.setBrand(model.getBrand());
                    }
                    return submodels;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Submodel>>()
                {
                    @Override
                    public void onNext(List<Submodel> submodels)
                    {
//                        Log.i("NEXT", submodels.get(0).getName());
//                        Log.i("THREADRX", Thread.currentThread().getName());
//                    EventBus.getDefault().post(new BrandLoadedEvent(brands));
                        EventBus.getDefault().post(new SubModelsLoadedEvent(submodels));
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


    public void loadCarList(final Submodel subModel)
    {
        service.getListCars(subModel.getId(), "en")
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .map(carListData ->
                {
                    Observable<List<CarListInfoData>> carListInfoDataObservable = Observable.fromIterable(carListData.getData().entrySet())
                            .subscribeOn(Schedulers.computation())
                            .filter(entry -> !entry.getKey().equals("im"))
                            .map(entry ->
                            {
                                CarListInfoData carListInfoData = new CarListInfoData();
                                carListInfoData.setName(entry.getValue().get("v1"));
                                carListInfoData.setYears(entry.getValue().get("v2"));
                                carListInfoData.setBrand(subModel.getBrand());
                                carListInfoData.setModel(subModel.getModel());
                                carListInfoData.setId(Integer.parseInt(entry.getValue().get("id")));
                                Log.i("carlistinfodata " + carListInfoData.getName(), Thread.currentThread().getName());
                                return carListInfoData;
                            })
                            .toList().toObservable();

                    Map<String, String> images = carListData.getData().get("im");
                    if (null == images)
                    {
                        images = new HashMap<>();
                    }

                    Observable<List<ImageHolder>> imageListObservable = Observable.fromIterable(images.entrySet())
                            .subscribeOn(Schedulers.computation())
                            .map(entry ->
                            {
                                ImageHolder localImageHolder = new ImageHolder();
                                localImageHolder.setId(Integer.parseInt(entry.getKey()));
                                localImageHolder.setUrl(entry.getValue());
                                Log.i("imageholder " + localImageHolder.getUrl(), Thread.currentThread().getName());
                                return localImageHolder;
                            })
                            .toList().toObservable();

                    return Observable.zip(imageListObservable, carListInfoDataObservable,
                            (imageHolders, carListInfoData) -> new CarListInfoHolder(carListInfoData, imageHolders))
                            .blockingFirst();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CarListInfoHolder>()
                {
                    @Override
                    public void onNext(CarListInfoHolder carListInfoHolder)
                    {
                        EventBus.getDefault().post(new CarListLoadedEvent(carListInfoHolder.getImageHolderList(),
                                carListInfoHolder.getCarListInfoDataList()));
                    }

                    @Override
                    public void onError(Throwable throwable)
                    {

                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });
    }


    public void loadCarDetails(CarListInfoData carListInfoData)
    {
        service.getDetails(carListInfoData.getId(), "en")
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .map(detailsData ->
                {
                    Observable<List<DetailsInfoData>> detailsInfoDataObservable = Observable.fromIterable(detailsData.getData().entrySet())
                            .subscribeOn(Schedulers.computation())
                            .filter(entry -> !entry.getKey().equals("im"))
                            .map(entry ->
                            {
                                DetailsInfoData detailsInfoData = new DetailsInfoData();
                                detailsInfoData.setKey(entry.getValue().get("p"));
                                detailsInfoData.setValue(entry.getValue().get("v"));
                                detailsInfoData.setCarListInfoDataId(carListInfoData.getId());
                                Log.i("detailsInfoData " + detailsInfoData.getKey(), Thread.currentThread().getName());

                                return detailsInfoData;
                            }).toList().toObservable();

                    Map<String, String> images = detailsData.getData().get("im");
                    if (null == images)
                    {
                        images = new HashMap<>();
                    }

                    Observable<List<ImageHolder>> imageHolderObservable = Observable.fromIterable(images.entrySet())
                            .subscribeOn(Schedulers.computation())
                            .map(entry ->
                            {
                                ImageHolder localImageHolder = new ImageHolder();
                                localImageHolder.setId(Integer.parseInt(entry.getKey()));
                                localImageHolder.setUrl(entry.getValue());
                                Log.i("imageholdercarlist " + localImageHolder.getUrl(), Thread.currentThread().getName());

                                return localImageHolder;
                            }).toList().toObservable();

                    return Observable.zip(imageHolderObservable, detailsInfoDataObservable,
                            (imageHolders, detailsInfoData) -> new DetailsInfoHolder(detailsInfoData, imageHolders)).blockingFirst();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DetailsInfoHolder>()
                {
                    @Override
                    public void onNext(DetailsInfoHolder holder)
                    {
                        EventBus.getDefault()
                                .post(new CarDetailsLoadedEvent(holder.getImageHolderList(),
                                        holder.getDetailsInfoDataList()));
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

    public void loadImages(final ImageHolder imageHolder)
    {
        service.getImages(imageHolder.getId(), "en")
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .map(imagesData ->
                {
                    List<ImagesInfoData> result = Observable.fromIterable(imagesData.getData().entrySet())
                            .subscribeOn(Schedulers.computation())
                            .map(entry ->
                            {
                                ImagesInfoData localImagesInfoData = new ImagesInfoData();
                                localImagesInfoData.setBig(entry.getValue().get("b"));
                                localImagesInfoData.setCopyRight(entry.getValue().get("c"));
                                localImagesInfoData.setSmall(entry.getValue().get("s"));
                                return localImagesInfoData;
                            })
                            .toList().blockingGet();

                    return result;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<ImagesInfoData>>()
                {
                    @Override
                    public void onNext(List<ImagesInfoData> imagesInfoData)
                    {
                        EventBus.getDefault().post(new ImagesInfoDataLoadedEvent(imagesInfoData));
                    }

                    @Override
                    public void onError(Throwable throwable)
                    {

                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });

    }
}

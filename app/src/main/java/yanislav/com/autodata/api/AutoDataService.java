package yanislav.com.autodata.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.model.CarListData;
import yanislav.com.autodata.model.DetailsData;
import yanislav.com.autodata.model.ImagesData;
import yanislav.com.autodata.model.Model;
import yanislav.com.autodata.model.Submodel;

/**
 * Created by yani on 19.2.2017 г..
 */

public interface AutoDataService
{
    @GET("brands.php")
    Observable<List<Brand>> getBrands(@Query("lang") String paramString);

    @FormUrlEncoded
    @POST("showcar.php")
    Observable<DetailsData> getDetails(@Field("id") long paramInt, @Field("lang") String paramString);

    @GET("imgs.php")
    Observable<ImagesData> getImages(@Query("id") long paramInt, @Query("lang") String paramString);

    @FormUrlEncoded
    @POST("listcars.php")
    Observable<CarListData> getListCars(@Field("id") long subModelId, @Field("lang") String paramString);

    @GET("models.php")
    Observable<List<Model>> getModels(@Query("id") long paramInt, @Query("lang") String paramString);

    @FormUrlEncoded
    @POST("submodels.php")
    Observable<List<Submodel>> getSubModels(@Field("id") long paramInt, @Field("lang") String paramString);
}

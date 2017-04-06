package yanislav.com.autodata.network;

import java.util.List;

import retrofit2.Call;
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

public interface AutodataService {
    @GET("brands.php")
    public Call<List<Brand>> getBrands(@Query("lang") String paramString);

    @FormUrlEncoded
    @POST("showcar.php")
    public abstract Call<DetailsData> getDetails(@Field("id") int paramInt, @Field("lang") String paramString);

    @GET("imgs.php")
    public abstract Call<ImagesData> getImages(@Query("id") int paramInt, @Query("lang") String paramString);

    @FormUrlEncoded
    @POST("listcars.php")
    public abstract Call<CarListData> getListCars(@Field("id") int subModelId, @Field("lang") String paramString);
//
//    @GET("menu.php")
//    public abstract Call<LanguageData> getMenu(@Query("lang") String paramString);
//
    @GET("models.php")
    public abstract Call<List<Model>> getModels(@Query("id") int paramInt, @Query("lang") String paramString);

    @FormUrlEncoded
    @POST("submodels.php")
    public abstract Call<List<Submodel>> getSubModels(@Field("id") int paramInt, @Field("lang") String paramString);
}

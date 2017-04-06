package yanislav.com.autodata.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import yanislav.com.autodata.R;
import yanislav.com.autodata.network.Api;

/**
 * Created by yani on 20.2.2017 г..
 */

public class ImageUtil
{
    public static String generateImageUrl(String paramString)
    {
        paramString = replaceWithUnderscore(paramString);
        return Api.BASE_URL.concat("/img/logos2/").concat(paramString).concat(".png");
    }

    public static String generateImageUrlModel(String paramString)
    {
        return Api.BASE_URL.concat("/images/").concat(paramString);
    }

    public static String generateImageUrlSmall(String paramString)
    {
        paramString = replaceWithUnderscore(paramString);
        return Api.BASE_URL.concat("/img/logos/").concat(paramString).concat(".png");
    }

    private static String replaceWithUnderscore(String input)
    {
        return input.replace(" ", "_");
    }

    public static void loadCarImage(ImageView imageView, String modelName, Context context)
    {
        Glide.with(context).load(ImageUtil.generateImageUrlModel(modelName)).error(R.drawable.no).fitCenter().into(imageView);
    }
}

package yanislav.com.autodata.network.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import yanislav.com.autodata.model.ImagesData;

/**
 * Created by yani on 12.3.2017 г..
 */

public class ImagesDataDeserializer
        implements JsonDeserializer<ImagesData>
{
    public ImagesData deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
            throws JsonParseException
    {
        paramType = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
        ImagesData localImagesData = new ImagesData();
        localImagesData.setData((Map)paramJsonDeserializationContext.deserialize(paramJsonElement, paramType));
        return localImagesData;
    }
}

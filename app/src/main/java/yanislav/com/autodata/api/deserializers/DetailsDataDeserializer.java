package yanislav.com.autodata.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import yanislav.com.autodata.model.DetailsData;

/**
 * Created by yani on 15.3.2017 г..
 */

public class DetailsDataDeserializer
        implements JsonDeserializer<DetailsData>
{
    public DetailsData deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
            throws JsonParseException
    {
        paramType = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
        DetailsData localDetailsData = new DetailsData();
        localDetailsData.setData((Map<String, Map<String,String>>) paramJsonDeserializationContext.deserialize(paramJsonElement, paramType));
        return localDetailsData;
    }
}


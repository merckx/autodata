package yanislav.com.autodata.network.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import yanislav.com.autodata.model.CarListData;

/**
 * Created by yani on 12.3.2017 г..
 */

public class CarListDataDeserializer
        implements JsonDeserializer<CarListData> {
    public CarListData deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
            throws JsonParseException {
        paramType = new TypeToken<Map<String, Map<String, String>>>() {
        }.getType();
        CarListData localCarListData = new CarListData();
        localCarListData.setData((Map<String, Map<String,String>>) paramJsonDeserializationContext.deserialize(paramJsonElement, paramType));
        return localCarListData;
    }
}

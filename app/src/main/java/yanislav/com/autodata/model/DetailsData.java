package yanislav.com.autodata.model;

import java.util.Map;

/**
 * Created by yani on 15.3.2017 г..
 */

public class DetailsData
{
    Map<String, Map<String, String>> data;

    public Map<String, Map<String, String>> getData()
    {
        return this.data;
    }

    public void setData(Map<String, Map<String, String>> paramMap)
    {
        this.data = paramMap;
    }
}


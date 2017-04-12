package yanislav.com.autodata.model;

import android.app.SearchableInfo;

import yanislav.com.autodata.utils.ISearchable;

/**
 * Created by yani on 15.3.2017 г..
 */


public class DetailsInfoData extends BaseAutodataModelEntity
{
    private String key;
    private String value;

    public String getKey()
    {
        return this.key;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setKey(String paramString)
    {
        this.key = paramString;
    }

    public void setValue(String paramString)
    {
        this.value = paramString;
    }

}


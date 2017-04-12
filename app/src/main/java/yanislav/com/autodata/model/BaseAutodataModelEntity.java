package yanislav.com.autodata.model;


import yanislav.com.autodata.utils.ISearchable;

/**
 * Created by yanislav on 4/7/17.
 */

public abstract class BaseAutodataModelEntity implements ISearchable
{

    @Override
    public boolean contains(String constraint)
    {
        return false;
    }
}

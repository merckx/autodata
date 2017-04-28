package yanislav.com.autodata.model;


import android.support.v7.util.DiffUtil;

import yanislav.com.autodata.utils.ISearchable;
import yanislav.com.autodata.utils.IVisitor;

/**
 * Created by yanislav on 4/7/17.
 */

public abstract class BaseAutodataModelEntity implements ISearchable
{
    public abstract long getId();
    @Override
    public boolean contains(String constraint)
    {
        return false;
    }

    public abstract boolean areContentsTheSame(BaseAutodataModelEntity baseAutodataModelEntity);

    public abstract <T> T accept(IVisitor<T> visitor);
}

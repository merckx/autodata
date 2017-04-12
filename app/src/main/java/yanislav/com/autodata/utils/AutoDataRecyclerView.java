package yanislav.com.autodata.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by yanislav on 4/6/17.
 */

public class AutoDataRecyclerView extends RecyclerView
{


    public AutoDataRecyclerView(Context context)
    {
        super(context);
    }

    public AutoDataRecyclerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public AutoDataRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public AutoDataAdapter getAdapter()
    {
        return (AutoDataAdapter) super.getAdapter();
    }


}

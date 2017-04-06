package yanislav.com.autodata.adapters;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.viewholders.SubmodelViewHolder;
import yanislav.com.autodata.events.SubModelItemSelectedEvent;
import yanislav.com.autodata.model.Submodel;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 10.3.2017 г..
 */

public class SubModelAdapter extends AutoDataAdapter<Submodel, SubmodelViewHolder> {


    public SubModelAdapter(List<Submodel> content, Context context) {
        super( SubmodelViewHolder.class, content, context);
    }

    @Override
    protected void onItemClicked(Submodel bean)
    {
        EventBus.getDefault().post(new SubModelItemSelectedEvent(bean));
    }

    @Override
    protected int getLayoutItemId() {
        return R.layout.item_submodel;
    }
}

package yanislav.com.autodata.adapters;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.viewholders.ModelViewHolder;
import yanislav.com.autodata.events.ModelSelectedEvent;
import yanislav.com.autodata.model.Model;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 21.2.2017 г..
 */

public class ModelAdapter extends AutoDataAdapter<Model, ModelViewHolder> {

    public ModelAdapter(List<Model> content, Context context) {
        super(ModelViewHolder.class, content, context);

    }


    //    @Override
//    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_model, parent, false);
//        ModelViewHolder vh = new ModelViewHolder(v);
//        return vh;
//    }


    @Override
    public void onBindViewHolder(ModelViewHolder holder, final int position) {
        holder.bind(getContentAt(position), context);
//        holder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new ModelSelectedEvent(getContentAt(position)));
//            }
//        });
    }

    @Override
    protected void onItemClicked(Model bean) {
        EventBus.getDefault().post(new ModelSelectedEvent(bean));
    }

    @Override
    protected int getLayoutItemId() {
        return R.layout.item_model;
    }
}

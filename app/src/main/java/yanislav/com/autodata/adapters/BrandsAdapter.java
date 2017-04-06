package yanislav.com.autodata.adapters;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.viewholders.BrandsViewHolder;
import yanislav.com.autodata.events.BrandSelectedEvent;
import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 20.2.2017 г..
 */

public class BrandsAdapter extends AutoDataAdapter<Brand, BrandsViewHolder> {

    public BrandsAdapter(List<Brand> content, Context context) {
        super(BrandsViewHolder.class, content, context);
    }

//    @Override
//    public BrandsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_brand, parent, false);
//        BrandsViewHolder vh = new BrandsViewHolder(v);
//        return vh;
//    }

    @Override
    public void onBindViewHolder(BrandsViewHolder holder, final int position) {
        final Brand selectedBrand = getContentAt(position);
        holder.bind(selectedBrand, context);
    }

    @Override
    protected void onItemClicked(Brand bean) {
        EventBus.getDefault().post(new BrandSelectedEvent(bean));
    }

    @Override
    protected int getLayoutItemId() {
        return R.layout.item_brand;
    }

}

package yanislav.com.autodata.adapters;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.viewholders.FullScreenCarouselViewHolder;
import yanislav.com.autodata.events.FullScreenCarouselImageSelected;
import yanislav.com.autodata.model.ImagesInfoData;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 26.3.2017 г..
 */

public class FullScreenCarouselAdapter extends AutoDataAdapter<ImagesInfoData, FullScreenCarouselViewHolder> {

    public FullScreenCarouselAdapter(List<ImagesInfoData> content, Context context) {
        super(FullScreenCarouselViewHolder.class, content, context);
    }

    @Override
    protected void onItemClicked(ImagesInfoData bean) {
        EventBus.getDefault().post(new FullScreenCarouselImageSelected(bean));
    }

    @Override
    protected int getLayoutItemId() {
        return R.layout.item_car_image;
    }
}

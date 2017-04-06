package yanislav.com.autodata.adapters;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.viewholders.CarouselViewHolder;
import yanislav.com.autodata.events.CarouselImageSelectedEvent;
import yanislav.com.autodata.model.ImageHolder;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 12.3.2017 г..
 */

public class CaroselAdapter extends AutoDataAdapter<ImageHolder, CarouselViewHolder> {

    public CaroselAdapter(List<ImageHolder> content,
                          Context context) {
        super(CarouselViewHolder.class, content, context);
    }

    @Override
    protected void onItemClicked(ImageHolder bean) {
        EventBus.getDefault().post(new CarouselImageSelectedEvent(bean));
    }

    @Override
    protected int getLayoutItemId() {
        return R.layout.item_car_image;
    }


}

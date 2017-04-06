package yanislav.com.autodata.adapters;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.viewholders.CarListInfoDataViewHolder;
import yanislav.com.autodata.events.CarListSelectedEvent;
import yanislav.com.autodata.model.CarListInfoData;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 13.3.2017 г..
 */

public class CarListInfoAdapter extends AutoDataAdapter<CarListInfoData, CarListInfoDataViewHolder> {

    public CarListInfoAdapter(List<CarListInfoData> content, Context context) {
        super(CarListInfoDataViewHolder.class, content, context);
    }

    @Override
    protected void onItemClicked(CarListInfoData bean) {
        EventBus.getDefault().post(new CarListSelectedEvent(bean));
    }

    @Override
    protected int getLayoutItemId() {
        return R.layout.item_car_list_info_data;
    }
}

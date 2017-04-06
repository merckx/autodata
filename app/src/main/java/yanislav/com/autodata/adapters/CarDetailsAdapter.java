package yanislav.com.autodata.adapters;

import android.content.Context;

import java.util.List;

import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.viewholders.CarDetailsViewHolder;
import yanislav.com.autodata.model.DetailsInfoData;
import yanislav.com.autodata.utils.AutoDataAdapter;


/**
 * Created by yani on 15.3.2017 г..
 */

public class CarDetailsAdapter extends AutoDataAdapter<DetailsInfoData, CarDetailsViewHolder> {

    public CarDetailsAdapter(List<DetailsInfoData> content, Context context) {
        super(CarDetailsViewHolder.class, content, context);
    }

    @Override
    protected int getLayoutItemId() {
        return R.layout.item_car_details;
    }


}

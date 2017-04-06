package yanislav.com.autodata.adapters.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.model.CarListInfoData;

/**
 * Created by yani on 13.3.2017 г..
 */

public class CarListInfoDataViewHolder extends BaseViewHolder<CarListInfoData> {

    @BindView(R.id.engine_type)
    TextView engineType;

    @BindView(R.id.years)
    TextView years;

    public CarListInfoDataViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(CarListInfoData model, Context context) {
        engineType.setText(model.getName());
        years.setText(model.getYears());
    }
}

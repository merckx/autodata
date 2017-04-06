package yanislav.com.autodata.adapters.viewholders;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.model.DetailsInfoData;

/**
 * Created by yani on 15.3.2017 г..
 */

public class CarDetailsViewHolder extends BaseViewHolder<DetailsInfoData> {
    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.detail_value)
    TextView detailValue;
    public CarDetailsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(DetailsInfoData model, Context context) {
        detail.setText(Html.fromHtml(model.getKey()));
        detailValue.setText(Html.fromHtml(model.getValue()));
    }
}

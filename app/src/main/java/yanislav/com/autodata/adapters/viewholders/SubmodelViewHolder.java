package yanislav.com.autodata.adapters.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.model.Submodel;
import yanislav.com.autodata.utils.ImageUtil;

/**
 * Created by yani on 10.3.2017 г..
 */

public class SubmodelViewHolder extends BaseViewHolder<Submodel> {
    @BindView(R.id.submodel_name)
    TextView name;
    @BindView(R.id.image_submodel)
    ImageView image;
    @BindView(R.id.year_of_production)
    TextView yearOfProduction;

    public SubmodelViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Submodel model, Context context) {
        name.setText(model.getName());
        ImageUtil.loadCarImage(image, model.getImage(), context);
        String endYear = model.getEnd() == 0 ? "present" : String.valueOf(model.getEnd());
        yearOfProduction.setText(model.getBegin() + " - " + endYear);
    }


//    @Override
//    public void setOnClickListener(View.OnClickListener listener) {
//        name.setOnClickListener(listener);
//        image.setOnClickListener(listener);
//        yearOfProduction.setOnClickListener(listener);
//    }
}

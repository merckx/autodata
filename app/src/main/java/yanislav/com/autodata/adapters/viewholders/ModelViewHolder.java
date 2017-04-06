package yanislav.com.autodata.adapters.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.model.Model;
import yanislav.com.autodata.utils.ImageUtil;

/**
 * Created by yani on 21.2.2017 г..
 */

public class ModelViewHolder extends BaseViewHolder<Model> {

    @BindView(R.id.model_logo)
    ImageView image;
    @BindView(R.id.model_name)
    TextView name;

    public ModelViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Model model, Context context) {
        name.setText(model.getName());
        ImageUtil.loadCarImage(image, model.getImage(), context);
    }

//    @Override
//    public void setOnClickListener(View.OnClickListener listener)
//    {
//        name.setOnClickListener(listener);
//        image.setOnClickListener(listener);
//    }
}

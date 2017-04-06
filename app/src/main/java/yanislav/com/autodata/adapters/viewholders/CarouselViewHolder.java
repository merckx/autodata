package yanislav.com.autodata.adapters.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.model.ImageHolder;
import yanislav.com.autodata.utils.ImageUtil;

/**
 * Created by yani on 12.3.2017 г..
 */

public class CarouselViewHolder extends BaseViewHolder<ImageHolder> {

    @BindView(R.id.item_car_image)
    ImageView image;

    public CarouselViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(ImageHolder model, Context context) {
        ImageUtil.loadCarImage(image, model.getUrl(), context);
    }

//    @Override
//    public void setOnClickListener(View.OnClickListener listener) {
//        super.setOnClickListener(listener);
//    }
}

package yanislav.com.autodata.adapters.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.model.ImagesInfoData;
import yanislav.com.autodata.utils.ImageUtil;

/**
 * Created by yani on 26.3.2017 г..
 */

public class FullScreenCarouselViewHolder extends BaseViewHolder<ImagesInfoData> {

    @BindView(R.id.item_car_image)
    ImageView image;

    public FullScreenCarouselViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(ImagesInfoData model, Context context) {
        ImageUtil.loadCarImage(image, model.getSmall(), context);
    }
}

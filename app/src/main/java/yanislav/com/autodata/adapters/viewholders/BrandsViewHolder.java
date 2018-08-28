package yanislav.com.autodata.adapters.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.utils.ImageUtil;

/**
 * Created by yani on 20.2.2017 г..
 */

public class BrandsViewHolder extends BaseViewHolder<Brand> {

    @BindView(R.id.brand_logo)
    ImageView imageView;
    @BindView(R.id.brand_name)
    TextView brandName;

    public BrandsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Brand brand, Context context)
    {
        brandName.setText(brand.getName());
        Glide.with(context).load(ImageUtil.generateImageUrl(brand.getName())).apply(new RequestOptions().fitCenter()).into(imageView);
    }


//    @Override
//    public void setOnClickListener(View.OnClickListener listener)
//    {
//        brandName.setOnClickListener(listener);
//        imageView.setOnClickListener(listener);
//    }
}

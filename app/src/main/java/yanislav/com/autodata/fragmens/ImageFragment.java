package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.utils.ImageUtil;

/**
 * Created by yani on 28.3.2017 г..
 */

public class ImageFragment extends BaseAutodataFragment {
    @BindView(R.id.image)
    ImageView imageView;

    private String url;

    public static ImageFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            this.url = getArguments().getString("url");
        }
    }

    @Override
    protected void onCreateView() {
        Glide.with(this).load(ImageUtil.generateImageUrlModel(url)).asBitmap().error(R.drawable.no).into(imageView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image;
    }
}

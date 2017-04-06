package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.FullScreenCarouselAdapter;
import yanislav.com.autodata.adapters.FullScreenViewPagerAdapter;
import yanislav.com.autodata.events.ImagesInfoDataLoadedEvent;
import yanislav.com.autodata.model.ImageHolder;
import yanislav.com.autodata.model.ImagesInfoData;
import yanislav.com.autodata.network.Api;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 26.3.2017 г..
 */

public class FullScreenImageFragment extends BaseAutodataFragment {

    @BindView(R.id.car_images_list)
    RecyclerView carImagesList;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private ImageHolder imageHolder;

    public static FullScreenImageFragment newInstance(ImageHolder imageHolder) {

        Bundle args = new Bundle();
        args.putParcelable("imageHolder", imageHolder);
        FullScreenImageFragment fragment = new FullScreenImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            imageHolder = getArguments().getParcelable("imageHolder");
        }
    }

    @Override
    protected void onCreateView() {
        registerEventBus();
        carImagesList.setAdapter(new FullScreenCarouselAdapter(new ArrayList<ImagesInfoData>(), getActivity()));
        carImagesList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        viewPager.setAdapter(new FullScreenViewPagerAdapter(getChildFragmentManager(), new ArrayList<ImagesInfoData>()));
        Api.getInstance().loadImages(imageHolder);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImagesInfoDataLoadedEvent(ImagesInfoDataLoadedEvent event)
    {
        ((AutoDataAdapter)carImagesList.getAdapter()).switchContent(event.getImagesInfoData());
        viewPager.setAdapter(new FullScreenViewPagerAdapter(getActivity().getSupportFragmentManager(), event.getImagesInfoData()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fullscreen_image;
    }
}

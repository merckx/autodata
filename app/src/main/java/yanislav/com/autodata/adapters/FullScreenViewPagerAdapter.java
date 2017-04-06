package yanislav.com.autodata.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import yanislav.com.autodata.fragmens.ImageFragment;
import yanislav.com.autodata.model.ImagesInfoData;

/**
 * Created by yani on 28.3.2017 г..
 */

public class FullScreenViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<ImagesInfoData> imagesInfoDataList;

    public FullScreenViewPagerAdapter(FragmentManager fm, List<ImagesInfoData> imagesInfoDataList) {
        super(fm);

        this.imagesInfoDataList = imagesInfoDataList;
    }

//    public FullScreenViewPagerAdapter(List<ImagesInfoData> imagesInfoDataList) {
//        this.imagesInfoDataList = imagesInfoDataList;
//    }

    @Override
    public int getCount() {
        return imagesInfoDataList.size();
    }

    public void switchContent(List<ImagesInfoData> imagesInfoDatas)
    {
        this.imagesInfoDataList.clear();
        this.imagesInfoDataList.addAll(imagesInfoDatas);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(imagesInfoDataList.get(position).getBig());
    }

}

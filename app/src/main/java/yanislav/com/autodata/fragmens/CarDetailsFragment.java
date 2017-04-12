package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.CarDetailsAdapter;
import yanislav.com.autodata.adapters.CaroselAdapter;
import yanislav.com.autodata.api.Api;
import yanislav.com.autodata.events.CarDetailsLoadedEvent;
import yanislav.com.autodata.model.CarListInfoData;
import yanislav.com.autodata.model.DetailsInfoData;
import yanislav.com.autodata.model.ImageHolder;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 15.3.2017 г..
 */

public class CarDetailsFragment extends BaseAutodataFragment {
    @BindView(R.id.car_title)
    TextView carTitle;
    @BindView(R.id.car_images_list)
    RecyclerView carImages;
    @BindView(R.id.car_details_info_data)
    RecyclerView detailsTable;

    private CarListInfoData carListInfoData;

    public static CarDetailsFragment newInstance(CarListInfoData carListInfoData) {

        Bundle args = new Bundle();
        args.putParcelable("carListInfoData", carListInfoData);
        CarDetailsFragment fragment = new CarDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            carListInfoData = getArguments().getParcelable("carListInfoData");
        }
    }

    @Override
    protected void onCreateView() {
        registerEventBus();
        carTitle.setText(carListInfoData.getName());
        carImages.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        carImages.setAdapter(new CaroselAdapter(new ArrayList<ImageHolder>(), getActivity()));
        detailsTable.setLayoutManager(new LinearLayoutManager(getActivity()));
        detailsTable.setAdapter(new CarDetailsAdapter(new ArrayList<DetailsInfoData>(), getActivity()));
        Api.getInstance().loadCarDetails(carListInfoData);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_car_details;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCarDetailsLoadedEvent(CarDetailsLoadedEvent event)
    {
        AutoDataAdapter carImagesAdapter = (AutoDataAdapter) carImages.getAdapter();
        carImagesAdapter.switchContent(event.getImageHolderList());
        AutoDataAdapter detailsAdapter = (AutoDataAdapter) detailsTable.getAdapter();
        detailsAdapter.switchContent(event.getDetailsInfoData());
    }
}

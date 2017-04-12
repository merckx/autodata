package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.CarListInfoAdapter;
import yanislav.com.autodata.adapters.CaroselAdapter;
import yanislav.com.autodata.api.Api;
import yanislav.com.autodata.events.CarListLoadedEvent;
import yanislav.com.autodata.model.CarListInfoData;
import yanislav.com.autodata.model.ImageHolder;
import yanislav.com.autodata.model.Submodel;
import yanislav.com.autodata.utils.AutoDataAdapter;

/**
 * Created by yani on 12.3.2017 г..
 */

public class CarListFragment extends BaseAutodataFragment {
    @BindView(R.id.car_images_list)
    RecyclerView imagesList;
    @BindView(R.id.car_list_info_data)
    RecyclerView carListInfoData;

    private Submodel submodel;

    public static CarListFragment newInstance(Submodel submodel) {

        Bundle args = new Bundle();
        args.putParcelable("subModel", submodel);
        CarListFragment fragment = new CarListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            submodel = getArguments().getParcelable("subModel");
        }
    }

    @Override
    protected void onCreateView() {
        registerEventBus();
        imagesList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        imagesList.setAdapter(new CaroselAdapter(new ArrayList<ImageHolder>(), getActivity()));
        carListInfoData.setAdapter(new CarListInfoAdapter(new ArrayList<CarListInfoData>(), getActivity()));
        carListInfoData.setLayoutManager(new LinearLayoutManager(getActivity()));
        Api.getInstance().loadCarList(submodel);
    }

    @Override
    public void onResume() {
        super.onResume();
        setDisplayHomeAsUpEnabled(true);
        setTitle(submodel.getBrand());
        setSubtitle(submodel.getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_car_list;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCarListDataInfoLoadedEvent(CarListLoadedEvent event)
    {
        ((AutoDataAdapter) imagesList.getAdapter()).switchContent(event.getImageHolderList());
        ((AutoDataAdapter)carListInfoData.getAdapter()).switchContent(event.getCarListInfoDataList());
    }
}

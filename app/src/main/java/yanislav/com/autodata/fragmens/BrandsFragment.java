package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.commons.orientation.OrientationUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.BrandsAdapter;
import yanislav.com.autodata.events.BrandLoadedEvent;
import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.network.Api;
import yanislav.com.autodata.utils.GridDividerDecoration;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrandsFragment extends BaseAutodataFragment {

    @BindView(R.id.brands_list)
    RecyclerView brandsList;

    public static BrandsFragment newInstance() {

        Bundle args = new Bundle();

        BrandsFragment fragment = new BrandsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public BrandsFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        setDisplayHomeAsUpEnabled(false);
        setTitle("Autodata");
        setSubtitle(null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onCreateView() {
        registerEventBus();
        BrandsAdapter adapter = new BrandsAdapter(new ArrayList<Brand>(), getContext());
        brandsList.setLayoutManager(new GridLayoutManager(getActivity(), getSpanCount()));
//        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.grid_divider);
        brandsList.addItemDecoration(new GridDividerDecoration(getActivity()));
        brandsList.setAdapter(adapter);

        Api.getInstance().loadBrands();


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus();
    }


    private int getSpanCount() {
        if (OrientationUtil.isLandscape(getActivity()))
        {
            return 3;
        }
        else
        {
            return 2;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBrandsLoadedEvent(BrandLoadedEvent event) {
        BrandsAdapter adapter = (BrandsAdapter) brandsList.getAdapter();
        adapter.switchContent(event.getBrands());
    }
}

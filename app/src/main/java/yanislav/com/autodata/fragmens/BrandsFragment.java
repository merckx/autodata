package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.BrandsAdapter;
import yanislav.com.autodata.api.Api;
import yanislav.com.autodata.events.BrandLoadedEvent;
import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.utils.AutoDataAdapter;
import yanislav.com.autodata.utils.AutoDataRecyclerView;
import yanislav.com.autodata.utils.GridDividerDecoration;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrandsFragment extends BaseAutodataFragment {

    @BindView(R.id.brands_list)
    AutoDataRecyclerView brandsList;

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
        configureSearchableContent(true);
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
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBrandsLoadedEvent(BrandLoadedEvent event) {
        AutoDataAdapter adapter = brandsList.getAdapter();
        adapter.switchContent(event.getBrands());
    }


    @Override
    protected AutoDataRecyclerView getSearchableList()
    {
        return brandsList;
    }
}

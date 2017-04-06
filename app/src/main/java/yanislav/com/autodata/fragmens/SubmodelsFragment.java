package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.commons.orientation.OrientationUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.SubModelAdapter;
import yanislav.com.autodata.events.SubModelsLoadedEvent;
import yanislav.com.autodata.model.Model;
import yanislav.com.autodata.model.Submodel;
import yanislav.com.autodata.network.Api;
import yanislav.com.autodata.utils.AutoDataAdapter;
import yanislav.com.autodata.utils.GridDividerDecoration;

/**
 * Created by yani on 10.3.2017 г..
 */

public class SubmodelsFragment extends BaseAutodataFragment {
    public static final String MODEL = "model";
    @BindView(R.id.models_list)
    RecyclerView subModels;

    private Model model;

    public static SubmodelsFragment newInstance(Model model) {

        Bundle args = new Bundle();
        args.putParcelable(MODEL, model);
        SubmodelsFragment fragment = new SubmodelsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            model = getArguments().getParcelable(MODEL);
        }
    }

    @Override
    protected void onCreateView() {
        registerEventBus();
        subModels.addItemDecoration(new GridDividerDecoration(getActivity()));
        SubModelAdapter adapter = new SubModelAdapter(new ArrayList<Submodel>(), getContext());
        subModels.setAdapter(adapter);
        subModels.setLayoutManager(new GridLayoutManager(getActivity(), getSpanCount()));

        Api.getInstance().loadSubModels(model);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubModelsLoadedEvent(SubModelsLoadedEvent event)
    {
        AutoDataAdapter adapter = (AutoDataAdapter) subModels.getAdapter();
        adapter.switchContent(event.getSubmodelList());
    }

    @Override
    public void onResume() {
        super.onResume();
        setDisplayHomeAsUpEnabled(true);
        setTitle(model.getBrand());
        setSubtitle(model.getName());
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


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_model;
    }
}

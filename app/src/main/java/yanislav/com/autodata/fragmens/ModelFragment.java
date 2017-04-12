package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import yanislav.com.autodata.R;
import yanislav.com.autodata.adapters.ModelAdapter;
import yanislav.com.autodata.events.ModelsLoadedEvent;
import yanislav.com.autodata.model.Brand;
import yanislav.com.autodata.model.Model;
import yanislav.com.autodata.api.Api;
import yanislav.com.autodata.utils.AutoDataRecyclerView;
import yanislav.com.autodata.utils.GridDividerDecoration;

/**
 * Created by yani on 21.2.2017 г..
 */

public class ModelFragment extends BaseAutodataFragment {

    public static final String BRAND = "brand";

    @BindView(R.id.models_list)
    AutoDataRecyclerView modelsList;


    private Brand brand;

    public static ModelFragment newInstance(Brand brand) {

        Bundle args = new Bundle();
        args.putParcelable(BRAND, brand);
        ModelFragment fragment = new ModelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            brand = getArguments().getParcelable(BRAND);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_model;
    }

    @Override
    protected void onCreateView() {
        ModelAdapter adapter = new ModelAdapter(new ArrayList<Model>(), getContext());
        modelsList.addItemDecoration(new GridDividerDecoration(getActivity()));
        modelsList.setLayoutManager(new GridLayoutManager(getActivity(), getSpanCount()));
////        modelsList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        modelsList.setAdapter(adapter);
        Api.getInstance().loadModels(brand);
        registerEventBus();
    }

    @Override
    public void onResume() {
        super.onResume();
        setDisplayHomeAsUpEnabled(true);
        setTitle(brand.getName());
        setSubtitle(null);
        configureSearchableContent(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onModelsLoadedEvent(ModelsLoadedEvent event)
    {
        ModelAdapter adapter = (ModelAdapter) modelsList.getAdapter();
        adapter.switchContent(event.getModels());
    }

    @Override
    protected AutoDataRecyclerView getSearchableList()
    {
        return modelsList;
    }
}

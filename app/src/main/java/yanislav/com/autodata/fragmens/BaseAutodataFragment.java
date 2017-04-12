package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.commons.orientation.OrientationUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import yanislav.com.autodata.R;
import yanislav.com.autodata.events.EventBusUtils;
import yanislav.com.autodata.utils.AutoDataAdapter;
import yanislav.com.autodata.utils.AutoDataRecyclerView;

/**
 * Created by yani on 25.2.2017 г..
 */

public abstract class BaseAutodataFragment extends Fragment
                                           implements SearchView.OnQueryTextListener
{

    private View mainView;
    private Unbinder unbinder;
    private boolean isSearchable;

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.mainView = paramLayoutInflater.inflate(getLayoutId(), paramViewGroup, false);
        unbinder = ButterKnife.bind(this, this.mainView);
//        this.pd = new ProgressDialog(getActivity());
//        this.pd.setCancelable(false);
        setHasOptionsMenu(true);
        onCreateView();
        return this.mainView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void setDisplayHomeAsUpEnabled(boolean enabled)
    {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
    }

    protected void setTitle(CharSequence title)
    {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    protected void setTitle(@StringRes int titleId)
    {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(titleId);
    }

    protected void setSubtitle(CharSequence subtitle)
    {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(subtitle);
    }

    protected CharSequence getTitle()
    {
        return ((AppCompatActivity) getActivity()).getSupportActionBar().getTitle();
    }

    protected CharSequence getSubTitle()
    {
        return ((AppCompatActivity) getActivity()).getSupportActionBar().getSubtitle();
    }

    protected abstract void onCreateView();

    protected abstract int getLayoutId();

    protected void registerEventBus()
    {
        EventBusUtils.register(this);
    }

    protected void unregisterEventBus()
    {
        EventBusUtils.unregister(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchItem.setVisible(isSearchable);
        if (isSearchable)
        {
            searchView.setOnQueryTextListener(this);
        }

    }

    protected void configureSearchableContent(boolean canBeSearched)
    {
        this.isSearchable = canBeSearched;
    }


    protected int getSpanCount()
    {
        if (OrientationUtil.isLandscape(getActivity()))
        {
            return 3;
        } else
        {
            return 2;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        filter(newText);
        return false;
    }

    protected void filter(String filterText)
    {
        AutoDataRecyclerView searchableList = getSearchableList();
        if (searchableList != null)
        {
            searchableList.getAdapter().getFilter().filter(filterText);
        }
    }

    protected AutoDataRecyclerView getSearchableList()
    {
        return null;
    }
}

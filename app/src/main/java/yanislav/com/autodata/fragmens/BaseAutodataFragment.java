package yanislav.com.autodata.fragmens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import yanislav.com.autodata.events.EventBusUtils;

/**
 * Created by yani on 25.2.2017 г..
 */

public abstract class BaseAutodataFragment extends Fragment {

    private View mainView;
    private Unbinder unbinder;

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.mainView = paramLayoutInflater.inflate(getLayoutId(), paramViewGroup, false);
        unbinder = ButterKnife.bind(this, this.mainView);
//        this.pd = new ProgressDialog(getActivity());
//        this.pd.setCancelable(false);
        onCreateView();
        return this.mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void setDisplayHomeAsUpEnabled(boolean enabled)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
    }

    protected void setTitle(CharSequence title)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
    }

    protected void setTitle(@StringRes int titleId)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(titleId);
    }

    protected void setSubtitle(CharSequence subtitle)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(subtitle);
    }

    protected CharSequence getTitle()
    {
        return ((AppCompatActivity)getActivity()).getSupportActionBar().getTitle();
    }

    protected CharSequence getSubTitle()
    {
        return ((AppCompatActivity)getActivity()).getSupportActionBar().getSubtitle();
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
}

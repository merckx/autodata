package yanislav.com.autodata.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by yani on 27.2.2017 г..
 */

public class FragmentsManager {

    private static final boolean DEFAULT_ADD_TO_BACKSTACK = true;

    private int containerId;
    private FragmentManager fragmentManager;

    public FragmentsManager(int containerId, FragmentManager fragmentManager) {
        this.containerId = containerId;
        this.fragmentManager = fragmentManager;
    }

    public void addFragment(Fragment fragment)
    {
        addFragment(fragment, false);
    }

    public void addFragment(Fragment fragment, boolean addToBackStack)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction().add(containerId, fragment);
        if (addToBackStack)
        {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment)
    {
        replaceFragment(fragment, DEFAULT_ADD_TO_BACKSTACK);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(containerId, fragment);
        if (addToBackStack)
        {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }
}

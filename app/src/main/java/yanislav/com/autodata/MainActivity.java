package yanislav.com.autodata;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import yanislav.com.autodata.events.BrandSelectedEvent;
import yanislav.com.autodata.events.CarListSelectedEvent;
import yanislav.com.autodata.events.CarouselImageSelectedEvent;
import yanislav.com.autodata.events.ModelSelectedEvent;
import yanislav.com.autodata.events.SubModelItemSelectedEvent;
import yanislav.com.autodata.fragmens.BrandsFragment;
import yanislav.com.autodata.fragmens.CarDetailsFragment;
import yanislav.com.autodata.fragmens.CarListFragment;
import yanislav.com.autodata.fragmens.FullScreenImageFragment;
import yanislav.com.autodata.fragmens.ModelFragment;
import yanislav.com.autodata.fragmens.SubmodelsFragment;
import yanislav.com.autodata.utils.FragmentsManager;

public class MainActivity extends AppCompatActivity
{

    private FragmentsManager fragmentsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentsManager = new FragmentsManager(R.id.fragment, getSupportFragmentManager());
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });
        if (savedInstanceState == null) {
            BrandsFragment fragment = BrandsFragment.newInstance();
            fragmentsManager.addFragment(fragment);
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment, fragment).commitAllowingStateLoss();
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBrandSelectedEvent(BrandSelectedEvent event) {
        ModelFragment fragment = ModelFragment.newInstance(event.getBrand());
        fragmentsManager.replaceFragment(fragment);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commitAllowingStateLoss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onModelSelectedEvent(ModelSelectedEvent event) {
        SubmodelsFragment fragment = SubmodelsFragment.newInstance(event.getModel());
        fragmentsManager.replaceFragment(fragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubModelSelectedEvent(SubModelItemSelectedEvent event) {
        CarListFragment fragment = CarListFragment.newInstance(event.getSubmodel());
        fragmentsManager.replaceFragment(fragment);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCarListInfoSelectedEvent(CarListSelectedEvent event) {
        CarDetailsFragment fragment = CarDetailsFragment.newInstance(event.getCarListInfoData());
        fragmentsManager.replaceFragment(fragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCarouselImageSelected(CarouselImageSelectedEvent event)
    {
        FullScreenImageFragment fragment = FullScreenImageFragment.newInstance(event.getImageHolder());
        fragmentsManager.replaceFragment(fragment);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void showNoInternetView()
    {
        TextView tv = (TextView) findViewById(R.id.no_internet_view);
        tv.setVisibility(View.VISIBLE);
    }
}

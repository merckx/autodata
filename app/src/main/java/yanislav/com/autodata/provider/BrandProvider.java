package yanislav.com.autodata.provider;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import yanislav.com.autodata.AutoDataApp;
import yanislav.com.autodata.api.Api;
import yanislav.com.autodata.events.BrandLoadedEvent;
import yanislav.com.autodata.model.BrandDao;

/**
 * Created by yanislav on 4/22/17.
 */

public class BrandProvider
{
    public static void loadBrands()
    {
        //todo load from db
        BrandDao brandDao = AutoDataApp.getINSTANCE()
                .getDaoSession()
                .getBrandDao();
        Observable.just(brandDao.loadAll())
                .subscribeOn(Schedulers.io())
                .subscribe(brands -> EventBus.getDefault().post(new BrandLoadedEvent(brands)));
        //load from web
        Api.getInstance().loadBrands();
    }
}

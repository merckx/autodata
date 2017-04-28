package yanislav.com.autodata;

import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;

import yanislav.com.autodata.model.DaoMaster;
import yanislav.com.autodata.model.DaoSession;

/**
 * Created by yanislav on 4/20/17.
 */

public class AutoDataApp extends Application
{

    private static AutoDataApp INSTANCE;
    private DaoSession daoSession;

    @Override
    public void onCreate()
    {
        super.onCreate();
        INSTANCE = this;
        initDb();
    }

    private void initDb()
    {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "autodata-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession()
    {
        return daoSession;
    }

    public static AutoDataApp getINSTANCE()
    {
        return INSTANCE;
    }

    public Context getContext()
    {
        return getApplicationContext();
    }

}

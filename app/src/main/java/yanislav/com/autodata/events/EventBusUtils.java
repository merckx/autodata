package yanislav.com.autodata.events;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yani on 25.2.2017 г..
 */

public class EventBusUtils {

    public static void register(Object subscriber)
    {
        if (!EventBus.getDefault().isRegistered(subscriber))
        {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unregister(Object subscriber)
    {
        if (EventBus.getDefault().isRegistered(subscriber))
        {
            EventBus.getDefault().unregister(subscriber);
        }
    }
}

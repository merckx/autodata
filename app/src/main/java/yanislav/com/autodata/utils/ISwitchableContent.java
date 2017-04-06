package yanislav.com.autodata.utils;

import java.util.List;

/**
 * Created by yani on 25.2.2017 г..
 */

public interface ISwitchableContent<T> {
    void switchContent(List<T> newContent);
}

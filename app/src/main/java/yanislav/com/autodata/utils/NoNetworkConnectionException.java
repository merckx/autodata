package yanislav.com.autodata.utils;

import java.io.IOException;

/**
 * Created by yanislav on 4/20/17.
 */

public class NoNetworkConnectionException extends IOException
{
    public NoNetworkConnectionException()
    {
        super("No internet connection");
    }
}

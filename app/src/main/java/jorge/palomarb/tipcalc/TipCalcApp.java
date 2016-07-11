package jorge.palomarb.tipcalc;

import android.app.Application;

/**
 * Created by JORGE on 17/06/2016.
 */
public class TipCalcApp extends Application
{
    private final String ABOUT_URL = "https://www.facebook.com/profile.php?id=100000349676937";

    public String getABOUT_URL()
    {
        return ABOUT_URL;
    }
}

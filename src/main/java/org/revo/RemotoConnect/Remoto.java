package org.revo.RemotoConnect;

import static org.revo.RemotoConnect.Util.Delay;
import static org.revo.RemotoConnect.Util.RandomContent;

/**
 * Created by revo on 03/09/15.
 */
public class Remoto {
    public static Site getData(String Url) {
        Delay();
        return new Site(Url, RandomContent());
    }

    public static Site Translate(Site site) {
        Delay();
        return site.setContent(RandomContent());
    }

}

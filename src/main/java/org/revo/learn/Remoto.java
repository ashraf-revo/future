package org.revo.learn;

import static org.revo.learn.Util.Delay;
import static org.revo.learn.Util.RandomContent;

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

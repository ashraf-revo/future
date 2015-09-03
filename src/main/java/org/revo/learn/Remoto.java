package org.revo.learn;

import static org.revo.learn.Util.*;

/**
 * Created by revo on 03/09/15.
 */
public class Remoto {
    public static Data getData(String Url) {
        Delay();
        return new Data(Url, RandomContent());
    }
}

package org.revo.RemotoConnect;

import java.util.UUID;

/**
 * Created by revo on 03/09/15.
 */
public class Util {
    public static void Delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String RandomContent() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

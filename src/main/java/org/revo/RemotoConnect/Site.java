package org.revo.RemotoConnect;

/**
 * Created by revo on 03/09/15.
 */
public class Site {
    private String Url;
    private String Content;

    public Site(String url, String content) {
        Url = url;
        Content = content;
    }
    public Site setContent(String content) {
        Content = content;
        return this;
    }
}

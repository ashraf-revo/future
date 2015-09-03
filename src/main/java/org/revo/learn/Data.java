package org.revo.learn;

/**
 * Created by revo on 03/09/15.
 */
public class Data {
    private String Url;
    private String Content;

    public Data(String url, String content) {
        Url = url;
        Content = content;
    }

    public Data() {
    }

    public String getUrl() {
        return Url;
    }

    public Data setUrl(String url) {
        Url = url;
        return this;
    }

    public String getContent() {
        return Content;
    }

    public Data setContent(String content) {
        Content = content;
        return this;
    }
}

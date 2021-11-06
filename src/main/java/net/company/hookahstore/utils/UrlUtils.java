package net.company.hookahstore.utils;

public class UrlUtils {
    public static boolean isMediaUrl(String url){
        return url.startsWith("/media/");
    }
    public static boolean isStaticUrl(String url){
        return url.startsWith("/static/");
    }
    private UrlUtils(){}


}

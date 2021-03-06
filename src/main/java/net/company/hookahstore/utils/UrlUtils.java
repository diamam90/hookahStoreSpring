package net.company.hookahstore.utils;

public class UrlUtils {
    public static boolean isMediaUrl(String url){
        return url.startsWith("/media/");
    }
    public static boolean isStaticUrl(String url){
        return url.startsWith("/static/");
    }
    public static boolean isAjaxJsonUrl(String url) {return url.startsWith("/ajax/json/");}
    public static boolean isAjaxHtmlUrl(String url) { return url.startsWith("/ajax/html");}
    public static boolean isAjaxUrl(String url) {return url.startsWith("/ajax");}


    private UrlUtils(){}


}

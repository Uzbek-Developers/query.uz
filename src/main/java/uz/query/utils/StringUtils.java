package uz.query.utils;

/**
 * Created by sherali on 12/18/15.
 */
public class StringUtils {
    public static boolean isEmty(String str) {
        return str == null || str.isEmpty();
    }

    public static String makeLinkFromTitle(String prefix, String title) {
        String titleInUrl = title.toLowerCase().trim().replaceAll("[^a-zA-Z0-9\\-\\s\\.]", "");
        titleInUrl = titleInUrl.replaceAll("[\\-| |\\.]+", "-");
        int TITLE_IN_URL_MAX_LENGTH = 100;
        String link = prefix;
        if (titleInUrl.length() > TITLE_IN_URL_MAX_LENGTH) {
            link += titleInUrl.substring(0, TITLE_IN_URL_MAX_LENGTH);
        } else {
            link += titleInUrl;
        }
        return link;
    }
}

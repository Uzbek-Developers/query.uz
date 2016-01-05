package uz.query;

/**
 * Created by sherali on 12/13/15.
 */
public interface Constants {
    //region <Application params>
    public static final String APP_VERSION = "1.0";
    public static final String APP_DOMEN = "Query.uz";
    public static final String APP_NAME = "Query";
    //endregion

    //region <Request Mapping urls>
    public final static String HOME = "/home";
    public final static String ASK_QUESTION = "/ask_question";
    public final static String USER_PROFILE = "/user_profile";
    public final static String WELCOME = "/welcome";

    public final static String LOGIN = "/login";
    public final static String REGISTRATION = "/registration";
    //endregion

    //region <Post params>
    public static final int SMALL_PAGE_SIZE = 15;
    public static final int MIDDLE_PAGE_SIZE = 30;
    public static final int LARGE_PAGE_SIZE = 50;
    //endregion

    //region <For model attributes>
    public static final String PAGE = "page";
    public static final String VIEW_DATA = "viewData";
    public static final String RULES_LIST = "rulesList";
    public static final String TAG_LIST = "tagList";
    //endregion
}
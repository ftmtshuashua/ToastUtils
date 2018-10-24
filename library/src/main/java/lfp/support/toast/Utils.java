package lfp.support.toast;

import android.app.Application;

/**
 * <pre>
 * Tip:
 *      辅助工具类
 *
 * Created by LiFuPing on 2018/10/23 17:34
 * </pre>
 */
class Utils {
    static Application app;

    public static <T extends Application> T getApp() {
        if (app == null) {
            try {
                Class<?> activityThread = Class.forName("android.app.ActivityThread");
                Object at = activityThread.getMethod("currentActivityThread").invoke(null);
                Object app = activityThread.getMethod("getApplication").invoke(at);
                Utils.app = (Application) app;
            } catch (Exception e) {
            }
        }
        return (T) app;
    }


}

package com.coolweather.android.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/9.
 */

public class ActivityController {
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void  removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }

    public static int getStackSize() {
        return activities.size();
    }
}

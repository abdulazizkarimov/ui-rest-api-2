package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import model.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {
    private static ISettingsFile config = new JsonSettingsFile("config.json");

    private static String getHostUrl() {
        return config.getValue("/host_url").toString();
    }

    public static String getApiAddress() {
        String api = config.getValue("/api").toString();
        return getHostUrl() + api;
    }

    public static String getWebAddress() {
        String web = config.getValue("/web").toString();
        return getHostUrl() + web;
    }

    public static String getAuthUrl(String url, String login, String password) {
        String[] arr = url.split("//");
        String access = login + ":" + password + "@";
        return arr[0] + "//" + access + arr[1];
    }

    public static boolean isSortedByDate(List<Test> testList) {
        List<Date> dates = getDates(testList);

        for (int i = 0; i <= dates.size() - 2; i++) {
            if (dates.get(i).getTime() < dates.get(i + 1).getTime()) {
                return false;
            }
        }

        return true;
    }

    private static List<Date> getDates(List<Test> testList) {
        List<Date> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        for (Test test : testList) {
            Date date = null;
            try {
                date = sdf.parse(test.getStartTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dates.add(date);
        }

        return dates;
    }

    public static boolean allMatch(List<Test> apiTests, List<Test> webTests) {
        List<String> apiNames = getTestNames(apiTests);
        List<String> webNames = getTestNames(webTests);
        int i = 0;

        for (String str : webNames) {
            if (apiNames.contains(str)) {
                i++;
            }
        }

        return (i == webTests.size()) ? true : false;
    }

    private static List<String> getTestNames(List<Test> testList) {
        List<String> names = new ArrayList<>();

        for (Test test : testList) {
            names.add(test.getName());
        }

        return names;
    }
}
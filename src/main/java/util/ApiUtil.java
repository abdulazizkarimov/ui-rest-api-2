package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Test;
import java.util.List;

public class ApiUtil {
    private static ISettingsFile config = new JsonSettingsFile("config.json");

    public static String getToken(String variant) {
        String url = Util.getApiAddress() + config.getValue("/tokenUrl").toString();

        HttpResponse<String> response = null;
        try {
            response = Unirest.post(url)
                    .queryString("variant", variant)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return response.getBody();
    }

    public static List<Test> getAllTests(String projectId) {
        String url = Util.getApiAddress() + config.getValue("/allTestsUrl").toString();

        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(url)
                    .queryString("projectId", projectId)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return MapperUtil.mapToTestList(response.getBody().getArray().toString());
    }

    public static String addTestToProject(String projectName, Test test) {
        String url = Util.getApiAddress() + config.getValue("/addTestUrl").toString();

        HttpResponse<String> response = null;
        try {
            response = Unirest.post(url)
                    .queryString("SID", test.getSid())
                    .field("projectName", projectName)
                    .field("testName", test.getName())
                    .field("methodName", test.getMethod())
                    .field("env", test.getEnv())
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return response.getBody();
    }

    public static void addLogToTest(String testId, String log) {
        String url = Util.getApiAddress() + config.getValue("/addLogUrl").toString();

        try {
            Unirest.post(url)
                    .queryString("testId", testId)
                    .field("content", log)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void addScreenshotToTest(String testId, String screenshot) {
        String url = Util.getApiAddress() + config.getValue("/addScreenShotUrl").toString();

        try {
            Unirest.post(url)
                    .queryString("testId", testId)
                    .field("content", screenshot)
                    .field("contentType", "image/png")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
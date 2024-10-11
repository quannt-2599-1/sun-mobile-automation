package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class BrowserStackUtils {
    public static String sessionNameGenerate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        return "Session_" + formatter.format(date);
    }

    public static String buildNameGenerate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        return "Build_" + formatter.format(date);
    }

    public static HashMap<String, Object> getBrowserstackOptions(Properties props) {
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();

        String userName = props.getProperty("userName");
        String accessKey = props.getProperty("accessKey");
        String appiumVersion = props.getProperty("appiumVersion");
        String sessionName = props.getProperty("sessionName");
        String projectName = props.getProperty("projectName");
        String buildName = props.getProperty("buildName");
        String debug = props.getProperty("debug");
        String enableCameraImageInjection = props.getProperty("enableCameraImageInjection");
        String interactiveDebugging = props.getProperty("interactiveDebugging");

        if (buildName == null) {
            buildName = BrowserStackUtils.buildNameGenerate();
        }

        browserstackOptions.put("userName", userName);
        browserstackOptions.put("accessKey", accessKey);
        browserstackOptions.put("appiumVersion", appiumVersion);
        browserstackOptions.put("debug", debug);
        browserstackOptions.put("buildName", buildName);
        browserstackOptions.put("sessionName", sessionName);
        browserstackOptions.put("projectName", projectName);
        browserstackOptions.put("enableCameraImageInjection", enableCameraImageInjection);
        browserstackOptions.put("interactiveDebugging", interactiveDebugging);
        return browserstackOptions;
    }
}

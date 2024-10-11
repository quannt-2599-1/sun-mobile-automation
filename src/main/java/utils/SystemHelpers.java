package utils;

import constant.OsType;

import java.io.File;

public final class SystemHelpers {
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    public static String getOSInfo() {
        return System.getProperty("os.name");
    }

    public static OsType getOsInfo() {
        if (isWindows()) {
            return OsType.WINDOWS;
        } else if (isMac()) {
            return OsType.MAC;
        }
        return OsType.LINUX;
    }

    public static boolean isWindows() {
        return (OS_NAME.contains("win"));
    }

    public static boolean isMac() {
        return (OS_NAME.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS_NAME.contains("nix") || OS_NAME.contains("nux") || OS_NAME.contains("aix"));
    }

    public static boolean isSolaris() {
        return (OS_NAME.contains("sunos"));
    }

    /**
     * @return Get the path to your source directory with a / at the end
     */
    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }

    /**
     * Create folder empty
     *
     * @param path path to create folder
     */
    public static void createFolder(String path) {
        // File is a class inside java.io package
        File file = new File(path);

        int lengthSum = path.length();
        int lengthSub = path.substring(0, path.lastIndexOf('/')).length();

        String result = path.substring(lengthSub, lengthSum);

        if (!file.exists()) {
            file.mkdir(); // mkdir is used to create folder
            System.out.println("Folder " + file.getName() + " created: " + path);
        } else {
            System.out.println("Folder already created");
        }
    }

}

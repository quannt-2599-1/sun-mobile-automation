package server;

import static constant.Constant.DEBUG_LOG_LEVEL;
import static constant.Constant.DEFAULT_NODEJS_PATH;
import static constant.Constant.DEFAULT_SERVER_PORT;
import static constant.Constant.PROP_CONFIG_FILE;
import static constant.Constant.SERVER_LOG_DIR;
import static constant.Constant.SERVER_LOG_FILE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import constant.OsType;
import drivers.PropertyManager;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utils.SystemHelpers;
import utils.TestUtils;

/**
 * [ServerManager] manages the Appium server
 */
public class ServerManager {
	private static final ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
	private static int portNumber = DEFAULT_SERVER_PORT;

	public void stopServer() {
		AppiumDriverLocalService server = ServerManager.server.get();
		if (server != null) {
			server.stop();
			TestUtils.log().info("[TEARDOWN] Appium server stopped");
		}
	}

	/**
	 * [getServer] gets the Appium server running in a local thread
	 *
	 * @return an instance of [AppiumDriverLocalService]
	 */
	public AppiumDriverLocalService getServer() {
		return server.get();
	}

	/**
	 * [startServer] starts the Appium server
	 *
	 * @throws IOException, IllegalArgumentException if the OS is not supported
	 */
	public void startServer() throws IOException, IllegalArgumentException {
		TestUtils.log().info("[SETUP] Starting appium server");
		Properties props = PropertyManager.getProps(PROP_CONFIG_FILE);

		// Use SystemHelpers để check OS
		AppiumDriverLocalService server;

		String propPath = props.getProperty("path");
		String propAndroidHome = props.getProperty("androidHome");
		String propAppiumJsPath = props.getProperty("appiumJsPath");
		String nodePath = props.getProperty("nodePath");
		String propJavaHome = props.getProperty("javaHome");
		if (nodePath == null)
			nodePath = DEFAULT_NODEJS_PATH;

		try {
			portNumber = Integer.parseInt(props.getProperty("port"));
		} catch (NumberFormatException e) {
			TestUtils.log().warn(
					"[SETUP] Port number is not set in the properties file. Using default port number: " + portNumber);
		}

		if (SystemHelpers.getOsInfo() == OsType.LINUX) {
			TestUtils.log().info("[SETUP] LINUX");

			if (propPath == null)
				throw new IllegalArgumentException("[SETUP] Path is not set in the properties file");
			if (propAndroidHome == null)
				throw new IllegalArgumentException("[SETUP] Android Home is not set in the properties file");
			if (propAppiumJsPath == null)
				throw new IllegalArgumentException("[SETUP] Appium JS Path is not set in the properties file");

			server = getLinuxAppiumService(propPath, propAndroidHome, propAppiumJsPath, nodePath);

		} else if (SystemHelpers.getOsInfo() == OsType.WINDOWS) {
			TestUtils.log().info("[SETUP] WINDOWS");
			server = getWindowsAppiumService();
		} else if (SystemHelpers.getOsInfo() == OsType.MAC) {
			TestUtils.log().info("[SETUP] MAC");

			if (propPath == null)
				throw new IllegalArgumentException("[SETUP] Path is not set in the properties file");
			if (propAndroidHome == null)
				throw new IllegalArgumentException("[SETUP] Android Home is not set in the properties file");
			if (propAppiumJsPath == null)
				throw new IllegalArgumentException("[SETUP] Appium JS Path is not set in the properties file");
			if (propJavaHome == null)
				throw new IllegalArgumentException("[SETUP] Java Home is not set in the properties file");

			server = MacGetAppiumService(propPath, propAndroidHome, propJavaHome, propAppiumJsPath, nodePath);
		} else {
			throw new IOException("[SETUP] OS not supported: " + SystemHelpers.getOSInfo());
		}

		server.start();

		if (!server.isRunning()) {
			TestUtils.log().fatal("[SETUP] Appium server not started. ABORT!!!");
			throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
		}
		server.clearOutPutStreams();
		ServerManager.server.set(server);
		TestUtils.log().info("[SETUP] Appium server started");
	}

	private AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}

	private AppiumDriverLocalService getWindowsAppiumService() {
		File logFile = getServerLogFile();

		AppiumServiceBuilder builder = new AppiumServiceBuilder().usingPort(portNumber).withArgument(SESSION_OVERRIDE)
				.withArgument(LOG_LEVEL, DEBUG_LOG_LEVEL).withLogFile(logFile);

		return AppiumDriverLocalService.buildService(builder);
	}

	private AppiumDriverLocalService getLinuxAppiumService(String path, String AndroidHome, String appiumJsPath,
			String nodePath) throws IllegalArgumentException {
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", path + System.getenv("PATH"));
		System.out.println("\n\n AndroidHome *******" + AndroidHome);
		environment.put("ANDROID_HOME", AndroidHome);

		File logFile = getServerLogFile();

		AppiumServiceBuilder builder = new AppiumServiceBuilder().usingDriverExecutable(new File(nodePath))
				.withAppiumJS(new File(appiumJsPath)).usingPort(portNumber).withArgument(SESSION_OVERRIDE)
				.withArgument(LOG_LEVEL, DEBUG_LOG_LEVEL).withEnvironment(environment).withLogFile(logFile);

		return AppiumDriverLocalService.buildService(builder);
	}

	private AppiumDriverLocalService MacGetAppiumService(String path, String androidHome, String javaHome,
			String appiumJsPath, String nodePath) {
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", path + System.getenv("PATH"));
		environment.put("ANDROID_HOME", androidHome);
		environment.put("JAVA_HOME", javaHome);

		File logFile = getServerLogFile();

		AppiumServiceBuilder builder = new AppiumServiceBuilder().usingDriverExecutable(new File(nodePath))
				.withAppiumJS(new File(appiumJsPath)).usingPort(portNumber).withArgument(SESSION_OVERRIDE)
				.withArgument(LOG_LEVEL, DEBUG_LOG_LEVEL).withEnvironment(environment).withLogFile(logFile);

		return AppiumDriverLocalService.buildService(builder);
	}

	private File getServerLogFile() {
		// GlobalParams params = new GlobalParams();
		// Create the Server Log directory if it doesn't exist
		File logDir = new File(SERVER_LOG_DIR);
		if (!logDir.exists()) {
			logDir.mkdirs();
		}

		File logFile = new File(SERVER_LOG_DIR + File.separator + SERVER_LOG_FILE);
		return logFile;
	}
}

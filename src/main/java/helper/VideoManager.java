package helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import drivers.DriverManager;
import drivers.GlobalParams;
import org.apache.commons.codec.binary.Base64;

import io.appium.java_client.screenrecording.CanRecordScreen;
import utils.TestUtils;

public class VideoManager {

	public void startRecording() {
		((CanRecordScreen) new DriverManager().getDriver()).startRecordingScreen();
	}

	public void stopRecording(String scenarioName) throws IOException {
		GlobalParams params = new GlobalParams();
		String media = ((CanRecordScreen) new DriverManager().getDriver()).stopRecordingScreen();
		String dirPath = params.getPlatformName() + "_" + params.getDeviceName() + File.separator + "Videos";

		File videoDir = new File(dirPath);

		synchronized (videoDir) {
			if (!videoDir.exists()) {
				videoDir.mkdirs();
			}
		}
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(videoDir + File.separator + scenarioName + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
			TestUtils.log().info("video path: " + videoDir + File.separator + scenarioName + ".mp4");
		} catch (Exception e) {
			TestUtils.log().error("error during video capture" + e.toString());
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
}

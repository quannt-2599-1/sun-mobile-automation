package listeners;

import java.io.IOException;

import org.openqa.selenium.OutputType;

import drivers.DriverManager;
import helper.VideoManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {
    /*Hooks run before and after every Cucumber Scenario*/
	private VideoManager videoManager;
	private DriverManager driverManager;

	public CucumberHooks() {
		this.videoManager = new VideoManager();
		this.driverManager = new DriverManager();
	}

	/* Hooks run before and after every Cucumber Scenario */
	@Before
	public void initialize() throws Exception {
		videoManager.startRecording();
	}

	@After
	public void quit(Scenario scenario) throws IOException {
		try {
			/* This is for attaching the screenshot in Cucumber report */
			if (scenario.isFailed()) {
				byte[] screenshot = driverManager.getDriver().getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", scenario.getName());
			}
		} finally {
			videoManager.stopRecording(scenario.getName());
		}
	}
}

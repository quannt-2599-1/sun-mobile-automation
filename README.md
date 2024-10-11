# Introduction
This project is designed for automated testing of mobile applications. It utilizes the Appium framework in combination with the Java programming language.

# Getting Started
## I. Setup environment
Follow below instructions:

### Install Appium & Driver

- Install Appium: [Flow this official quickstart instructions](https://appium.io/docs/en/2.4/quickstart/)
- Install Appium UIAutomator2 driver(V.3.7.7):
  ``` shell
  npm install -g appium-uiautomator2-driver@3.7.7
  ```
- Install Appium XCUItest driver
  ``` shell
  appium driver install XCUITest
  ```
- Install Appium Flutter Driver
  ``` shell
  appium driver install --source=npm appium-flutter-driver
  ```

### Config Path

Get paths config with following command (UNIX OS like Linux, OSX, etc... only):

``` shell
chmod +x ./get_path.sh
./get_path.sh
```

Create *_configs.properties_* file in *_src/main/resources_* folder and copy all contents of configs.properties.example
into it, then put following config into:

1. path
    - Linux, Mac
   > root path environment variable. Default in Unix OS is /usr/local/bin
    - Windows
   > *If you use Windows, please ignore this config*
2. javaHome:
    - Linux, Mac
   > JavaHome path variable
    - Windows
   > echo %JAVA_HOME%
3. appiumJsPath
    - Linux, Mac
   > Appium main.js file. If install with npm, usually it is /home/<username>
   /.nvm/versions/node/v20.16.0/lib/node_modules/appium/lib/main.js

   Or

   > /opt/homebrew/lib/node_modules/appium/build/lib/main.js if install with homebrew
    - Windows
   > *If you use Windows, please ingore this config*
4. nodePath
    - Linux, Mac
   > Nodejs Runpath
    - Windows
   > *If you use Windows, please ingore this config*
5. androidHome
    - Linux, Mac
   > Android SDK path variable
    - Windows
   > echo %ANDROID_HOME%
6. port
   > Appium server running port. Default is 4723

## II. Setup Emulator & App info

1. Put the application to be tested into src/test/resources/app
2. Replace the application path in the "androidAppLocation" field in the configs.properties file
3. Replace the package name and emulator device name in the configs.properties file

## III. Add plugin Cucumber and Gherkin (instructions for IntelliJ IDEA)

1. Open the Plugin Manager
- Open IntelliJ IDEA
- From the top menu, go to File > Settings (on Windows/Linux) or IntelliJ IDEA > Preferences (on macOS)
- In the Settings window, navigate to Plugins

2. Search and Install the plugins
- Cucumber for Java
- Gherkin

3. Restart IDE to complete the installation process

# Build and Test

1. With Maven
- Maven command:
    + Clean state Cucumber tests are executed: and mvn clean install
    + Output cucumber report: mvn test verify

2. With TestNG
- Open file suites/testng.xml
- Run file: right click > select Run/suites/testng.xml

# Using with BrowserStack
1. Config
- Login to [BrowserStack Platform](https://www.browserstack.com/) 
- Goto [DashBoard](https://app-automate.browserstack.com/dashboard) and get **Username** & **Access Key**
- Upload your APK Application to BrowserStack and get App_url (form like bs://4b4a666.... )
    - To upload app apk, you need use command:
  ```bash
  curl -u "Username:AccessKey" \
    -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
    -F "file=@/PATH_APP/app.apk"
- Create *browserstack.properties* file in src/main/resources folder and fill following config into:
  - userName 
  - accessKey 
  - appiumVersion 
  - sessionName 
  - projectName
  - buildName
  - debug

- Config following info in configs.properties file:
  - testDevice: "local" for local emulator testing and "browserstack" for using BrowserStack
  - androidAppLocation: App_URL if using Browser Stack and app name if using local
  - androidAvd: Device name

2. Run with command
- using mvn command to run test. You need to declare profileName in pom.xml file
```shell
mvn test -P profileName
```
Example:
```shell
mvn test -P testng-login
```

3. Run with file
Config information in config.properties and browserstack.properties, after that run by right click at the test_suite file -> run
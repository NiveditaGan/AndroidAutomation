package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IOSBaseTest {
    public IOSDriver driver;
    public AppiumDriverLocalService server;
    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        System.out.println("Hello World!");

        server = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
        server.start();
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 12 Pro Max");
        options.setApp("//Users//niveditagan//Library//Developer//Xcode//DerivedData//UIKitCatalog-efgmionujlkqmaexkqgcjkibgwha//Build//Products//Debug-iphonesimulator//UIKitCatalog.app");
        options.setPlatformVersion("16.2");
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
        server.stop();
    }
}

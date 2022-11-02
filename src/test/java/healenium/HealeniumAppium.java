package healenium;

import com.epam.healenium.appium.wrapper.DriverWrapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class HealeniumAppium {

    private static AppiumDriver appiumWrapper;

    @BeforeAll
    public static void setUp() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("deviceName", "23cebc82");
        dc.setCapability("platformVersion", "12");
        dc.setCapability("appPackage", "com.android.chrome");
        dc.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        dc.setCapability("noReset", "true");
        dc.setCapability("autoGrantPermissions", "true");

        //declare delegate driver
        appiumWrapper = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        appiumWrapper.get("https://elenastepuro.github.io/test_env/index.html");
        //adding healing support
        appiumWrapper = DriverWrapper.wrap(appiumWrapper);

    }

    @Test
    public void cambiandoSelector() throws Exception {

        Thread.sleep(2000);
        scrollVerticalAbajo(764,2006, 1144, 1);
        Thread.sleep(2000);

        // ENVIAR TEXTO A LA CAJA DE TEXTO
        WebElement txtCambiaName = appiumWrapper.findElement(By.xpath("//android.widget.EditText[@resource-id='change:name']"));
        txtCambiaName.sendKeys("Hola Prueba");
        Thread.sleep(2000);

        // BTN CAMBIAR LOCATOR
        WebElement btnLocator = appiumWrapper.findElement(By.xpath("//android.widget.Button[@resource-id='Submit']"));
        btnLocator.click();
        txtCambiaName.clear();

        // ENVIAR TEXTO A LA CAJA DE TEXTO
        WebElement txtCambiaName2 = appiumWrapper.findElement(By.xpath("//android.widget.EditText[@resource-id='change:name']"));
        txtCambiaName2.sendKeys("Cambiando de elemento");
        Thread.sleep(2000);

    }

    @AfterAll
    public static void tearDown() {
        if (appiumWrapper != null) {
            appiumWrapper.quit();
        }
    }

    // SCROLLVERCTICAL  ABAJO ANDROID
    public void scrollVerticalAbajo( int xini, int yini, int yfinal, int iteraciones) {
        for (int i = 1; i <= iteraciones; i++) {
            @SuppressWarnings("rawtypes")
            TouchAction touch = new TouchAction(appiumWrapper);
            touch.press(PointOption.point(xini, yini)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                    .moveTo(PointOption.point(xini, yfinal)).release().perform();

        }
    }

}


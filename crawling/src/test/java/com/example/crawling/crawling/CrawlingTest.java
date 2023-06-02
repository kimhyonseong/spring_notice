package com.example.crawling.crawling;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CrawlingTest {
  private Crawling crawling;

  @Test
  void staticCrawlingTest() {
    crawling = new StaticPageCrawling();
    crawling.crawling("https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105");
  }

  @Test
  void dynamicCrawlingTest() {
    crawling = new DynamicPageCrawling();
    crawling.crawling("https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105");
  }

  @Test
  void showSystemProperty() {
    String path = Paths.get("./driver").toAbsolutePath().toString();
    File dir = new File(path);
    File[] files = dir.listFiles();

    System.out.println("webdriver.chrome.driver: " + System.getProperty("webdriver.chrome.driver"));

    if (files != null && files.length > 0) {
      for (File file : files) {
        // 디렉토리이면 넘어감
        if (file.isDirectory()) continue;

        String fileName = file.toString();
        System.setProperty("webdriver.chrome.driver", fileName);
        System.out.println("webdriver.chrome.driver: " + System.getProperty("webdriver.chrome.driver"));
      }
    }
  }

  @Test
  void checkVersion() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("headless");
    options.addArguments("--lang=ko");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--disable-gpu");
    options.addArguments("--blink-settings=imagesEnabled=false");
    options.addArguments("--remote-allow-origins=*");
    options.setCapability("ignoreProtectedModeSettings", true);

    WebDriver driver = new ChromeDriver(options);

    String chromeVersion = driver.getClass().getPackage().getSpecificationVersion();
    System.out.println("chromeVersion: "+chromeVersion);

    String chromeDriverVersion = driver.getClass().getPackage().getImplementationVersion();
    System.out.println("chromeDriverVersion: "+chromeDriverVersion);
    System.out.println("getPackage: "+driver.getClass().getPackage());
  }
}
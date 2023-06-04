package com.example.crawling.crawling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class DynamicPageCrawling implements Crawling{
  private static Logger log = LogManager.getLogger(DynamicPageCrawling.class);
  private WebDriver driver;

  @Override
  public void crawling(String url) {
    if (setDriver()) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("headless");
      options.addArguments("--lang=ko");
      options.addArguments("--disable-popup-blocking");
      options.addArguments("--disable-gpu");
      options.addArguments("--blink-settings=imagesEnabled=false");
      options.addArguments("--remote-allow-origins=*");

      driver = new ChromeDriver(options);

      try {
        List<WebElement> list = getNewsList(url);

        for (WebElement element : list) {
          System.out.println(element.getText());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      driver.close();
      driver.quit();
    } else {
      log.error("Fail setting driver");
    }
  }

  private List<WebElement> getNewsList(String url) {
    List<WebElement> list = null;
    try {
      driver.get(url);
      Thread.sleep(1000);

      list = driver.findElements(By.cssSelector(".type06_headline"));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return list;
  }

  private boolean setDriver() {
    boolean result = false;
    String path = Paths.get("./driver").toAbsolutePath().toString();
    File dir = new File(path);
    File[] files = dir.listFiles();

    if (files != null && files.length > 0) {
      for(File file : files) {
        if (file.isDirectory()) continue;
        String fileName = file.toString();

        // 현재 브라우저랑 버전 같아야함
        System.setProperty("webdriver.chrome.driver", fileName);
        log.info("webdriver.chrome.driver: " + System.getProperty("webdriver.chrome.driver"));
        result = true;
      }
    } else {
      log.error("empty Directory: ./driver");
    }

    return result;
  }
}

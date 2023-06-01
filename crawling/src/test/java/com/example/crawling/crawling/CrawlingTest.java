package com.example.crawling.crawling;

import org.junit.jupiter.api.Test;

import java.io.File;
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
    File[] file = dir.listFiles();

    System.out.println("webdriver.chrome.driver: " + System.getProperty("webdriver.chrome.driver"));

    if (file != null && file.length > 0) {
      String fileName = file[0].toString();

      System.setProperty("webdriver.chrome.driver", fileName);
      System.out.println("webdriver.chrome.driver: " + System.getProperty("webdriver.chrome.driver"));
    }
  }
}
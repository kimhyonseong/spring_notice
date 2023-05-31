package com.example.crawling.staticPage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StaticPageCrawlingTest {
  private final StaticPageCrawling staticCrawling = new StaticPageCrawling();

  @Test
  void crawlingTest() {
    staticCrawling.crawling("https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105");
  }
}
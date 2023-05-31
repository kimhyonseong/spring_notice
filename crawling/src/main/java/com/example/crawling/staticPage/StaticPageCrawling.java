package com.example.crawling.staticPage;

import com.example.crawling.domain.OgGraph;
import com.example.crawling.service.OgGraphService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class StaticPageCrawling {
  private static final Logger log = LogManager.getLogger(StaticPageCrawling.class);
  private final OgGraphService ogGraphService = new OgGraphService();

  public void crawling(String url) {
    Connection connection = Jsoup.connect(url);
    Document document;

    try {
      document = connection.get();
    } catch (IOException e) {
      document = null;
      log.error(e);
    }

    OgGraph ogGraph = ogGraphService.getOgGraph(document);
    System.out.println(ogGraph);
  }
}

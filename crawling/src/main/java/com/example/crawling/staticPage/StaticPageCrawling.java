package com.example.crawling.staticPage;

import com.example.crawling.domain.OgGraph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticPageCrawling {
  private static final Logger log = LogManager.getLogger(StaticPageCrawling.class);
  //private static final String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105";

  public void crawling(String url) {
    Connection connection = Jsoup.connect(url);
    Document document;

    try {
      document = connection.get();
    } catch (IOException e) {
      document = null;
      log.error(e);
    }

    List<OgGraph> list = parseOgGraphData(document);
  }

  private List<OgGraph> parseOgGraphData(Document document) {
    List<OgGraph> list = new ArrayList<>();

    if (document == null) return list;
    Elements heads = Objects.requireNonNull(document.selectFirst("head")).select("meta");

    for (Element head : heads) {
      OgGraph tmpHead = matchPattern(head);

      if (tmpHead.isOgGraph()) {
        list.add(tmpHead);
      }
    }

    return list;
  }

  private OgGraph matchPattern(Element element) {
    OgGraph ogGraph = new OgGraph();
    String target = String.valueOf(element);
    System.out.println("target : " + target);

    //String property = "og:([^\"']+)";
    //String property = "<*[\"']og:([^\"']+)[\"']?";
    String property = "<*[\"']og:([^\"']+)[\"']?(.+)content=[\"']([^\"']+)[\"']";
    Pattern pattern = Pattern.compile(property);
    Matcher matcher = pattern.matcher(target);

    if (matcher.find()) {
      ogGraph.setOgGraph(true);
      ogGraph.setProperty(matcher.group(0));

      System.out.println(matcher.group(0));
      System.out.println(matcher.group(1));
      System.out.println(matcher.group(2));
      System.out.println(matcher.group(3));
      System.out.println(ogGraph);
    }

    return ogGraph;
  }
}

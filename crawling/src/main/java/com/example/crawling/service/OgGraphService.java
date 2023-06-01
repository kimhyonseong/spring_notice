package com.example.crawling.service;

import com.example.crawling.domain.OgGraph;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OgGraphService {
  public OgGraph getOgGraph(Document document) {
    if (document == null) return null;
    Elements heads = Objects.requireNonNull(document.selectFirst("head")).select("meta");

    return createOgGraph(heads);
  }

  private OgGraph createOgGraph(Elements elements) {
    OgGraph ogGraph = new OgGraph();

    for (Element element : elements) {
      String target = String.valueOf(element);

      String meta = "<*[\"']og:([^\"']+)[\"']?(.+)content=[\"']([^\"']+)[\"']";
      Pattern pattern = Pattern.compile(meta);
      Matcher matcher = pattern.matcher(target);

      if (matcher.find()) {
        String property = "set" + Character.toUpperCase(matcher.group(1).charAt(0)) + matcher.group(1).substring(1);
        String value = matcher.group(3);

        try {
          Method method = ogGraph.getClass().getMethod(property, String.class);
          method.invoke(ogGraph,value);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
          System.out.println("없는 메소드입니다.("+property+")");
        }
      }
    }

    return ogGraph;
  }
}

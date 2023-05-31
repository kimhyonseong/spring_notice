package com.example.crawling.domain;

public class OgGraph {
  private boolean ogGraph;
  private String property;
  private String value;

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public boolean isOgGraph() {
    return ogGraph;
  }

  public void setOgGraph(boolean ogGraph) {
    this.ogGraph = ogGraph;
  }

  @Override
  public String toString() {
    return "Head{" +
            "ogGraph=" + ogGraph +
            ", property='" + property + '\'' +
            ", value='" + value + '\'' +
            '}';
  }
}

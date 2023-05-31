package com.example.crawling.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OgGraph {
  private String title;
  private String description;
  private String type;
  private String image;
  private String url;
}

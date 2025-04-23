package com.elasticsearch.elasticsearchengine.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TourListDetailResponseVo {
    private int contentId;
    private String title;
    private String tags;
    private String addr;
    private String thumbnail;
    private LocalDate createdTime;
    private LocalDate modifiedTime;
    private String overview;
    private String homepage;
    private String infocenter;
    private String opendate;
    private String restdate;
    private String expguide;
    private String expagerange;
    private String accomcount;
    private String useseason;
    private String usetime;
    private String parking;
    private double mapx;
    private double mapy;
}

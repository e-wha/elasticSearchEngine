package com.elasticsearch.elasticsearchengine.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TourSaveRequestDto {

    private int contentId;

    private String contentTypeId;

    private String title;

    private String addr;

    private String zipCode;

    private String areaCode;

    private String sigunguCode;

    private String category;

    private String sideCategory;

    private String tags;

    private String thumbnail;

    private double mapx;

    private double mapy;

    private LocalDate createdTime;

    private LocalDate modifiedTime;
}

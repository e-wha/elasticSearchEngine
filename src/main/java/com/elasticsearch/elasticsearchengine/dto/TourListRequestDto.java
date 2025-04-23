package com.elasticsearch.elasticsearchengine.dto;

import lombok.Data;

@Data
public class TourListRequestDto {
    private String contentTypeId = "12";
    private String title;
    private String sigunguCode;
    private String category;
    private String tags;
    private int sort = 0;
    private int page = 1;
    private int size = 12;
}

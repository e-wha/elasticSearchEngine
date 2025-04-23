package com.elasticsearch.elasticsearchengine.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourListRequestDto {
    private String contentTypeId = "12";
    private String title;
    private String sigunguCode;
    private String category;
    private String tags;
    private Integer sort = 0;
    private Integer page = 1;
    private Integer size = 12;
}

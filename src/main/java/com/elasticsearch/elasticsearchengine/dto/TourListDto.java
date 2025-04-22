package com.elasticsearch.elasticsearchengine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourListDto {
    private int contentId;
    private String title;
    private String thumbnail;
    private String tags;
}

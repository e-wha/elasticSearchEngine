package com.elasticsearch.elasticsearchengine.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TourListResponseDto {
    private int totalList;
    private int currentPage;
    private int totalPages;
    private List<TourListDto> tourListDto;
}
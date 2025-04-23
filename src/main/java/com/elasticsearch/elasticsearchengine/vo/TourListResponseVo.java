package com.elasticsearch.elasticsearchengine.vo;

import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TourListResponseVo {
    private int totalCount;
    private int currentPage;
    private int totalPages;
    private List<TourListDto> tourListDto;
}

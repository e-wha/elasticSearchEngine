package com.elasticsearch.elasticsearchengine.vo;

import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import com.elasticsearch.elasticsearchengine.dto.TourListResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TourListResponseVo {
    private int totalList;
    private int currentPage;
    private int totalPages;
    private List<TourListDto> tourListDto;

    public static TourListResponseVo dtoToVo(TourListResponseDto tourListResponseDto) {
        return TourListResponseVo.builder()
                .totalList(tourListResponseDto.getTotalList())
                .currentPage(tourListResponseDto.getCurrentPage())
                .totalPages(tourListResponseDto.getTotalPages())
                .tourListDto(tourListResponseDto.getTourListDto())
                .build();
    }
}

package com.elasticsearch.elasticsearchengine.vo;

import com.elasticsearch.elasticsearchengine.dto.ExcelSaveTourListResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ExcelSaveTourListResponseVo {

    private int successCount;
    private int errorCount;
    private List<String> errors;

    public static ExcelSaveTourListResponseVo dtoToVo(ExcelSaveTourListResponseDto excelSaveTourListResponseDto) {
        return ExcelSaveTourListResponseVo.builder()
                .successCount(excelSaveTourListResponseDto.getSuccessCount())
                .errorCount(excelSaveTourListResponseDto.getFailedCount())
                .errors(excelSaveTourListResponseDto.getErrors())
                .build();
    }
}

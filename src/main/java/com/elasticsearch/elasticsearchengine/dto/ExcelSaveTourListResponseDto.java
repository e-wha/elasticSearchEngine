package com.elasticsearch.elasticsearchengine.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ExcelSaveTourListResponseDto {

    private int successCount;
    private int failedCount;
    private List<String> errors;
}

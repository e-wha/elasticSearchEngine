package com.elasticsearch.elasticsearchengine.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ExcelSaveTourListResponseVo {

    private int successCount;
    private int failedCount;
    private List<String> errors;
}

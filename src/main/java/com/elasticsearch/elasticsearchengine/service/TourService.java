package com.elasticsearch.elasticsearchengine.service;

import com.elasticsearch.elasticsearchengine.vo.ExcelSaveTourListResponseVo;
import com.elasticsearch.elasticsearchengine.vo.TourListResponseVo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TourService {

    public ExcelSaveTourListResponseVo excelSaveTourList(MultipartFile file) throws IOException;
    public TourListResponseVo findBycontentTypeId(String contentTypeId, Pageable pageable);
    public TourListResponseVo findByMultiCode(String contentTypeId, String sigunguCode, String category, List<String> tags, Pageable pageable);
}

package com.elasticsearch.elasticsearchengine.service;

import com.elasticsearch.elasticsearchengine.dto.ExcelSaveTourListResponseDto;
import com.elasticsearch.elasticsearchengine.dto.TourListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TourService {

    public ExcelSaveTourListResponseDto excelSaveTourList(MultipartFile file) throws IOException;
    public Page<TourListResponseDto> findBycontentTypeId(int page, String contentTypeId);
    public Page<TourListResponseDto> findByMultiCode(int page,  String contentTypeId, String sigunguCode, String sideCategory, List<String> tags);
}

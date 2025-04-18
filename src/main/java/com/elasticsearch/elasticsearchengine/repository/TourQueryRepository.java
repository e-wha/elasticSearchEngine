package com.elasticsearch.elasticsearchengine.repository;

import com.elasticsearch.elasticsearchengine.domain.Tour;
import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourQueryRepository {
    
    Page<TourListDto> findByMultiCode(String contentTypeId, String sigunguCode, String sideCategory, List<String> tags, Pageable pageable);
    
    //자연용
    Page<TourListDto> findMultiCodeNature(String contentTypeId, String category, String sigunguCode, String sideCategory, List<String> tags, Pageable pageable);
}

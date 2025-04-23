package com.elasticsearch.elasticsearchengine.repository;

import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourQueryRepository {
    
    Page<TourListDto> findByMultiCode(String contentTypeId, String sigunguCode, String category, List<String> tags, Pageable pageable);
}

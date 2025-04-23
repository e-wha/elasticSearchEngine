package com.elasticsearch.elasticsearchengine.service;

import com.elasticsearch.elasticsearchengine.vo.TourListDetailResponseVo;
import com.elasticsearch.elasticsearchengine.vo.TourListResponseVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourElasticSearchService {
    public TourListResponseVo searchTourWithPasing(String contentTypeId, String title, String sigunguCode, String category, List<String> tags, int sort, Pageable pageable);
    public TourListDetailResponseVo findByContentId(int contentId);
}

package com.elasticsearch.elasticsearchengine.repository;

import com.elasticsearch.elasticsearchengine.domain.TourAttractionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface TourAttractionRepository extends ElasticsearchRepository<TourAttractionDocument, Integer> {

    Optional<TourAttractionDocument> findByContentId(int contentId);

}

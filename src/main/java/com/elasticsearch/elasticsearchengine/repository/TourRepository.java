package com.elasticsearch.elasticsearchengine.repository;

import com.elasticsearch.elasticsearchengine.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TourRepository extends JpaRepository <Tour, Integer>, TourQueryRepository {

    Page<Tour> findByContentTypeId(String contentTypeId, Pageable pageable);

    //자연용
    Page<Tour> findByContentTypeIdAndCategory(String contentTypeId, String category, Pageable pageable);



}

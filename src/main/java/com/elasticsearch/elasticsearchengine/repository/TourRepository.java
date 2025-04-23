package com.elasticsearch.elasticsearchengine.repository;

import com.elasticsearch.elasticsearchengine.domain.TouristAttreaction;
import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TourRepository extends JpaRepository <TouristAttreaction, Integer>, TourQueryRepository {

    Page<TourListDto> findByContentTypeId(String contentTypeId, Pageable pageable);
}

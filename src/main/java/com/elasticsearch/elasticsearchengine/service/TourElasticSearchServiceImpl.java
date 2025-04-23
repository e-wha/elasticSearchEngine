package com.elasticsearch.elasticsearchengine.service;

import com.elasticsearch.elasticsearchengine.domain.TourAttractionDocument;
import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import com.elasticsearch.elasticsearchengine.repository.TourAttractionRepository;
import com.elasticsearch.elasticsearchengine.vo.TourListDetailResponseVo;
import com.elasticsearch.elasticsearchengine.vo.TourListResponseVo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TourElasticSearchServiceImpl implements TourElasticSearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final TourAttractionRepository tourAttractionRepository;

    @Override
    public TourListResponseVo searchTourWithPasing(String contentTypeId, String title, String sigunguCode, String category, List<String> tags, int sort, Pageable pageable) {

        Criteria criteria = new Criteria("content_type_id").is(contentTypeId);

        if (title != null && !title.isEmpty()) {
            criteria = criteria.and(new Criteria("title").contains(title));
        }
        if (sigunguCode != null && !sigunguCode.isEmpty()) {
            criteria = criteria.and(new Criteria("sigungu_code").is(sigunguCode));
        }
        if (category != null && !category.isEmpty()) {
            criteria = criteria.and(new Criteria("category").is(category));
        }
        if (tags != null && !tags.isEmpty()) {
            // 하나라도 포함되는 조건
            Criteria tagsCriteria = new Criteria("tags");
            for (String tag : tags) {
                tagsCriteria = tagsCriteria.or(new Criteria("tags").contains(tag));
            }
            criteria = criteria.and(tagsCriteria);
        }

        CriteriaQuery query = new CriteriaQuery(criteria, pageable);

        // 정렬 적용
        if (sort == 1) {
            query.addSort(Sort.by(Sort.Order.asc("title_sort")));
        } else {
            query.addSort(Sort.by(Sort.Order.desc("created_time")));
        }

        SearchHits<TourAttractionDocument> hits = elasticsearchOperations.search(query, TourAttractionDocument.class);

        int totalCount = (int) hits.getTotalHits();
        int currentPage = pageable.getPageNumber();
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());

        List<TourListDto> tourList = hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(doc -> TourListDto.builder()
                        .contentId(doc.getContentId())
                        .title(doc.getTitle())
                        .thumbnail(doc.getThumbnail())
                        .tags(String.join(",", doc.getTags()))
                        .build())
                .toList();

        return TourListResponseVo.builder()
                .totalCount(totalCount)
                .currentPage(currentPage)
                .totalPages(totalPages)
                .tourListDto(tourList)
                .build();
    }

    @Override
    public TourListDetailResponseVo findByContentId(int contentId) {
        TourAttractionDocument TourDetail = tourAttractionRepository.findByContentId(contentId).orElseThrow(RuntimeException::new);

        return TourListDetailResponseVo.builder()
                .contentId(TourDetail.getContentId())
                .title(TourDetail.getTitle())
                .tags(String.join(",", TourDetail.getTags()))
                .addr(TourDetail.getAddr())
                .thumbnail(TourDetail.getThumbnail())
                .createdTime(LocalDate.from(TourDetail.getCreatedTime()))
                .modifiedTime(LocalDate.from(TourDetail.getModifiedTime()))
                .overview(TourDetail.getOverview())
                .homepage(TourDetail.getHomepage())
                .infocenter(TourDetail.getInfocenter())
                .opendate(TourDetail.getOpendate())
                .restdate(TourDetail.getRestdate())
                .expguide(TourDetail.getExpguide())
                .expagerange(TourDetail.getExpagerange())
                .accomcount(TourDetail.getAccomcount())
                .useseason(TourDetail.getUseseason())
                .usetime(TourDetail.getUsetime())
                .parking(TourDetail.getParking())
                .mapx(TourDetail.getMapx())
                .mapy(TourDetail.getMapy())
                .build();
    }


}

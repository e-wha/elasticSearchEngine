package com.elasticsearch.elasticsearchengine.controller;

import com.elasticsearch.elasticsearchengine.common.CommonResponse;
import com.elasticsearch.elasticsearchengine.dto.TourListRequestDto;
import com.elasticsearch.elasticsearchengine.service.TourElasticSearchService;
import com.elasticsearch.elasticsearchengine.vo.TourListDetailResponseVo;
import com.elasticsearch.elasticsearchengine.vo.TourListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("tour_E")
@Tag(name = "tour_elasticSearch_controller", description = "관광정보관리시스템 ver.elasticSearch")
public class TourElasticSearchController {

    private final TourElasticSearchService tourElasticSearchService;

    @GetMapping(value = "/find")
    @Operation(summary = "관광 정보 조회", description = "페이지 번호, 관광 타입 번호, 시군구코드, 서브 카테고리, 태그를 기준으로 관광 정보를 조회합니다.")
    public CommonResponse<TourListResponseVo> searchTourWithPasing(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "검색 조건 및 페이징 정보")
            TourListRequestDto tourListRequestDto
    ) {
        Pageable pageable = PageRequest.of(tourListRequestDto.getPage(), tourListRequestDto.getSize());

        List<String> tagList = tourListRequestDto.getTags() != null
                ? Arrays.stream(tourListRequestDto.getTags().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList()
                : List.of();

        TourListResponseVo tourListResponseVo = tourElasticSearchService.searchTourWithPasing(
                tourListRequestDto.getContentTypeId(),
                tourListRequestDto.getTitle(),
                tourListRequestDto.getSigunguCode(),
                tourListRequestDto.getCategory(),
                tagList,
                tourListRequestDto.getSort(),
                pageable);

        return CommonResponse.success("관광 정보", tourListResponseVo);
    }

    @GetMapping(value = "/find/detail")
    @Operation(summary = "관광 정보 상세 조회", description = "콘텐츠 고유 번호를 기준으로 관광 정보를 상세 조회합니다.")
    public CommonResponse<TourListDetailResponseVo> findByContentId(
            @Parameter(description = "콘텐츠 고유 번호")
            int contentId
    ) {
        TourListDetailResponseVo tourListDetailResponseVo = tourElasticSearchService.findByContentId(contentId);

        return CommonResponse.success("관광 정보", tourListDetailResponseVo);
    }
}

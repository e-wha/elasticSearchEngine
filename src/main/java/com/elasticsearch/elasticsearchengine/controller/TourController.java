package com.elasticsearch.elasticsearchengine.controller;

import com.elasticsearch.elasticsearchengine.common.CommonResponse;
import com.elasticsearch.elasticsearchengine.dto.TourListRequestDto;
import com.elasticsearch.elasticsearchengine.service.TourService;
import com.elasticsearch.elasticsearchengine.vo.ExcelSaveTourListResponseVo;
import com.elasticsearch.elasticsearchengine.vo.TourListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.elasticsearch.elasticsearchengine.common.BaseResponseStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("tour")
@Tag(name = "tour_controller", description = "관광정보관리시스템")
public class TourController {

    private final TourService tourService;

    @PostMapping(value = "/save", consumes = "multipart/form-data")
    @Operation(summary = "관광 정보 등록 (엑셀 삽입)", description = "엑셀에 등록된 관광 정보를 등록합니다.")
    public CommonResponse<ExcelSaveTourListResponseVo> excelSaveTourList(
            @RequestPart("file")
            @io.swagger.v3.oas.annotations.Parameter(
                    description = "관광 정보가 삽입된 엑셀 파일",
                    required = true
            ) MultipartFile file
    ) {
        try {
            ExcelSaveTourListResponseVo responseVo = tourService.excelSaveTourList(file);
            return CommonResponse.success("관광정보가 성공적으로 등록되었습니다.", responseVo);
        } catch (IOException e) {
            return CommonResponse.fail(INTERNAL_SERVER_ERROR, "파일 처리 중 오류가 발생했습니다.");
        }
    }


    @GetMapping(value = "/find")
    @Operation(summary = "관광 정보 조회", description = "페이지 번호, 관광 타입 번호, 시군구코드, 서브 카테고리, 태그를 기준으로 관광 정보를 조회합니다.")
    public CommonResponse<TourListResponseVo> findTourList(
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

        TourListResponseVo tourListResponseVo = tourService.findByMultiCode(
                tourListRequestDto.getContentTypeId(),
                tourListRequestDto.getSigunguCode(),
                tourListRequestDto.getCategory(),
                tagList,
                pageable);

        return CommonResponse.success("관광 정보", tourListResponseVo);
    }
}

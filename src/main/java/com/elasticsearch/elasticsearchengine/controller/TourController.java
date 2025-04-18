package com.elasticsearch.elasticsearchengine.controller;

import com.elasticsearch.elasticsearchengine.common.CommonResponse;
import com.elasticsearch.elasticsearchengine.dto.ExcelSaveTourListResponseDto;
import com.elasticsearch.elasticsearchengine.dto.TourListResponseDto;
import com.elasticsearch.elasticsearchengine.service.TourService;
import com.elasticsearch.elasticsearchengine.vo.ExcelSaveTourListResponseVo;
import com.elasticsearch.elasticsearchengine.vo.TourListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
            ExcelSaveTourListResponseDto responseDto = tourService.excelSaveTourList(file);
            ExcelSaveTourListResponseVo responseVo = ExcelSaveTourListResponseVo.dtoToVo(responseDto);
            return CommonResponse.success("관광정보가 성공적으로 등록되었습니다.", responseVo);
        } catch (IOException e) {
            return CommonResponse.fail(INTERNAL_SERVER_ERROR, "파일 처리 중 오류가 발생했습니다.");
        }
    }


    @GetMapping(value = "/find")
    @Operation(summary = "관광 정보 조회", description = "페이지 번호, 관광 타입 번호, 시군구코드, 서브 카테고리, 태그를 기준으로 관광 정보를 조회합니다.")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 기본 값 : 1"),
            @Parameter(name = "contentTypeId", description = "관광 타입 번호"),
            @Parameter(name = "sigunguCode", description = "시군구코드"),
            @Parameter(name = "sideCategory", description = "서브 카테고리"),
            @Parameter(name = "tags", description = "태그"),
    })
    public CommonResponse<TourListResponseVo> findTourList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "contentTypeId") String contentTypeId,
            @RequestParam(name = "sigunguCode", required = false) String sigunguCode,
            @RequestParam(name = "sideCategory", required = false) String sideCategory,
            @RequestParam(name = "tags", required = false) List<String> tags
    ) {
        Page<TourListResponseDto> TourListPage = tourService.findByMultiCode(page, contentTypeId, sigunguCode, sideCategory, tags);

        if (TourListPage.isEmpty()) {
            return CommonResponse.success("관광 정보", TourListResponseVo.builder()
                    .totalList(0)
                    .currentPage(page)
                    .totalPages(0)
                    .tourListDto(null)
                    .build());
        }

        return CommonResponse.success("관광 정보",
                TourListResponseVo.dtoToVo(TourListPage.getContent().get(0)));
    }
}

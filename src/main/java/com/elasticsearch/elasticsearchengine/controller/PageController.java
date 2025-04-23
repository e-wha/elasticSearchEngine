package com.elasticsearch.elasticsearchengine.controller;

import com.elasticsearch.elasticsearchengine.dto.TourListRequestDto;
import com.elasticsearch.elasticsearchengine.service.TourElasticSearchService;
import com.elasticsearch.elasticsearchengine.vo.TourListResponseVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/tour")
public class PageController {

    private final TourElasticSearchService tourElasticSearchService;
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/main.do")
    public String findTourListByCategory(TourListRequestDto tourListRequestDto, Model model) {

        List<String> areaList = Arrays.asList("강서구", "금정구", "기장군", "남구", "동구", "동래구", "부산진구",
                "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구");

        List<String> categoryList = Arrays.asList("문화", "자연", "체험");

        if (tourListRequestDto.getContentTypeId() == null) {
            tourListRequestDto.setContentTypeId("12");
        }
        if (tourListRequestDto.getSort() == null) {
            tourListRequestDto.setSort(0);
        }
        if (tourListRequestDto.getPage() == null) {
            tourListRequestDto.setPage(0);
        }
        if (tourListRequestDto.getSize() == null) {
            tourListRequestDto.setSize(12);
        }
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
                pageable
        );
        logger.info("tourListPage: {}", tourListRequestDto);

        int currentPage = tourListResponseVo.getCurrentPage();
        int totalPages = tourListResponseVo.getTotalPages() - 1;
        int totalCount = tourListResponseVo.getTotalCount();

        if (tourListResponseVo.getTotalCount() != 0) {

            int pageCount = 5; // 페이지네이션에 보여줄 버튼 개수
            int startPage = ((currentPage - 1) / pageCount) * pageCount + 1;
            int endPage = Math.min(startPage + pageCount - 1, totalPages);
            boolean hasNext = currentPage < totalPages;
            boolean hasPrev = currentPage > 1;

            model.addAttribute("tourList", tourListResponseVo.getTourListDto());
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", totalPages);


            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("hasPrev", hasPrev);
            model.addAttribute("hasNext", hasNext);

        } else {
            model.addAttribute("message", "조회된 관광 정보가 없습니다.");
            model.addAttribute("tourList", new ArrayList<>());
            model.addAttribute("totalList", 0);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", 1);
            model.addAttribute("startPage", 1);
            model.addAttribute("endPage", 1);
        }

        model.addAttribute("areaList", areaList);
        model.addAttribute("categoryList", categoryList);

        return "pages/main";
    }

    @GetMapping("/tourDetail.do")
    public String detailTourList(

    ) {
        return "pages/detail";
    }

    @GetMapping("/myBusan.do")
    public String myBusanTourList(

    ) {
        return "pages/myBusan";
    }
}
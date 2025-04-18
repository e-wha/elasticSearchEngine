package com.elasticsearch.elasticsearchengine.service;

import com.elasticsearch.elasticsearchengine.domain.Tour;
import com.elasticsearch.elasticsearchengine.dto.ExcelSaveTourListResponseDto;
import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import com.elasticsearch.elasticsearchengine.dto.TourListResponseDto;
import com.elasticsearch.elasticsearchengine.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;


    @Override
    @Transactional
    public ExcelSaveTourListResponseDto excelSaveTourList(MultipartFile file) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        List<Tour> TourToSave = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int failedCount = 0;


        try (InputStream inputStream = file.getInputStream(); Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String contentId = getCellValue(row.getCell(0));
                String contentTypeId = getCellValue(row.getCell(1));
                String title = getCellValue(row.getCell(2));
                String addr = getCellValue(row.getCell(3));
                String zipCode = getCellValue(row.getCell(4));
                String areaCode = getCellValue(row.getCell(5));
                String sigunguCode = getCellValue(row.getCell(6));
                String category = getCellValue(row.getCell(7));
                String sideCategory = getCellValue(row.getCell(8));
                String tags = getCellValue(row.getCell(9));
                String thumbnail = getCellValue(row.getCell(10));
                String mapx = getCellValue(row.getCell(11));
                String mapy = getCellValue(row.getCell(12));
                String createdTime = getCellValue(row.getCell(13));
                String modifiedTime = getCellValue(row.getCell(14));

                TourToSave.add(Tour.builder()
                                .contentId(Integer.parseInt(contentId))
                                .contentTypeId(contentTypeId)
                                .title(title)
                                .addr(addr)
                                .zipCode(zipCode)
                                .areaCode(areaCode)
                                .sigunguCode(sigunguCode)
                                .category(category)
                                .sideCategory(sideCategory)
                                .tags(tags)
                                .thumbnail(thumbnail)
                                .mapx(Double.parseDouble(mapx))
                                .mapy(Double.parseDouble(mapy))
                                .createdTime(LocalDate.parse(createdTime, formatter))
                                .modifiedTime(LocalDate.parse(modifiedTime, formatter))
                                .build()
                );
                successCount++;
            }
        }

        tourRepository.saveAll(TourToSave);

        return ExcelSaveTourListResponseDto.builder()
                .successCount(successCount)
                .failedCount(failedCount)
                .errors(errors)
                .build();
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    @Override
    public Page<TourListResponseDto> findBycontentTypeId(int page, String contentTypeId) {
        PageRequest pageable = PageRequest.of(page, 12);
        Page<Tour> TourPage;

        TourPage = tourRepository.findByContentTypeId(contentTypeId, pageable);

        if (TourPage.isEmpty()) {
            return Page.empty();
        }

        return TourPage.map(Tour -> TourListResponseDto.builder()
                .totalList((int)TourPage.getTotalElements())
                .currentPage(TourPage.getNumber() + 1)
                .totalPages(TourPage.getTotalPages())
                .build());
    }

    @Override
    public Page<TourListResponseDto> findByMultiCode(int page, String contentTypeId, String sigunguCode, String sideCategory, List<String> tags) {
        PageRequest pageable = PageRequest.of(page, 12);
        Page<TourListDto> TourPage;

        TourPage = tourRepository.findByMultiCode(contentTypeId, sigunguCode, sideCategory ,tags ,pageable);

        if (TourPage.isEmpty()) {
            return Page.empty();
        }

        TourListResponseDto responseDto = TourListResponseDto.builder()
                .totalList((int) TourPage.getTotalElements())
                .currentPage(TourPage.getNumber() + 1)
                .totalPages(TourPage.getTotalPages())
                .tourListDto(TourPage.toList())
                .build();

        return new PageImpl<>(List.of(responseDto), pageable, 1);
    }
}
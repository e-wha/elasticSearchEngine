package com.elasticsearch.elasticsearchengine.service;

import com.elasticsearch.elasticsearchengine.domain.TouristAttreaction;
import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import com.elasticsearch.elasticsearchengine.repository.TourRepository;
import com.elasticsearch.elasticsearchengine.vo.ExcelSaveTourListResponseVo;
import com.elasticsearch.elasticsearchengine.vo.TourListResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ExcelSaveTourListResponseVo excelSaveTourList(MultipartFile file) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        List<TouristAttreaction> TouristAttreactionToSave = new ArrayList<>();
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
                String sigunguCode = getCellValue(row.getCell(3));
                String category = getCellValue(row.getCell(4));
                String tags = getCellValue(row.getCell(5));
                String addr = getCellValue(row.getCell(6));
                String thumbnail = getCellValue(row.getCell(7));
                String createdTime = getCellValue(row.getCell(8));
                String modifiedTime = getCellValue(row.getCell(9));
                String overview = getCellValue(row.getCell(10));
                String homepage = getCellValue(row.getCell(11));
                String infocenter = getCellValue(row.getCell(12));
                String opendate = getCellValue(row.getCell(13));
                String restdate = getCellValue(row.getCell(14));
                String expguide = getCellValue(row.getCell(15));
                String expagerange = getCellValue(row.getCell(16));
                String accomcount = getCellValue(row.getCell(17));
                String useseason = getCellValue(row.getCell(18));
                String usetime = getCellValue(row.getCell(19));
                String parking = getCellValue(row.getCell(20));
                String mapx = getCellValue(row.getCell(21));
                String mapy = getCellValue(row.getCell(22));
                String areaCode = getCellValue(row.getCell(23));

                TouristAttreactionToSave.add(TouristAttreaction.builder()
                                .contentId(Integer.parseInt(contentId))
                                .contentTypeId(contentTypeId)
                                .title(title)
                                .sigunguCode(sigunguCode)
                                .category(category)
                                .tags(tags)
                                .addr(addr)
                                .thumbnail(thumbnail)
                                .createdTime(LocalDate.parse(createdTime, formatter))
                                .modifiedTime(LocalDate.parse(modifiedTime, formatter))
                                .overview(overview)
                                .homepage(homepage)
                                .infocenter(infocenter)
                                .opendate(opendate)
                                .restdate(restdate)
                                .expguide(expguide)
                                .expagerange(expagerange)
                                .accomcount(accomcount)
                                .useseason(useseason)
                                .usetime(usetime)
                                .parking(parking)
                                .mapx(Double.parseDouble(mapx))
                                .mapy(Double.parseDouble(mapy))
                                .areaCode(areaCode)
                                .build()
                );
                successCount++;
            }
        }

        tourRepository.saveAll(TouristAttreactionToSave);

        return ExcelSaveTourListResponseVo.builder()
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
    public TourListResponseVo findBycontentTypeId(String contentTypeId, Pageable pageable) {
        Page<TourListDto> TourListPage = tourRepository.findByContentTypeId(contentTypeId, pageable);

        return TourListResponseVo.builder()
                .totalCount((int) TourListPage.getTotalElements())
                .currentPage(TourListPage.getNumber() + 1)
                .totalPages(TourListPage.getTotalPages())
                .tourListDto(TourListPage.toList())
                .build();
    }

    @Override
    public TourListResponseVo findByMultiCode(String contentTypeId, String sigunguCode, String category, List<String> tags, Pageable pageable) {
        Page<TourListDto> TourListPage = tourRepository.findByMultiCode(contentTypeId, sigunguCode, category ,tags ,pageable);

        return TourListResponseVo.builder()
                .totalCount((int) TourListPage.getTotalElements())
                .currentPage(TourListPage.getNumber() + 1)
                .totalPages(TourListPage.getTotalPages())
                .tourListDto(TourListPage.toList())
                .build();
    }
}
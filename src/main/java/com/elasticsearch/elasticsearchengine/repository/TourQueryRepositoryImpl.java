package com.elasticsearch.elasticsearchengine.repository;

import com.elasticsearch.elasticsearchengine.domain.QTour;
import com.elasticsearch.elasticsearchengine.domain.Tour;
import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.ast.Projection;

import java.util.List;


@RequiredArgsConstructor
public class TourQueryRepositoryImpl implements TourQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TourListDto> findByMultiCode(String contentTypeId, String sigunguCode, String sideCategory, List<String> tags, Pageable pageable) {
        QTour tour = QTour.tour;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(tour.contentTypeId.eq(contentTypeId));

        if (sigunguCode != null && !sigunguCode.isBlank()) {
            builder.and(tour.sigunguCode.eq(sigunguCode));
        }

        if (sideCategory != null && !sideCategory.isBlank()) {
            builder.and(tour.sideCategory.eq(sideCategory));
        }

        if (tags != null && !tags.isEmpty()) {
            BooleanBuilder tagBuilder = new BooleanBuilder();
            for (String tag : tags) {
                tagBuilder.or(tour.tags.contains(tag)); // tags LIKE %tag%
            }
            builder.and(tagBuilder);
        }

        List<TourListDto> content = queryFactory
                .select(Projections.constructor(
                        TourListDto.class,
                        tour.contentId,
                        tour.title,
                        tour.thumbnail,
                        tour.tags
                ))
                .from(tour)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(tour.count())
                .from(tour)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    @Override
    public Page<TourListDto> findMultiCodeNature(String contentTypeId, String category, String sigunguCode, String sideCategory, List<String> tags, Pageable pageable) {
        QTour tour = QTour.tour;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(tour.contentTypeId.eq(contentTypeId));
        builder.and(tour.category.eq(category));

        if (sigunguCode != null && !sigunguCode.isBlank()) {
            builder.and(tour.sigunguCode.eq(sigunguCode));
        }

        if (sideCategory != null && !sideCategory.isBlank()) {
            builder.and(tour.sideCategory.eq(sideCategory));
        }

        if (tags != null && !tags.isEmpty()) {
            BooleanBuilder tagBuilder = new BooleanBuilder();
            for (String tag : tags) {
                tagBuilder.or(tour.tags.contains(tag)); // tags LIKE %tag%
            }
            builder.and(tagBuilder);
        }

        List<TourListDto> content = queryFactory
                .select(Projections.constructor(
                        TourListDto.class,
                        tour.contentId.stringValue(),
                        tour.title,
                        tour.thumbnail,
                        Expressions.stringTemplate("function('string_to_array', {0}, ',')", tour.tags)
                ))
                .from(tour)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(tour.count())
                .from(tour)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}

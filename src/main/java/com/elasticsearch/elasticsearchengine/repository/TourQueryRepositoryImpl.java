package com.elasticsearch.elasticsearchengine.repository;

import com.elasticsearch.elasticsearchengine.domain.QTouristAttreaction;
import com.elasticsearch.elasticsearchengine.dto.TourListDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


@RequiredArgsConstructor
public class TourQueryRepositoryImpl implements TourQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TourListDto> findByMultiCode(String contentTypeId, String sigunguCode, String category, List<String> tags, Pageable pageable) {
        QTouristAttreaction touristAttreaction = QTouristAttreaction.touristAttreaction;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(touristAttreaction.contentTypeId.eq(contentTypeId));

        if (sigunguCode != null && !sigunguCode.isBlank()) {
            builder.and(touristAttreaction.sigunguCode.eq(sigunguCode));
        }

        if (category != null && !category.isBlank()) {
            builder.and(touristAttreaction.category.eq(category));
        }

        if (tags != null && !tags.isEmpty()) {
            BooleanBuilder tagBuilder = new BooleanBuilder();
            for (String tag : tags) {
                tagBuilder.or(touristAttreaction.tags.contains(tag)); // tags LIKE %tag%
            }
            builder.and(tagBuilder);
        }

        List<TourListDto> content = queryFactory
                .select(Projections.constructor(
                        TourListDto.class,
                        touristAttreaction.contentId,
                        touristAttreaction.title,
                        touristAttreaction.thumbnail,
                        touristAttreaction.tags
                ))
                .from(touristAttreaction)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(touristAttreaction.count())
                .from(touristAttreaction)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}

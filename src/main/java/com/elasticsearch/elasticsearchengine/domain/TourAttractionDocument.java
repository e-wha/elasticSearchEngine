package com.elasticsearch.elasticsearchengine.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "tourist_attraction_index")
@Data
public class TourAttractionDocument {

    @Id
    @Field(name = "content_id", type = FieldType.Integer)
    private int contentId;

    @Field(name = "content_type_id")
    private String contentTypeId;

    @Field(name = "title")
    private String title;

    @Field(name = "sigungu_code")
    private String sigunguCode;

    private String category;

    private List<String> tags;

    @Field(name = "title_sort", type = FieldType.Keyword)
    private String title_sort;

    private String addr;

    private String thumbnail;

    @Field(name = "created_time")
    private LocalDateTime createdTime;

    @Field(name = "modified_time")
    private LocalDateTime modifiedTime;

    private String overview;

    private String homepage;

    private String infocenter;

    private String opendate;

    private String restdate;

    private String expguide;

    private String expagerange;

    private String accomcount;

    private String useseason;

    private String usetime;

    private String parking;

    private double mapx;

    private double mapy;

    @Field(name = "area_code")
    private String areaCode;
}

package com.elasticsearch.elasticsearchengine.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TourId;

    @Column(nullable = false, unique = true)
    private int contentId;

    private String contentTypeId;

    private String title;

    private String addr;

    private String zipCode;

    private String areaCode;

    private String sigunguCode;

    private String category;

    private String sideCategory;

    private String tags;

    private String thumbnail;

    private double mapx;

    private double mapy;

    private LocalDate createdTime;

    private LocalDate modifiedTime;
}

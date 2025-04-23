package com.elasticsearch.elasticsearchengine.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tourist_attraction")
public class TouristAttreaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TourId;

    @Column(nullable = false, unique = true)
    private int contentId;

    private String contentTypeId;

    private String title;

    private String sigunguCode;

    private String category;

    private String tags;

    private String addr;

    private String thumbnail;

    private LocalDate createdTime;

    private LocalDate modifiedTime;

    @Column(columnDefinition = "text")
    private String overview;

    @Column(columnDefinition = "text")
    private String homepage;

    private String infocenter;

    private String opendate;

    private String restdate;

    @Column(columnDefinition = "text")
    private String expguide;

    private String expagerange;

    private String accomcount;

    private String useseason;

    @Column(columnDefinition = "text")
    private String usetime;

    private String parking;

    private double mapx;

    private double mapy;

    private String areaCode;
}

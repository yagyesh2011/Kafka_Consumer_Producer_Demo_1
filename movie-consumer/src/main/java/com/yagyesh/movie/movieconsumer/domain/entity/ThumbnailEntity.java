package com.yagyesh.movie.movieconsumer.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "thumbnails")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThumbnailEntity {
    @Id
    private String url;
    private Integer width;
    private Integer height;
}

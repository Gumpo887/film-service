package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.EditionEntity;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.FilmEntity;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.PictureEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class EditionEntityMapperImpl implements EditionEntityMapper {

    private FilmEntityMapper filmEntityMapper;

    private Picture toDomain(PictureEntity entity) {
        return Picture.of(
                PictureId.of(entity.getId()),
                Url.of(entity.getUrl()),
                entity.getUploadedAt()
        );
    }

    private PictureEntity toEntity(Picture picture) {
        PictureEntity entity = new PictureEntity();
        entity.setId(picture.id().value());
        entity.setUrl(picture.url().value());
        entity.setUploadedAt(picture.uploadedAt());
        return entity;
    }

    private List<Picture> toDomainPictures(List<PictureEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    private List<PictureEntity> toEntityPictures(List<Picture> pictures) {
        return pictures.stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public Edition toDomain(EditionEntity entity) {
        if (entity == null) return null;
        Film film = filmEntityMapper.toDomain(entity.getFilm());
        return Edition.of(
                EditionId.of(entity.getId()),
                film,
                BarCode.of(entity.getBarCode()),
                Country.of(entity.getCountry()),
                entity.getFormat(), // 1:1 mapping from enum in VO and persistence
                entity.getReleaseYear(),
                entity.getPackagingType(), // 1:1
                Notes.of(entity.getNotes()),
                this.toDomainPictures(entity.getPictures())
        );
    }

    @Override
    public EditionEntity toEntity(Edition edition) {
        if (edition == null) return null;
        FilmEntity filmEntity = filmEntityMapper.toEntity(edition.film());
        List<PictureEntity> pictureEntities = this.toEntityPictures(edition.pictures());
        EditionEntity entity = new EditionEntity();
        entity.setId(edition.editionId().value());
        entity.setFilm(filmEntity);
        entity.setBarCode(edition.barCode().value());
        entity.setCountry(edition.country().value());
        entity.setFormat(edition.format());
        entity.setReleaseYear(edition.releaseYear());
        entity.setPackagingType(edition.packagingType());
        entity.setNotes(edition.notes().value());
        entity.setPictures(pictureEntities);
        return entity;
    }
}

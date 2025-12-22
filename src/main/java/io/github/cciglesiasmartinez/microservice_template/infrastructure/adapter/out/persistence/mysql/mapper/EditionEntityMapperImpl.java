package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.EditionEntity;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.FilmEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EditionEntityMapperImpl implements EditionEntityMapper {

    private FilmEntityMapper filmEntityMapper;

    @Override
    public Edition toDomain(EditionEntity entity) {
        if (entity == null) return null;
        Film film = filmEntityMapper.toDomain(entity.getFilm());
        return Edition.of(
                EditionId.of(entity.getId()),
                film,
                BarCode.of(entity.getBarCode()),
                Country.of(entity.getCountry()),
                Format.valueOf(entity.getFormat()), // 1:1 mapping from enum in VO and persistence
                entity.getReleaseYear(),
                PackagingType.valueOf(entity.getPackagingType()),
                Notes.of(entity.getNotes())
        );
    }

    @Override
    public EditionEntity toEntity(Edition edition) {
        if (edition == null) return null;
        FilmEntity filmEntity = filmEntityMapper.toEntity(edition.film());
        EditionEntity entity = new EditionEntity();
        entity.setId(edition.editionId().value());
        entity.setFilm(filmEntity);
        entity.setBarCode(edition.barCode().value());
        entity.setCountry(edition.country().value());
        entity.setFormat(edition.format().name());
        entity.setReleaseYear(edition.releaseYear());
        entity.setPackagingType(edition.packagingType().name());
        entity.setNotes(edition.notes().value());
        return entity;
    }
}

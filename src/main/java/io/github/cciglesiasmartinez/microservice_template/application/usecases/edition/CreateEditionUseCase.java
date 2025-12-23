package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongFilmIdException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.CreateEditionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * Create edition use case.
 *
 */
@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CreateEditionUseCase {

    private final EditionRepository editionRepository;
    private final FilmRepository filmRepository;

    /**
     * Executes create edition use case.
     *
     * @param request
     * @return
     */
    public Envelope<CreateEditionResponse> execute(CreateEditionRequest request) {
        FilmId filmId = FilmId.of(request.getFilmId());
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new WrongFilmIdException("Film ID not found"));
        BarCode barCode = BarCode.of(request.getBarCode());
        Country country = Country.of(request.getCountry());
        Format format = Format.valueOf(request.getFormat());
        Year releaseYear = Year.of(request.getReleaseYear());
        PackagingType packagingType = PackagingType.valueOf(request.getPackagingType());
        Notes notes = Notes.of(request.getNotes());
        List<Picture> pictures = new ArrayList<>();
        Edition edition = Edition.create(film, barCode, country, format, releaseYear, packagingType, notes, pictures);
        edition = editionRepository.save(edition); // TODO: Careful with this.
        CreateEditionResponse data = new CreateEditionResponse(edition.editionId().value(), true);
        Meta meta = new Meta();
        log.info("Edition {} successfully created.", edition.editionId().value());
        return new Envelope<>(data, meta);
    }

}

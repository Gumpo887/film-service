package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongFilmIdException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.UpdateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.UpdateEditionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
@AllArgsConstructor
@Slf4j
public class UpdateEditionUseCase {

    private EditionRepository editionRepository;

    /**
     * Execute update edition use case.
     *
     * @param request
     * @return
     */
    public Envelope<UpdateEditionResponse> execute(UpdateEditionRequest request) {
        Edition existing = editionRepository.findById(EditionId.of(request.getId()))
                .orElseThrow(() -> new WrongFilmIdException("Film ID not found"));
        BarCode barCode = request.getBarCode() != null ? BarCode.of(request.getBarCode()) : existing.barCode();
        Country country = request.getCountry() != null ? Country.of(request.getCountry()) : existing.country();
        Format format = request.getFormat() != null ? Format.valueOf(request.getFormat()) : existing.format();
        Year releaseYear = request.getReleaseYear() != null ? request.getReleaseYear() : existing.releaseYear();
        PackagingType packagingType = request.getPackagingType() != null
                ? PackagingType.valueOf(request.getPackagingType()) : existing.packagingType();
        Notes notes = request.getNotes() != null ?  Notes.of(request.getNotes()) : existing.notes();
        Edition updated = Edition.of(
                existing.editionId(),
                existing.film(),
                barCode,
                country,
                format,
                releaseYear,
                packagingType,
                notes,
                existing.pictures());
        updated = editionRepository.update(updated); // TODO: Review this, consider a void method instead.
        UpdateEditionResponse data = new UpdateEditionResponse(updated.editionId().value(), true);
        return new Envelope<>(data, new Meta());
    }

}

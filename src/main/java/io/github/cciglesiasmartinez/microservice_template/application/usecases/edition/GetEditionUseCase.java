package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.GetEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.GetPictureResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Get edition use case.
 */
@Service
@AllArgsConstructor
@Slf4j
public class GetEditionUseCase {

    private final EditionRepository editionRepository;

    /**
     * Executes get edition use case.
     *
     * @param id
     * @return
     */
    public Envelope<GetEditionResponse> execute(String id) {
        EditionId editionId = EditionId.of(id);
        Edition edition = editionRepository.findById(editionId)
                .orElseThrow(() -> {
                    log.warn("Edition ID does not exist.");
                    return new IllegalArgumentException("Edition ID does not exist.");
                });
        List<GetPictureResponse> pictureList = edition.pictures().stream()
                .map((picture) -> {
                            return new GetPictureResponse(
                                    picture.id().value(),
                                    picture.url().value(),
                                    picture.uploadedAt()
                            );
                        })
                .toList();
        GetEditionResponse data = new GetEditionResponse(
                edition.editionId().value(),
                edition.film().itemId().value(),
                edition.barCode().value(),
                edition.country().value(),
                edition.format().name(),
                edition.releaseYear(),
                edition.packagingType().name(),
                true,
                edition.notes().value(),
                pictureList
        );
        Meta meta = new Meta();
        return new Envelope<>(data, meta);
    }

}

package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.DeletePictureResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class DeletePictureUseCase {

    private EditionRepository editionRepository;
    private StorageService storageService;

    public Envelope<DeletePictureResponse> execute(String editionId, String pictureId) {
        EditionId id = EditionId.of(editionId);
        Edition edition = editionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Picture ID not found"));
        Picture pictureToDelete = null;
        for (Picture p: edition.pictures()) {
            if (p.id().value().equals(pictureId)) {
                pictureToDelete = p;
                edition.pictures().remove(p);
                storageService.delete(pictureToDelete.url().value());
                break;
            }
        }
        if (pictureToDelete == null) { throw new IllegalArgumentException("Picture NOT found!"); }
        Edition updated = editionRepository.update(edition);
        DeletePictureResponse data = new DeletePictureResponse(updated.editionId().value(), true);
        log.info("Picture deleted successfully.");
        return new Envelope<>(data, new Meta());
    }

}

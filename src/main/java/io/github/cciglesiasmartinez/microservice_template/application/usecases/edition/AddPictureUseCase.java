package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.CreatePictureResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage.StorageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Add picture use case.
 */
@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AddPictureUseCase {

    private EditionRepository editionRepository;
    private StorageService storageService;

    /**
     * Executes add picture use case.
     *
     * @param id
     * @param file
     * @return
     */
    public Envelope<CreatePictureResponse> execute(String id, MultipartFile file) {
        EditionId editionId = EditionId.of(id);
        Edition edition = editionRepository.findById(editionId)
                .orElseThrow(() -> new RuntimeException("ID not found."));
        String fileNameExtension = storageService.getFileExtension(file);
        Picture picture = edition.addPicture(fileNameExtension);
        Edition updated = editionRepository.update(edition);
        storageService.save(picture.id().value(), file);
        CreatePictureResponse data = new CreatePictureResponse(picture.id().value(), true);
        log.info("Picture added successfully.");
        return new Envelope<>(data, new Meta());
    }

}

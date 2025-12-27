package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.Url;
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

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AddPictureUseCase {

    private EditionRepository editionRepository;
    private StorageService storageService;

    public Envelope<CreatePictureResponse> execute(String id, MultipartFile file, String type) {
        EditionId editionId = EditionId.of(id);
        String fileName = storageService.save(editionId.value(), file, type);
        Url pictureUrl = Url.of(fileName);
        Edition edition = editionRepository.findById(editionId)
                .orElseThrow(() -> new RuntimeException("ID not found."));
        Picture picture = edition.addPicture(pictureUrl);
        Edition updated = editionRepository.update(edition);
        System.out.println(edition.pictures().getFirst());
        CreatePictureResponse data = new CreatePictureResponse(picture.id().value(), true);
        log.info("Picture added successfully.");
        return new Envelope<>(data, new Meta());
    }

}

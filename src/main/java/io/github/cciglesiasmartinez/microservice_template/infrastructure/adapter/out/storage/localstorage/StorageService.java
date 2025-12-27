package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String save(String editionId, MultipartFile file, String type);

}

package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void save(String fileName, MultipartFile file);
    void delete(String fileName);
    String getFileExtension(MultipartFile file);

}

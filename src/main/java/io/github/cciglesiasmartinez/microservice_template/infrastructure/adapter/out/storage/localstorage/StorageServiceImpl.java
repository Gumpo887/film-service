package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class StorageServiceImpl implements StorageService {

    private final Path root = Paths.get("static", "images");

    @Override
    public String save(String id, MultipartFile file, String type) {
        System.out.println("Executing save in StorageService in dir: " + root);
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            Path target = root.resolve(id + "." + extension);
            Files.copy(file.getInputStream(), target);
            return "/images/" + target.getFileName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

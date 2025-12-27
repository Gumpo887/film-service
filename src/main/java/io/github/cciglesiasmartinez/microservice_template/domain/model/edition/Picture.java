package io.github.cciglesiasmartinez.microservice_template.domain.model.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.PictureId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.Url;

import java.time.LocalDateTime;

/**
 * Represents a Picture within the system.
 */
public class Picture {

    private PictureId id;
    private Edition edition;
    private Url url;
    private LocalDateTime uploadedAt;

    private Picture(
            PictureId id,
            Url url,
            Edition edition,
            LocalDateTime uploadedAt
    ) {
        this.id = id;
        this.url = url;
        this.edition = edition;
        this.uploadedAt = uploadedAt;
    }

    /**
     * Factory method intended to create a picture for the first time.
     *
     * @param url
     * @param uploadedAt
     * @return
     */
    public static Picture create(
            Url url,
            Edition edition,
            LocalDateTime uploadedAt
    ) {
        return new Picture(PictureId.generate(), url, edition, uploadedAt);
    }

    /**
     * Factory method to hidrate from a picture object from persistence.
     *
     * @param id
     * @param url
     * @param uploadedAt
     * @return
     */
    public static Picture of(
            PictureId id,
            Url url,
            Edition edition,
            LocalDateTime uploadedAt
    ) {
        return new Picture(id, url, edition, uploadedAt);
    }

    public PictureId id() { return this.id; }
    public Url url() { return this.url; }
    public LocalDateTime uploadedAt() { return this.uploadedAt; }
    public Edition edition() { return this.edition; }
}

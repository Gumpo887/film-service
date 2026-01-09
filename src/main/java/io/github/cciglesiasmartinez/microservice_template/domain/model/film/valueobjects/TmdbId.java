package io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TmdbId {

    private final String value;

    private TmdbId(String value) {
        this.value = value;
    }

    public static TmdbId of(String tmdbId) {
        if (tmdbId == null || tmdbId.isBlank()) {
            throw new IllegalArgumentException("TMDB ID can't be null or empty.");
        }
        return new TmdbId(tmdbId);
    }

    public String getValue() {
        return this.value;
    }

    public String value() {
        return this.value;
    }
}

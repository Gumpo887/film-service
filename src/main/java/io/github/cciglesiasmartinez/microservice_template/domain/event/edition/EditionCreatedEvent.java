package io.github.cciglesiasmartinez.microservice_template.domain.event.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EditionCreatedEvent extends DomainEvent {

    private final String editionId;
    private final String filmId;
    private final String filmTitle;
    private final String coverPicture;
    private final String barCode;
    private final String country;
    private final String format;
    private final Year releaseYear;
    private final String packagingType;
    private final String notes;

}

package io.github.cciglesiasmartinez.microservice_template.infrastructure.event.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionCreatedEvent;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionDocument;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionSearchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class EditionElasticsearchSyncHandler {

    private final EditionSearchRepository editionSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleEditionCreatedEvent(EditionCreatedEvent event) {
        log.info("Handling EditionCreatedEvent {} ", event.getRequestId());
        EditionDocument edition = EditionDocument.builder()
                .id(event.getEditionId())
                .filmId(event.getFilmId())
                .slug(event.getSlug())
                .filmTitle(event.getFilmTitle())
                .coverPicture(event.getCoverPicture())
                .barCode(event.getBarCode())
                .country(event.getCountry())
                .format(event.getFormat())
                .releaseYear(event.getReleaseYear().getValue())
                .packagingType(event.getPackagingType())
                .notes(event.getNotes())
                .indexedAt(LocalDateTime.now())
                .build();
        editionSearchRepository.save(edition);
    }

    // TODO: Implementar caso de update, con miras a obtener la coverPicture de las ediciones.
    // TODO: Posiblemente repasar si queremos algunos datos de cara a las búsquedas.

}

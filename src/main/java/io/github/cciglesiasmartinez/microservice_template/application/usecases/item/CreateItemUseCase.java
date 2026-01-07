package io.github.cciglesiasmartinez.microservice_template.application.usecases.item;

import io.github.cciglesiasmartinez.microservice_template.domain.exception.EditionNotFoundException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.CreateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.CreateItemResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Use case for creating items in a user's collection.
 */
@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CreateItemUseCase {

    private final EditionRepository editionRepository;
    private final ItemRepository itemRepository;

    /**
     * Retrieves an edition by ID.
     *
     * @param id edition identifier
     * @return the found edition
     * @throws EditionNotFoundException if not found
     */
    private Edition getEditionFrom(String id) {
        EditionId editionId = EditionId.of(id);
        return editionRepository.findById(editionId)
                .orElseThrow(() -> new EditionNotFoundException("Edition not found."));
    }

    /**
     * Builds an Item domain object from request data.
     *
     * @param request item data
     * @param userId  owner identifier
     * @param edition associated edition
     * @return new Item instance
     */
    private Item getItemFrom(CreateItemRequest request, String userId, Edition edition) {
        return Item.create(
                edition,
                userId,
                request.getPurchasePlace(),
                request.getPurchaseDate(),
                request.getPurchasePrice(),
                request.getMediaCondition(),
                request.getCaseCondition(),
                request.getComments());
    }

    /**
     * Executes create item use case.
     *
     * @param request item creation data
     * @param userId  owner's identifier
     * @return response envelope with created item ID
     * @throws EditionNotFoundException if edition doesn't exist
     */
    public Envelope<CreateItemResponse> execute(CreateItemRequest request, String userId) {
        Edition edition = getEditionFrom(request.getEditionId());
        Item item = getItemFrom(request, userId, edition);
        Item created = itemRepository.create(item);
        CreateItemResponse data = new CreateItemResponse(created.id().value(), true);
        log.info("Item {} created successfully.", created.id().value());
        return new Envelope<>(data, new Meta());
    }

}

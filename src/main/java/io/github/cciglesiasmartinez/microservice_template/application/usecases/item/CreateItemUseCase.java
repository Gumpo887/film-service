package io.github.cciglesiasmartinez.microservice_template.application.usecases.item;

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

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CreateItemUseCase {

    private EditionRepository editionRepository;
    private ItemRepository itemRepository;

    private Edition getEditionFrom(String id) {
        EditionId editionId = EditionId.of(id);
        return editionRepository.findById(editionId)
                .orElseThrow(() -> new IllegalArgumentException("Edition not found."));
    }

    public Envelope<CreateItemResponse> execute(CreateItemRequest request, String userId) {
        Edition edition = getEditionFrom(request.getEditionId());
        Item item = Item.create(
                edition,
                userId,
                request.getPurchasePlace(),
                request.getPurchaseDate(),
                request.getPurchasePrice(),
                request.getMediaCondition(),
                request.getCaseCondition(),
                request.getComments());
        Item created = itemRepository.create(item);
        CreateItemResponse data = new CreateItemResponse(created.id().value(), true);
        return new Envelope<>(data, new Meta());
    }

}

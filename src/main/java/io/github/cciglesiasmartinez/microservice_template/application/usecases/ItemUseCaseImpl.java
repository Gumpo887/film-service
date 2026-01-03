package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.item.CreateItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.item.UpdateItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.CreateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.UpdateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.CreateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.DeleteItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.GetItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.UpdateItemResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemUseCaseImpl implements ItemUseCase {

    private CreateItemUseCase createItemUseCase;
    private UpdateItemUseCase updateItemUseCase;

    @Override
    public Envelope<CreateItemResponse> createItem(CreateItemRequest request, String userId) {
        return createItemUseCase.execute(request, userId);
    }

    @Override
    public Envelope<GetItemResponse> getItem(String itemId, String userId) {
        return null;
    }

    @Override
    public Envelope<UpdateItemResponse> updateItem(UpdateItemRequest request, String userId) {
        return updateItemUseCase.execute(request, userId);
    }

    @Override
    public Envelope<DeleteItemResponse> deleteItem(String itemId) {
        return null;
    }
}

package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.EditionUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.edition.*;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.UpdateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.CreateEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.DeleteEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.GetEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.UpdateEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.wrappers.EditionWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EditionUseCaseImpl implements EditionUseCase {

    private final CreateEditionUseCase createEditionUseCase;
    private final GetEditionUseCase getEditionUseCase;
    private final UpdateEditionUseCase updateEditionUseCase;
    private final DeleteEditionUseCase deleteEditionUseCase;
    private final ListEditionsUseCase listEditionsUseCase;

    @Override
    public Envelope<CreateEditionResponse> createEdition(CreateEditionRequest request) {
        return createEditionUseCase.execute(request);
    }

    @Override
    public Envelope<GetEditionResponse> getEdition(String id) {
        return getEditionUseCase.execute(id);
    }

    @Override
    public Envelope<ListGenericResponse<EditionWrapper>> listAllEditions(int page, int size) {
        return listEditionsUseCase.execute(page, size);
    }

    @Override
    public Envelope<UpdateEditionResponse> updateEdition(UpdateEditionRequest request) {
        return updateEditionUseCase.execute(request);
    }

    @Override
    public Envelope<DeleteEditionResponse> deleteFilmById(String id) {
        return deleteEditionUseCase.execute(id);
    }
}

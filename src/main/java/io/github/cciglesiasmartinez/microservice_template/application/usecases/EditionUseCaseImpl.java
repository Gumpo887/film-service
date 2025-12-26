package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.EditionUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.edition.CreateEditionUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.edition.GetEditionUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.edition.UpdateEditionUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.UpdateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.CreateEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.GetEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.UpdateEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.DeleteFilmResponse;
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

    @Override
    public Envelope<CreateEditionResponse> createEdition(CreateEditionRequest request) {
        return createEditionUseCase.execute(request);
    }

    @Override
    public Envelope<GetEditionResponse> getEdition(String id) {
        return getEditionUseCase.execute(id);
    }

    @Override
    public Envelope<UpdateEditionResponse> updateEdition(UpdateEditionRequest request) {
        return updateEditionUseCase.execute(request);
    }

    @Override
    public Envelope<DeleteFilmResponse> deleteFilmById(String id) {
        return null;
    }
}

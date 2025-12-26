package io.github.cciglesiasmartinez.microservice_template.application.port.in;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.UpdateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.CreateEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.DeleteEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.GetEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.UpdateEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.wrappers.EditionWrapper;

public interface EditionUseCase {

    // Create
    Envelope<CreateEditionResponse> createEdition(CreateEditionRequest request);

    // Read
    Envelope<GetEditionResponse> getEdition(String id);
    Envelope<ListGenericResponse<EditionWrapper>> listAllEditions(int page, int size);

    // Update
    Envelope<UpdateEditionResponse> updateEdition(UpdateEditionRequest request);

    // Delete
    Envelope<DeleteEditionResponse> deleteFilmById(String id);

}

package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.listfilmsresponse.ListFilmsResponse;

public interface ListFilmsUseCase {

	Envelope<ListFilmsResponse> execute(int page, int size);
	
}

package io.github.cciglesiasmartinez.microservice_template.application.port.in;

import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.filmrequest.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.filmrequest.UpdateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.CreateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.DeleteFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.UpdateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse.ListFilmsResponse;

public interface FilmUseCase {
	
	// CREATE
    Envelope<CreateFilmResponse> createFilm(CreateFilmRequest request);
    // (Opcional) soporte de idempotencia en la request (idempotencyKey)

    // READ
    Envelope<GetFilmResponse> getFilm(FilmId id);
  
    // UPDATE (PUT completo o PATCH parcial)
    Envelope<UpdateFilmResponse> updateFilm(FilmId id, UpdateFilmRequest request);
    // Sugerencia: control de concurrencia optimista (version/E-Tag) en request

    // DELETE
    Envelope<DeleteFilmResponse> deleteFilm(FilmId id);
    // o Envelope<Void> si no necesitas payload
    
    Envelope<ListFilmsResponse> listFilms(int page, int size);
    
}



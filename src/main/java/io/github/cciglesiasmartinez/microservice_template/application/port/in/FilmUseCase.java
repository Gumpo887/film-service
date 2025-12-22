package io.github.cciglesiasmartinez.microservice_template.application.port.in;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.requests.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.requests.UpdateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.requests.TmdbDiscoverRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.CreateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.DeleteFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.GetFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.UpdateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.listfilmsresponse.ListFilmsResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses.TmdbDiscoverResponse;

public interface FilmUseCase {
	
	// CREATE
    Envelope<CreateFilmResponse> createFilm(CreateFilmRequest request);
    // (Opcional) soporte de idempotencia en la request (idempotencyKey)

    // READ
    Envelope<GetFilmResponse> getFilm(String id); // Pay attention here, an adapter DOES NOT receive domain entities/value objects
  
    // UPDATE (PUT completo o PATCH parcial)
    Envelope<UpdateFilmResponse> updateFilm(UpdateFilmRequest request);
    // Sugerencia: control de concurrencia optimista (version/E-Tag) en request

    // DELETE
    Envelope<DeleteFilmResponse> deleteFilm(String id);
    // o Envelope<Void> si no necesitas payload
    
    Envelope<ListFilmsResponse> listFilms(int page, int size);
    
    Envelope<TmdbDiscoverResponse> tmdbDiscover(TmdbDiscoverRequest request);

    
}



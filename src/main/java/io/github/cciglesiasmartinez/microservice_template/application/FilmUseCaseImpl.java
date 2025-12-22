package io.github.cciglesiasmartinez.microservice_template.application;

import io.github.cciglesiasmartinez.microservice_template.application.usecases.*;
import org.springframework.stereotype.Service;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.FilmUseCase;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FilmUseCaseImpl implements FilmUseCase {

	
    private final ListFilmsUseCase listFilmsUseCase;
    private final TmdbDiscoverUseCase tmdbDiscoverUseCase;

    // TODO: Refactor these usecases, give each one a class then inject them here via constructor

    private final CreateFilmUseCase createFilmUseCase;
    private final GetFilmUseCase getFilmUseCase;
    private final UpdateFilmUseCase updateFilmUseCase;
    private final DeleteFilmUseCase deleteFilmUseCase;

    @Override
    public Envelope<CreateFilmResponse> createFilm(CreateFilmRequest request) {
        return createFilmUseCase.execute(request);
    }

    @Override
    public Envelope<GetFilmResponse> getFilm(String filmId) {
        return getFilmUseCase.execute(filmId);
    }

    @Override
    public Envelope<UpdateFilmResponse> updateFilm(UpdateFilmRequest request) {
        return updateFilmUseCase.execute(request);
    }

    @Override
    public Envelope<DeleteFilmResponse> deleteFilm(String id) {
        return deleteFilmUseCase.execute(id);
    }

    @Override
    public Envelope<ListFilmsResponse> listFilms(int page, int size) {
        return listFilmsUseCase.execute(page, size);
    }

	@Override
	public Envelope<TmdbDiscoverResponse> tmdbSearch(TmdbDiscoverRequest request) {
		return tmdbDiscoverUseCase.execute(request);
	}

}


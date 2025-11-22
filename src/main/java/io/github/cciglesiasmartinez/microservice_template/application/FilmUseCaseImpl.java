package io.github.cciglesiasmartinez.microservice_template.application;

import org.springframework.stereotype.Service;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.FilmUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.ListFilmsUseCase;
import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongFilmIdException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.filmrequest.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.filmrequest.UpdateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.CreateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.DeleteFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.UpdateFilmResponse;
// ✂️ Imports de paginación/DTO listado eliminados de aquí (se mueven al nuevo caso de uso)
// import io.github...PageResult;
// import io.github...listfilmsresponse.FilmSummaryDto;
// import io.github...listfilmsresponse.ListFilmsResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse.ListFilmsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FilmUseCaseImpl implements FilmUseCase {

    private final FilmRepository filmRepository;
    private final ListFilmsUseCase listFilmsUseCase;

    // TODO: Refactor these usecases, give each one a class then inject them here via constructor

    @Override
    public Envelope<CreateFilmResponse> createFilm(CreateFilmRequest request) {
        Film film = Film.create(
                Title.of(request.getTitle()),
                Description.of(request.getDescription()),
                ReleaseYear.of(request.getReleaseYear()),
                ProducingCountry.of(request.getProducingCountry()),
                Rating.of(request.getRating()),
                Poster.of(request.getPoster())
        );

        Film saved = filmRepository.save(film);

        CreateFilmResponse data = new CreateFilmResponse(
                saved.itemId().value(),
                saved.title().value(),
                saved.description().value(),
                saved.releaseYear().value(),
                saved.producingCountry().value(),
                saved.rating().value(),
                saved.poster().value()
        );

        return new Envelope<>(data, new Meta());
    }

    @Override
    public Envelope<GetFilmResponse> getFilm(FilmId id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new WrongFilmIdException("Film ID not found"));

        GetFilmResponse data = new GetFilmResponse(
                film.itemId().value(),
                film.title().value(),
                film.description().value(),
                film.releaseYear().value(),
                film.producingCountry().value(),
                film.rating().value(),
                film.poster().value()
        );

        return new Envelope<>(data, new Meta());
    }

    @Override
    public Envelope<UpdateFilmResponse> updateFilm(FilmId id, UpdateFilmRequest request) {
        Film existing = filmRepository.findById(id)
                .orElseThrow(() -> new WrongFilmIdException("Film ID not found"));

        Title title = request.getTitle() != null ? Title.of(request.getTitle()) : existing.title();
        Description description = request.getDescription() != null ? Description.of(request.getDescription()) : existing.description();
        ReleaseYear releaseYear = request.getReleaseYear() != null ? ReleaseYear.of(request.getReleaseYear()) : existing.releaseYear();
        ProducingCountry producingCountry = request.getProducingCountry() != null ? ProducingCountry.of(request.getProducingCountry()) : existing.producingCountry();
        Rating rating = request.getRating() != null ? Rating.of(request.getRating()) : existing.rating();
        Poster poster = request.getPoster() != null ? Poster.of(request.getPoster()) : existing.poster();

        Film updated = Film.of(existing.itemId(), title, description, releaseYear, producingCountry, rating, poster);
        updated = filmRepository.update(updated);

        UpdateFilmResponse data = new UpdateFilmResponse(
                updated.itemId().value(),
                updated.title().value(),
                updated.description().value(),
                updated.releaseYear().value(),
                updated.producingCountry().value(),
                updated.rating().value(),
                updated.poster().value()
        );

        return new Envelope<>(data, new Meta());
    }

    @Override
    public Envelope<DeleteFilmResponse> deleteFilm(FilmId id) {
        boolean deleted = filmRepository.deleteById(id);

        DeleteFilmResponse data = new DeleteFilmResponse(id.value(), deleted);

        // Logging informativo
        if (deleted) {
            log.info("Film {} deleted.", id.value());
        } else {
            log.warn("Film {} not found to delete.", id.value());
        }

        return new Envelope<>(data, new Meta());
    }

	@Override
	public Envelope<ListFilmsResponse> listFilms(int page, int size) {
		
		return listFilmsUseCase.execute(page, size);
	}

}


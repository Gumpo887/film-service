package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.FilmUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.ListFilmsUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.UpdateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.tmdb.TmdbSearchRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.*;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse.ListFilmsResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.dto.response.tmdb.TmdbDiscoverResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/films")
@Tag(name = "Film service.", description = "Endpoints related to Film entity.")
public class FilmController {

    private final FilmUseCase filmUseCase;
    private final ListFilmsUseCase listFilmsUseCase;

    @Operation(summary = "Retrieves a film.", description = "Retrieves a film given its identifier.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Film retrieved successfully."))
    @GetMapping("/{id}")
    public ResponseEntity<Envelope<GetFilmResponse>> getFilm(@PathVariable String id) {
        Envelope<GetFilmResponse> response = filmUseCase.getFilm(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Creates a new film.", description = "Creates a new film with the provided data.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Film created successfully."))
    @PostMapping("") //TODO : Whats the convention here? To-slash or not-to-slash?
    public ResponseEntity<Envelope<CreateFilmResponse>> createFilm(@Valid @RequestBody CreateFilmRequest request) {
        Envelope<CreateFilmResponse> response = filmUseCase.createFilm(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "List films (paged).")
    @ApiResponses(@ApiResponse(responseCode ="200", description = "films retrieved successfully."))
    @GetMapping
    public ResponseEntity<Envelope<ListFilmsResponse>> listFilms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
       Envelope<ListFilmsResponse> response = listFilmsUseCase.execute(page, size);
       return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deletes a film.", description = "Deletes a film by its identifier.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Film deleted successfully."))
    @DeleteMapping("/{id}")
    public ResponseEntity<Envelope<DeleteFilmResponse>> deleteFilm(@PathVariable String id) {
        Envelope<DeleteFilmResponse> response = filmUseCase.deleteFilm(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Updates a film.", description = "Updates information on a given film.")
    @ApiResponses(@ApiResponse(responseCode = "202", description = "Film updated successfully."))
    @PutMapping("")
    public ResponseEntity<Envelope<UpdateFilmResponse>> updateFilm(@Valid @RequestBody UpdateFilmRequest request) {
        Envelope<UpdateFilmResponse> response = filmUseCase.updateFilm(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    
    @Operation(summary = "Discover TMDB films.", description = "Call TMDB discover/movie using the provided filters.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "TMDB results retrieved successfully."))
    @PostMapping("/tmdb")
    public ResponseEntity<Envelope<TmdbDiscoverResponse>> discoverTmdb(@Valid @RequestBody TmdbSearchRequest request) {
        Envelope<TmdbDiscoverResponse> response = filmUseCase.tmdbSearch(request);
        return ResponseEntity.ok(response);
    }


}


package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.tmdb.TmdbSearchRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.dto.response.tmdb.TmdbDiscoverResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TmdbSearchUseCase {

    private final WebClient tmdbWebClient;

    public Envelope<TmdbDiscoverResponse> execute(TmdbSearchRequest request) {
        TmdbDiscoverResponse response = tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/discover/movie")
                        .queryParam("include_adult", request.getIncludeAdult())
                        .queryParam("include_video", request.getIncludeVideo())
                        .queryParam("language", request.getLanguage())
                        .queryParam("page", request.getPage())
                        .queryParam("sort_by", request.getSortBy())
                        .build())
                .retrieve()
                .bodyToMono(TmdbDiscoverResponse.class)
                .block();

        return new Envelope<>(response, new Meta());
    }
}
package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.requests.TmdbDiscoverRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses.TmdbDiscoverResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses.TmdbVideo;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses.TmdbVideosResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TmdbSearchUseCase {

    private final WebClient tmdbWebClient;

    public Envelope<TmdbDiscoverResponse> execute(TmdbDiscoverRequest request) {
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

        if (response != null && response.getResults() != null) {
            response.getResults().forEach(movie -> movie.setTrailerKey(fetchTrailerKey(movie.getId(), request.getLanguage())));
        }

        return new Envelope<>(response, new Meta());
    }

    private String fetchTrailerKey(Integer movieId, String language) {
        if (movieId == null) {
            return null;
        }

        TmdbVideosResponse videosResponse = tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/videos")
                        .queryParam("language", language)
                        .build(movieId))
                .retrieve()
                .bodyToMono(TmdbVideosResponse.class)
                .block();

        if (videosResponse == null) {
            return null;
        }

        return extractTrailerKey(videosResponse.getResults());
    }

    private String extractTrailerKey(List<TmdbVideo> videos) {
        if (videos == null) {
            return null;
        }

        return videos.stream()
                .filter(video -> Boolean.TRUE.equals(video.getOfficial()))
                .filter(video -> "youtube".equalsIgnoreCase(video.getSite()))
                .filter(video -> "trailer".equalsIgnoreCase(video.getType()))
                .findFirst()
                .map(TmdbVideo::getKey)
                .orElse(null);
    }
}
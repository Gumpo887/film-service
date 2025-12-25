package io.github.cciglesiasmartinez.microservice_template.application.usecases.film;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.requests.TmdbSearchRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses.TmdbListResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.wrappers.TmdbVideoWrapper;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses.TmdbVideosResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class TmdbSearchUseCase {

    private final WebClient tmdbWebClient;

    /**
     * Helper method.
     *
     * @param movieId
     * @param language
     * @return
     */
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

    /**
     * Helper method.
     *
     * @param videos
     * @return
     */
    private String extractTrailerKey(List<TmdbVideoWrapper> videos) {
        if (videos == null) {
            return null;
        }

        return videos.stream()
                .filter(video -> Boolean.TRUE.equals(video.getOfficial()))
                .filter(video -> "youtube".equalsIgnoreCase(video.getSite()))
                .filter(video -> "trailer".equalsIgnoreCase(video.getType()))
                .findFirst()
                .map(TmdbVideoWrapper::getKey)
                .orElse(null);
    }

    /**
     * Executes TMDB Search use case.
     *
     * @param request
     * @return
     */
    public Envelope<TmdbListResponse> execute(TmdbSearchRequest request) {
        TmdbListResponse response = tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", request.getQuery())
                        .queryParam("include_adult", request.getIncludeAdult())
                        .queryParam("language", request.getLanguage())
                        .queryParam("primary_release", request.getPrimaryReleaseYear())
                        .queryParam("page", request.getPage())
                        .queryParam("region", request.getRegion())
                        .queryParam("year", request.getYear())
                        .build())
                .retrieve()
                .bodyToMono(TmdbListResponse.class)
                .block();
        if (response != null && response.getResults() != null) {
            response.getResults().forEach(
                    movie -> movie.setTrailerKey(fetchTrailerKey(movie.getId(), request.getLanguage()))
            );
        }
        return new Envelope<>(response, new Meta());
    }

}

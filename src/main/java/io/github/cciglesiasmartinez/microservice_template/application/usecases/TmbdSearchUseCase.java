package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor

public class TmbdSearchUseCase {

	private final WebClient tmdbWebClient;
	
	public Mono<Envelope<TmdbDiscoverRespone>> execute(TmdbDiscoverFilter filter) {
		
		return tmdbWebClient.get()
        .uri(uriBuilder -> uriBuilder
                .path("/discover/movie")
                .queryParam("include_adult", filter.includeAdult())
                .queryParam("include_video", filter.includeVideo())
                .queryParam("language", filter.language())
                .queryParam("page", filter.page())
                .queryParam("sort_by", filter.sortBy())
                .build())
        .retrieve()
        .bodyToMono(TmdbDiscoverResponse.class)
        .map(response -> new Envelope<>(response, new Meta()));
		
		
		
		
		
	}
	
	
}

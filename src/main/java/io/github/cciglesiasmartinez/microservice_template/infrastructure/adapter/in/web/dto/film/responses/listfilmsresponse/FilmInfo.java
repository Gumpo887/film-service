package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.listfilmsresponse;

public record FilmInfo(
	    String id,
	    String title,
	    Integer releaseYear,
	    String producingCountry,
	    String rating,
	    String poster
	) {}


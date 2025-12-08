package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.dto.response.tmdb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TmdbVideosResponse {
	
	private Integer id;
	private List<TmdbVideo> results;

}

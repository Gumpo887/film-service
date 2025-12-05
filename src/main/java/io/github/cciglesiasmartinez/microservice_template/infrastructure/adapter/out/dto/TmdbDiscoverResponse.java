package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class	)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TmdbDiscoverResponse {

	private Integer id;
	private List<TmbdMovie> results;
	private Integer totalPages;
	private Integer totalResults;
	
}

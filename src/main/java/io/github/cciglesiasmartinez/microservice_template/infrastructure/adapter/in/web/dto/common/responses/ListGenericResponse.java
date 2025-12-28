package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ListGenericResponse<T> {

	private List<T> elements;
	private int page;
	private int size;
	private long totalElements;
	private int totalPages;
	private boolean hasNext;
	private boolean hasPrevious;
	private Integer nextPage;
	private Integer prevPage;
	private String currentLink;
	private String nextLink;
	private String prevLink;

}
package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Edition resource.")
public class GetEditionResponse {

    private String id;
    private String filmId;

    private String barcode;
    private String country;
    private String format;
    private Year releaseYear;
    private String packagingType;
    private boolean verified;
    private String notes;

    private List<GetPictureResponse> pictures;

}

package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Request for creating a new item.")
public class CreateItemRequest {

    @Schema(
            description = "Item name",
            example = "ThingyThing"
    )
    private String name;

}

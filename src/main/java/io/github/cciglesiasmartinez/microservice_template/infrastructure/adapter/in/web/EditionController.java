package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.EditionUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.UpdateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/edition")
@Tag(name = "Edition service.", description = "Endpoints related to Edition entity.")
public class EditionController {

    private final EditionUseCase editionUseCase;

    @Operation(summary = "Retrieves an edition.", description = "Retrieves an edition given its identifier.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Edition retrieved successfully."))
    @GetMapping("/{id}")
    public ResponseEntity<Envelope<GetEditionResponse>> getEdition(@PathVariable String id) {
        Envelope<GetEditionResponse> response = editionUseCase.getEdition(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Creates a new edition.", description = "Creates a new edition with provided data.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Film created successfully."))
    @PostMapping("")
    public ResponseEntity<Envelope<CreateEditionResponse>> createEdition(
            @Valid @RequestBody CreateEditionRequest request) {
        Envelope<CreateEditionResponse> response = editionUseCase.createEdition(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

//    @Operation(summary = "List editions (paged).")
//    @ApiResponses(@ApiResponse(responseCode ="200", description = "Editions retrieved successfully."))
//    @GetMapping
//    public ResponseEntity<Envelope<ListEditionsResponse>> listEditions(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "20") int size) {
//        return null;
//    }

    @Operation(summary = "Deletes an edition.", description = "Deletes an edition by its identifier.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Edition deleted successfully."))
    @DeleteMapping("/{id}")
    public ResponseEntity<Envelope<DeleteEditionResponse>> deleteEdition(@PathVariable String id) {
        Envelope<DeleteEditionResponse> response = editionUseCase.deleteFilmById(id);
        return null;
    }

    @Operation(summary = "Updates an edition.", description = "Updates information on a given edition.")
    @ApiResponses(@ApiResponse(responseCode = "202", description = "Edition updated successfully."))
    @PutMapping("")
    public ResponseEntity<Envelope<UpdateEditionResponse>> updateEdition(
            @Valid @RequestBody UpdateEditionRequest request) {
        Envelope<UpdateEditionResponse> response = editionUseCase.updateEdition(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Gets a picture.", description = "Gets the data for a given picture id.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Picture retrieved successfully."))
    @GetMapping("/pictures/{id}")
    public ResponseEntity<Envelope<GetPictureResponse>> getPicture(@PathVariable String id ) {
        return null;
    }

    @Operation(summary = "Uploads a picture.", description = "Uploads a picture for a given edition id.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Picture correctly uploaded."))
    @PostMapping("/{id}/pictures")
    public ResponseEntity<Envelope<CreatePictureResponse>> uploadPicture(
            @PathVariable String editionId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type
    ) {
        return null;
    }

    @Operation(summary = "Deletes a picture.", description = "Deletes a given picture by id.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Picture deleted."))
    @DeleteMapping("/pictures/{id}")
    public ResponseEntity<Envelope<DeletePictureResponse>> deletePicture(@PathVariable String id) {
        return null;
    }

}

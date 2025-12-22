package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

public enum PackagingType {

    AMARAY,        // Standard case
    SLIPCASE,     // Standard with slipcase
    STEELBOOK,    // Metallic case
    DIGIPAK,      // Cardboard case
    MEDIABOOK,    // Like a book
    BOX_SET       // Box set (may contain AMARAY or others inside)

}

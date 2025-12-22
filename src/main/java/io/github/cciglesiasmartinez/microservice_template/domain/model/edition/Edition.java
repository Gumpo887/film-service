package io.github.cciglesiasmartinez.microservice_template.domain.model.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;

import java.time.Year;

/**
 * Represents an edition within the system.
 */
public class Edition {

    private EditionId id;
    private Film film;
    private BarCode barCode;
    private Country country;
    private Format format;
    private Year releaseYear;
    private PackagingType packagingType;
    private Notes notes;

    private Edition(
            EditionId id,
            Film film,
            BarCode barCode,
            Country country,
            Format format,
            Year releaseYear,
            PackagingType packagingType,
            Notes notes
    ) {
        this.id = id;
        this.film = film;
        this.barCode = barCode;
        this.country = country;
        this.format = format;
        this.releaseYear = releaseYear;
        this.packagingType = packagingType;
        this.notes = notes;
    }

    /**
     * Factory method intended to create an edition for the first time.
     *
     * @param film
     * @param barCode
     * @param country
     * @param format
     * @param releaseYear
     * @param packagingType
     * @param notes
     * @return
     */
    public static Edition create(
            Film film,
            BarCode barCode,
            Country country,
            Format format,
            Year releaseYear,
            PackagingType packagingType,
            Notes notes
    ) {
        return new Edition(EditionId.generate(), film, barCode, country, format, releaseYear, packagingType, notes);
    }

    /**
     * Factory method to hidrate a domain edition from a persistence object.
     *
     * @param id
     * @param film
     * @param barCode
     * @param country
     * @param format
     * @param releaseYear
     * @param packagingType
     * @param notes
     * @return
     */
    public static Edition of(
            EditionId id,
            Film film,
            BarCode barCode,
            Country country,
            Format format,
            Year releaseYear,
            PackagingType packagingType,
            Notes notes
    ) {
        return new Edition(id, film, barCode, country, format, releaseYear, packagingType, notes);
    }

    public EditionId editionId() { return this.id; }
    public Film film() { return this.film; }
    public BarCode barCode() { return this.barCode; }
    public Country country() { return this.country; }
    public Format format() { return this.format; }
    public Year releaseYear() { return this.releaseYear; }
    public PackagingType packagingType() { return this.packagingType; }
    public Notes notes() { return this.notes; }
}

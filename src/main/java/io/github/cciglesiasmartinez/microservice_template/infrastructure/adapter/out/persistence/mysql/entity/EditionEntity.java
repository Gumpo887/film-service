package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.Year;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "editions")
public class EditionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(nullable = false, name = "id_film")
    private String filmId;

    /*
        Information codes
     */
    @Column(name = "barcode")
    private String barCode;

    @Column(name = "upc_code")
    private String upcCode;

    @Column(name = "ean_code")
    private String eanCode;

    @Column(name = "catalogue_number")
    private String catalogueNumber;

    @Column(name = "asin")
    private String asin;

    /*
        Edition information
     */
    @Column(name = "edition_name")
    private String editionName;

    @Column(name = "country")
    private String country;

    @Column(name = "format")
    private String format;

    @Column(name = "region_code")
    private String regionCode;

    /*
        Release information
     */
    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "release_year")
    private Year releaseYear;

    @Column(name = "distributor")
    private String distributor;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "out_of_print")
    private boolean outOfPrint;

    /*
        Physical details
     */
    @Column(name = "disc_count")
    private int discCount;

    @Column(name = "packaging_type")
    private String packagingType;

    @Column(name = "case_dimensions")
    private String caseDimensions;

    /*
        Special editions information
     */
    @Column(name = "is_restored")
    private boolean isRestoredVersion;

    @Column(name = "is_directors_cut")
    private boolean isDirectorsCutVersion;

    @Column(name = "version_type")
    private String versionType;

    /*
        Images and media
     */
    @Column(name = "cover_image_url")
    private String coverImagenUrl;

    @Column(name = "back_cover_image_url")
    private String backCoverImageUrl;

    @Column(name = "disc_image_url")
    private String discImageUrl;

    /*
        Metadata
     */
    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "notes")
    private String notes;

    // TODO: Review a refine all these fields.

}

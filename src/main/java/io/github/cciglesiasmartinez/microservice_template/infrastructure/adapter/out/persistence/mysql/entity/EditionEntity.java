package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.Format;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.PackagingType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film;

    /*
        Information codes
     */
    @Column(name = "barcode")
    private String barCode;

//    @Column(name = "upc_code")
//    private String upcCode;
//
//    @Column(name = "ean_code")
//    private String eanCode;
//
//    @Column(name = "catalogue_number")
//    private String catalogueNumber;

    /*
        Edition information
     */
//    @Column(name = "edition_name")
//    private String editionName;

    @Column(name = "country")
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private Format format;

//    @Column(name = "region_code")
//    private String regionCode;

    /*
        Release information
     */
    @Column(name = "release_year")
    private Year releaseYear;

//    @Column(name = "distributor")
//    private String distributor;
//
//    @Column(name = "publisher")
//    private String publisher;
//
//    @Column(name = "out_of_print")
//    private boolean outOfPrint;

    /*
        Physical details
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "packaging_type")
    private PackagingType packagingType;
//
//    @Column(name = "case_dimensions")
//    private String caseDimensions;

    /*
        Special editions information
     */
//    @Column(name = "is_restored")
//    private boolean isRestoredVersion;
//
//    @Column(name = "is_directors_cut")
//    private boolean isDirectorsCutVersion;
//
//    @Column(name = "version_type")
//    private String versionType;

    /*
        Images and media
     */
//    @Column(name = "cover_image_url")
//    private String coverImagenUrl;
//
//    @Column(name = "back_cover_image_url")
//    private String backCoverImageUrl;
//
//    @Column(name = "disc_image_url")
//    private String discImageUrl;

    @OneToMany(
            mappedBy = "edition",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PictureEntity> pictures = new ArrayList<>();

    /*
        Metadata
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "notes")
    private String notes;

    /*
        Media
     */
//    @OneToMany(
//            mappedBy = "edition",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<MediaEntity> medias = new ArrayList<>();


    // TODO: Review a refine all these fields.

}

package io.github.cciglesiasmartinez.microservice_template.domain.model.item;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.Condition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.ItemId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Item {

    private ItemId id;
    private Edition edition;
    private String userId;
    private String purchasePlace;
    private LocalDate purchaseDate;
    private Double purchasePrice;
    private Condition mediaCondition;
    private Condition caseCondition;
    private String comments;
    private LocalDateTime addedDate;
    private LocalDateTime lastModified;

    private Item(
            ItemId id,
            Edition edition,
            String userId,
            String purchasePlace,
            LocalDate purchaseDate,
            Double purchasePrice,
            Condition mediaCondition,
            Condition caseCondition,
            String comments,
            LocalDateTime addedDate,
            LocalDateTime lastModified) {
        this.id = id;
        this.edition = edition;
        this.userId = userId;
        this.purchasePlace = purchasePlace;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.mediaCondition = mediaCondition;
        this.caseCondition = caseCondition;
        this.comments = comments;
        this.addedDate = addedDate;
        this.lastModified = lastModified;
    }

    public static Item create(
            Edition edition,
            String userId,
            String purchasePlace,
            LocalDate purchaseDate,
            Double purchasePrice,
            Condition mediaCondition,
            Condition caseCondition,
            String comments) {
        LocalDateTime now = LocalDateTime.now();
        return new Item(
                ItemId.generate(),
                edition,
                userId,
                purchasePlace,
                purchaseDate,
                purchasePrice,
                mediaCondition,
                caseCondition,
                comments,
                now,
                now
        );
    }

    public static Item of(
            ItemId id,
            Edition edition,
            String userId,
            String purchasePlace,
            LocalDate purchaseDate,
            Double purchasePrice,
            Condition mediaCondition,
            Condition caseCondition,
            String comments,
            LocalDateTime addedDate,
            LocalDateTime lastModified) {
        return new Item(
                id,
                edition,
                userId,
                purchasePlace,
                purchaseDate,
                purchasePrice,
                mediaCondition,
                caseCondition,
                comments,
                addedDate,
                lastModified
        );
    }

    public ItemId id() {
        return this.id;
    }
    public Edition edition() {
        return this.edition;
    }
    public String userId() {
        return this.userId;
    }
    public String purchasePlace() {
        return this.purchasePlace;
    }
    public LocalDate purchaseDate() {
        return this.purchaseDate;
    }
    public Double purchasePrice() {
        return this.purchasePrice;
    }
    public Condition mediaCondition() {
        return this.mediaCondition;
    }
    public Condition caseCondition() {
        return this.caseCondition;
    }
    public String comments() {
        return this.comments;
    }
    public LocalDateTime addedDate() {
        return this.addedDate;
    }
    public LocalDateTime lastModified() {
        return this.lastModified;
    }

}

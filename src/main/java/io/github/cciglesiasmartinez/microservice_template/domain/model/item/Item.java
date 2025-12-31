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
    private LocalDate purchaseData;
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
            LocalDate purchaseData,
            Double purchasePrice,
            Condition mediaCondition,
            Condition caseCondition,
            String comments,
            LocalDateTime addedDate,
            LocalDateTime lastModified) {
        this.id = id;
        this.edition = edition;
        this.userId = userId;
        this.purchaseData = purchaseData;
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
            LocalDate purchaseData,
            Double purchasePrice,
            Condition mediaCondition,
            Condition caseCondition,
            String comments) {
        LocalDateTime now = LocalDateTime.now();
        return new Item(
                ItemId.generate(),
                edition,
                userId,
                purchaseData,
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
            LocalDate purchaseData,
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
                purchaseData,
                purchasePrice,
                mediaCondition,
                caseCondition,
                comments,
                addedDate,
                lastModified
        );
    }
}

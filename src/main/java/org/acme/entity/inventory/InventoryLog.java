package org.acme.entity.inventory;

import jakarta.persistence.*;
import org.acme.entity.AuditListener;
import org.acme.entity.BaseEntity;
import org.acme.model.enumerate.InventoryLogType;
import org.acme.model.enumerate.ReferenceType;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_log")
public class InventoryLog extends BaseEntity {

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private InventoryLogType type;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "reference_type")
    @Enumerated(EnumType.STRING)
    private ReferenceType referenceType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    public InventoryLog() {
        super();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public InventoryLogType getType() {
        return type;
    }

    public void setType(InventoryLogType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public ReferenceType getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}

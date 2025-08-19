package org.acme.entity.item;

import jakarta.persistence.*;
import org.acme.entity.BaseEntity;
import org.acme.model.enumerate.ReferenceType;

import java.math.BigDecimal;

@Entity
@Table(name = "item_cost")
public class ItemCost extends BaseEntity {

    @Column(name = "ref_number")
    private String referenceNumber;

    @Column(name = "reference_type")
    @Enumerated(EnumType.STRING)
    private ReferenceType referenceType;

    @Column(name = "entry_date")
    private String entryDate;

    @Column(name = "priority")
    private int priority;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "supplier")
    private String supplier;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemCost() {
        super();
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

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

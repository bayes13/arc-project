package org.acme.entity.inventory;

import jakarta.persistence.*;
import org.acme.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "inventory_price")
public class InventoryPrice extends BaseEntity {

    @Column(name = "selling_price")
    private BigDecimal sellingPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    public InventoryPrice() {
        super();
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}

package org.acme.entity.item;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.acme.entity.BaseEntity;
import org.acme.entity.inventory.Inventory;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item extends BaseEntity {
    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "unit_type")
    private String unitType;

    @Column(name = "category")
    private String category;

    @Column(name = "sellable")
    private boolean sellable;

    @Column(name = "moq")
    private int moq;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "item")
    private Set<ItemCost> itemCostList = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Inventory> inventoryList = new HashSet<>();

    public Item() {
        super();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSellable() {
        return sellable;
    }

    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    public int getMoq() {
        return moq;
    }

    public void setMoq(int moq) {
        this.moq = moq;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<ItemCost> getItemCostList() {
        return itemCostList;
    }

    public void setItemCostList(Set<ItemCost> itemCostList) {
        this.itemCostList = itemCostList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(Set<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}

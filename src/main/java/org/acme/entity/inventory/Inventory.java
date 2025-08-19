package org.acme.entity.inventory;

import jakarta.persistence.*;
import org.acme.entity.BaseEntity;
import org.acme.entity.item.Item;
import org.acme.entity.location.Location;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "inventory")
public class Inventory extends BaseEntity {

    @Column(name = "on_hand_qty")
    private int onHandQty;

    @Column(name = "available_qty")
    private int availableQty;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "inventory")
    private Set<InventoryLog> inventoryLogList = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "inventory")
    private Set<InventoryPrice> inventoryPriceList = new HashSet<>();

    public Inventory() {
    }

    public int getOnHandQty() {
        return onHandQty;
    }

    public void setOnHandQty(int onHandQty) {
        this.onHandQty = onHandQty;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Set<InventoryLog> getInventoryLogList() {
        return inventoryLogList;
    }

    public void setInventoryLogList(Set<InventoryLog> inventoryLogList) {
        this.inventoryLogList = inventoryLogList;
    }

    public Set<InventoryPrice> getInventoryPriceList() {
        return inventoryPriceList;
    }

    public void setInventoryPriceList(Set<InventoryPrice> inventoryPriceList) {
        this.inventoryPriceList = inventoryPriceList;
    }
}

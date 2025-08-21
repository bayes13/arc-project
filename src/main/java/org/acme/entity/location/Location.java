package org.acme.entity.location;

import jakarta.persistence.*;
import org.acme.entity.BaseEntity;
import org.acme.entity.inventory.Inventory;
import org.acme.model.enumerate.LocationType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "location")
public class Location extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private LocationType type;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "default_phone")
    private String defaultPhone;

    @Column
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "location")
    private Set<Inventory> inventoryList = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "location")
    private Set<ExtLocation> extLocationList = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "location")
    private Set<Contact> contactList = new HashSet<>();

    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getDefaultPhone() {
        return defaultPhone;
    }

    public void setDefaultPhone(String defaultPhone) {
        this.defaultPhone = defaultPhone;
    }

    public Set<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(Set<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public Set<ExtLocation> getExtLocationList() {
        return extLocationList;
    }

    public void setExtLocationList(Set<ExtLocation> extLocationList) {
        this.extLocationList = extLocationList;
    }

    public Set<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Set<Contact> contactList) {
        this.contactList = contactList;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

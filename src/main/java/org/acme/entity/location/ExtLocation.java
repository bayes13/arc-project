package org.acme.entity.location;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.acme.entity.BaseEntity;
import org.acme.model.enumerate.LocationType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ext_location")
public class ExtLocation extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private LocationType type;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "default_phone")
    private String defaultPhone;

    @Column(name = "enabled", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "extLocation")
    private Set<Contact> contactList = new HashSet<>();

    public ExtLocation() {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

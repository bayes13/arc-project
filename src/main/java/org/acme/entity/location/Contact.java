package org.acme.entity.location;

import jakarta.persistence.*;
import org.acme.entity.AuditListener;
import org.acme.entity.BaseEntity;
import org.acme.model.enumerate.ContactType;

@Entity
@Table(name = "contact")
public class Contact extends BaseEntity {
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "company")
    private String company;

    @Column(name = "type")
    private ContactType type;

    @Column(name = "enabled", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ext_location_id")
    private ExtLocation extLocation;

    public Contact() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ExtLocation getExtLocation() {
        return extLocation;
    }

    public void setExtLocation(ExtLocation extLocation) {
        this.extLocation = extLocation;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

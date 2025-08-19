package org.acme.model.location;

import org.acme.model.BaseModel;

public class ContactRequest extends BaseModel {
    private String fullName;

    private String company;

    private String type;

    private String locationId;

    private boolean isExtLocationId;

    public ContactRequest() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public boolean isExtLocationId() {
        return isExtLocationId;
    }

    public void setExtLocationId(boolean extLocationId) {
        isExtLocationId = extLocationId;
    }
}

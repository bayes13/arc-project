package org.acme.model.location;

import org.acme.model.BaseModel;

public class LocationRequest extends BaseModel {
    private String name;

    private String type;

    private String address;

    private String defaultPhone;

    private Boolean enabled;

    private boolean isExtLocation;

    private ContactRequest contactRequest;

    public LocationRequest() {
    }

    public String getDefaultPhone() {
        return defaultPhone;
    }

    public void setDefaultPhone(String defaultPhone) {
        this.defaultPhone = defaultPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExtLocation() {
        return isExtLocation;
    }

    public void setExtLocation(boolean extLocation) {
        isExtLocation = extLocation;
    }

    public ContactRequest getContactRequest() {
        return contactRequest;
    }

    public void setContactRequest(ContactRequest contactRequest) {
        this.contactRequest = contactRequest;
    }
}

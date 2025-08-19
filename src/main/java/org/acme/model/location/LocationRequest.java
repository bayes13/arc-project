package org.acme.model.location;

import org.acme.model.BaseModel;

public class LocationRequest extends BaseModel {
    private String name;

    private String type;

    private String address;

    private String defaultPhone;

    private boolean enabled;


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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ContactRequest getContactRequest() {
        return contactRequest;
    }

    public void setContactRequest(ContactRequest contactRequest) {
        this.contactRequest = contactRequest;
    }
}

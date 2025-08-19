package org.acme.model.location;

import org.acme.model.BaseModel;

public class ContactResponse extends BaseModel {
    private String fullName;

    private String company;

    private String type;

    public ContactResponse() {
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

}

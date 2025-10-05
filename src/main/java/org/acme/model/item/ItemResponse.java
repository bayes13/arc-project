package org.acme.model.item;

import org.acme.model.BaseModel;

public class ItemResponse extends BaseModel {
    private String sku;
    private String name;
    private String unitType;
    private String category;
    private boolean sellable;
    private int moq;
    private boolean enable;

    public ItemResponse() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

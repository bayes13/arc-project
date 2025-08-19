package org.acme.entity.item;

import jakarta.persistence.*;
import org.acme.entity.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_category")
public class ItemCategory extends BaseEntity {
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "sub_name")
    private String subName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "itemCategory")
    private Set<Item> itemList = new HashSet<>();

    public ItemCategory() {
        super();
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Item> getItemList() {
        return itemList;
    }

    public void setItemList(Set<Item> itemList) {
        this.itemList = itemList;
    }
}

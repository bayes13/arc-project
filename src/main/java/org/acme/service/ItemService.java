package org.acme.service;

import io.quarkus.panache.common.Sort;
import org.acme.entity.item.Item;
import org.acme.entity.item.ItemCost;
import org.acme.model.enumerate.ReferenceType;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemService {
    List<Item> viewItem(String name, String category, String unitType, boolean sellable, boolean enabled, Sort sort, int index, int size);

    List<ItemCost> viewItemCost(String itemId, String referenceNo, ReferenceType referenceType, LocalDateTime startDate, LocalDateTime endDate, Sort sort, int index, int size);

    List<String> getItemCategory();

    boolean insertItem(Item item);

    boolean insertItemCost(ItemCost itemCost);

    boolean updateItem(String id, String name, String category, String unitType, boolean sellable, boolean enabled);

    boolean updateItemCost(String id, int qty, String supplier);

    boolean updateItemCostPriority(String id, int priority);

}

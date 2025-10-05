package org.acme.serviceImpl;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.HibernateQueryHelper;
import org.acme.entity.item.Item;
import org.acme.entity.item.ItemCost;
import org.acme.model.enumerate.ReferenceType;
import org.acme.service.ItemService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {
    private static final String QUERY = "query";
    private static final String PARAM = "param";

    @Override
    @Transactional
    public List<Item> viewItem(String name, String category, String unitType, boolean sellable, boolean enabled, Sort sort, int index, int size) {
        final Map<String, Object> queryAndParam = HibernateQueryHelper.itemQuery("from Item", name, category, unitType, sellable, enabled);
        return Item.find((String) queryAndParam.get(QUERY), sort, (Parameters) queryAndParam.get(PARAM))
                .page(index, size)
                .list();
    }

    @Override
    @Transactional
    public List<ItemCost> viewItemCost(String itemId, String referenceNo, ReferenceType referenceType, LocalDateTime startDate, LocalDateTime endDate, Sort sort, int index, int size) {
        final Map<String, Object> queryAndParam = HibernateQueryHelper.itemCostQuery("from ItemCost", itemId, referenceNo, referenceType, startDate, endDate);
        return ItemCost.find((String) queryAndParam.get(QUERY), sort, (Parameters) queryAndParam.get(PARAM))
                .page(index, size)
                .list();
    }

    @Override
    @Transactional
    public boolean insertItem(Item item) {
        item.persist();
        return true;
    }

    @Override
    @Transactional
    public boolean insertItemCost(ItemCost itemCost) {
        final Item item = Item.findById(itemCost.getItem().getId());
        itemCost.setItem(item);
        itemCost.persist();
        return true;
    }

    @Override
    @Transactional
    public boolean updateItem(String id, String name, String category, String unitType, boolean sellable, boolean enabled) {
        final Item currentItem = Item.findById(UUID.fromString(id));
        Optional.ofNullable(name).ifPresent(currentItem::setName);
        Optional.ofNullable(category).ifPresent(currentItem::setCategory);
        Optional.ofNullable(unitType).ifPresent(currentItem::setUnitType);
        Optional.of(sellable).ifPresent(currentItem::setEnabled);
        Optional.of(enabled).ifPresent(currentItem::setEnabled);

        return true;
    }

    @Override
    public boolean updateItemCost(String id, int qty, String supplier) {
        final ItemCost currentItemCost = ItemCost.findById(id);
        Optional.of(qty).ifPresent(currentItemCost::setQuantity);
        Optional.of(supplier).ifPresent(currentItemCost::setSupplier);

        return true;
    }

    @Override
    public boolean updateItemCostPriority(String id, int priority) {

        final ItemCost currentItemCost = ItemCost.findById(id);
        currentItemCost.setPriority(priority);

        return true;
    }
}

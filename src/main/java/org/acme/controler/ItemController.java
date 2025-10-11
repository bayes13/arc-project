package org.acme.controler;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acme.AppHelper;
import org.acme.entity.item.Item;
import org.acme.entity.item.ItemCost;
import org.acme.model.BaseHttpModel;
import org.acme.model.item.ItemCostRequest;
import org.acme.model.item.ItemCostResponse;
import org.acme.model.item.ItemRequest;
import org.acme.model.item.ItemResponse;
import org.acme.service.ItemService;
import org.slf4j.MDC;

import java.util.List;
import java.util.Objects;

@Path("/v1/item")
public class ItemController {
    private static final String CREATED_BY = "createdBy";

    @Inject
    ItemService service;

    @Path("/insertItem")
    @POST
    public Response insertItem(BaseHttpModel<ItemRequest> request) {
        setAuditUser(request.getBody().getCreatedBy());
        final Item item = AppHelper.generateItemEntity(request.getBody());

        boolean isSuccess = service.insertItem(item);

        request.getBody().setModificationInfo(AppHelper.generateModificationInfo(isSuccess, null, null));
        return Response.ok(request).build();
    }

    @Path("/insertItemCost")
    @POST
    public Response insertItemCost(BaseHttpModel<ItemCostRequest> request) {
        setAuditUser(request.getBody().getCreatedBy());
        final ItemCost itemCost = AppHelper.generateItemCostEntity(request.getBody());

        final boolean isSuccess = service.insertItemCost(itemCost);

        request.getBody().setModificationInfo(AppHelper.generateModificationInfo(isSuccess, null, null));
        return Response.ok(request).build();
    }

    @Path("/viewItem")
    @POST
    public Response viewItem(BaseHttpModel<ItemRequest> request) {
        final ItemRequest body = request.getBody();
        final Sort sort = buildSort(request);

        final List<ItemResponse> responses = service.viewItem(
                        AppHelper.validateNullString(body.getName()), AppHelper.validateNullString(body.getCategory()), AppHelper.validateNullString(body.getUnitType()),
                        AppHelper.validateNullBoolean(body.isSellable()), AppHelper.validateNullBoolean(body.isEnable()),
                        sort, request.getPageMetaData().getIndex(), request.getPageMetaData().getSize())
                .stream()
                .map(AppHelper::mapToItemResponse).toList();

        return Response.ok(responses).build();
    }

    @Path("/viewItemCost")
    @POST
    public Response viewItemCost(BaseHttpModel<ItemCostRequest> request) {
        final ItemCostRequest body = request.getBody();
        final Sort sort = buildSort(request);

        final List<ItemCostResponse> responses = service.viewItemCost(
                        body.getItemId(), AppHelper.validateNullString(body.getReferenceNo()), body.getReferenceType(),
                        body.getStartDate(), body.getEndDate(),
                        sort, request.getPageMetaData().getIndex(), request.getPageMetaData().getSize())
                .stream()
                .map(AppHelper::mapToItemCostResponse)
                .toList();

        return Response.ok(responses).build();
    }

    @Path("/getCategories")
    @GET
    public Response getCategories() {
        final List<String> responses = service.getItemCategory();
        return Response.ok(responses).build();
    }

    @Path("/updateItem")
    public Response updateItem(BaseHttpModel<ItemRequest> request) {
        setAuditUser(request.getBody().getCreatedBy());
        final ItemRequest body = request.getBody();

        final boolean isUpdated = service.updateItem(body.getId(), body.getName(), body.getCategory(), body.getUnitType(), body.isSellable(), body.isEnable());
        request.getBody().getModificationInfo().setSuccess(isUpdated);
        return Response.ok(request)
                .build();
    }

    @Path("/updateItemCost")
    public Response updateItemCost(BaseHttpModel<ItemCostRequest> request) {
        setAuditUser(request.getBody().getCreatedBy());
        final ItemCostRequest body = request.getBody();

        final boolean isUpdated = service.updateItemCost(body.getId(),body.getQty(),body.getSupplier());
        request.getBody().getModificationInfo().setSuccess(isUpdated);
        return Response.ok(request)
                .build();
    }

    @Path("/updateItemCost")
    public Response updateCostPriority(BaseHttpModel<ItemCostRequest> request) {
        setAuditUser(request.getBody().getCreatedBy());
        final ItemCostRequest body = request.getBody();

        final boolean isUpdated = service.updateItemCostPriority(body.getId(),body.getPriority());
        request.getBody().getModificationInfo().setSuccess(isUpdated);
        return Response.ok(request)
                .build();
    }




    private void setAuditUser(String user) {
        MDC.put(CREATED_BY, user);
    }

    private Sort buildSort(BaseHttpModel<?> request) {
        return AppHelper.generatedSortedRequest(request.getPageMetaData().getSortList(), "name", Sort.Direction.Ascending);
    }

    private <E extends Enum<E>> E parseEnum(String value, Class<E> enumClass) {
        return (Objects.nonNull(value)) ? Enum.valueOf(enumClass, value) : null;
    }
}

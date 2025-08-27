package org.acme.controller;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acme.AppHelper;
import org.acme.entity.location.Contact;
import org.acme.entity.location.ExtLocation;
import org.acme.entity.location.Location;
import org.acme.model.BaseHttpModel;
import org.acme.model.enumerate.ContactType;
import org.acme.model.enumerate.LocationType;
import org.acme.model.location.ContactRequest;
import org.acme.model.location.ContactResponse;
import org.acme.model.location.LocationRequest;
import org.acme.model.location.LocationResponse;
import org.acme.service.LocationService;
import org.slf4j.MDC;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Path("/v1/location")
public class LocationController {

    private static final String CREATED_BY = "createdBy";
    @Inject
    LocationService service;

    @Path("/insertContact")
    @POST
    public Response insertContact(BaseHttpModel<ContactRequest> request) {
        setAuditUser(request.getBody().getCreatedBy());
        Contact contact = AppHelper.generateContactEntity(request.getBody());

        boolean isSuccess = service.insertContact(contact, request.getBody().isExtLocation(), UUID.fromString(request.getBody().getLocationId()));

        request.getBody().setModificationInfo(AppHelper.generateModificationInfo(isSuccess, null, null));
        return Response.ok(request).build();
    }

    @Path("/insertLocation")
    @POST
    public Response insertLocation(BaseHttpModel<LocationRequest> request) {
        setAuditUser(request.getBody().getCreatedBy());
        Location location = AppHelper.generateLocationEntity(request.getBody());

        boolean isSuccess = service.insertLocation(location);
        request.getBody().setModificationInfo(AppHelper.generateModificationInfo(isSuccess, null, null));

        return Response.ok(request).build();
    }

    @Path("/insertExtLocation")
    @POST
    public Response insertExtLocation(BaseHttpModel<LocationRequest> request) {
        setAuditUser(request.getBody().getCreatedBy());
        ExtLocation extLocation = AppHelper.generateExtLocationEntity(request.getBody());

        boolean isSuccess = service.insertExtLocation(extLocation);
        request.getBody().setModificationInfo(AppHelper.generateModificationInfo(isSuccess, null, null));

        return Response.ok(request).build();
    }

    @Path("/viewContact")
    @POST
    public Response viewContact(BaseHttpModel<ContactRequest> request) {
        ContactRequest body = request.getBody();
        Sort sort = buildSort(request);

        List<ContactResponse> responses = service.viewContact(AppHelper.validateNullString(body.getLocationId()),
                AppHelper.validateNullString(body.getFullName()), AppHelper.validateNullString(body.getCompany()),
                parseEnum(body.getType(), ContactType.class), body.isExtLocation(), AppHelper.validateNullBoolean(body.getEnabled()),
                sort, request.getPageMetaData().getIndex(), request.getPageMetaData().getSize()).stream().map(AppHelper::mapToContactResponse).toList();

        return Response.ok(responses).build();
    }

    @Path("/viewLocation")
    @POST
    public Response viewLocation(BaseHttpModel<LocationRequest> request) {
        LocationRequest body = request.getBody();
        Sort sort = buildSort(request);

        List<LocationResponse> responses = service.viewLocation(AppHelper.validateNullString(body.getName()),
                AppHelper.validateNullString(body.getAddress()), parseEnum(body.getType(), LocationType.class),
                AppHelper.validateNullBoolean(body.getEnabled()), sort, request.getPageMetaData().getIndex(),
                request.getPageMetaData().getSize()).stream().map(AppHelper::mapToLocationResponse).toList();

        return Response.ok(responses).build();
    }

    @Path("/viewExtLocation")
    @POST
    public Response viewExtLocation(BaseHttpModel<LocationRequest> request) {
        LocationRequest body = request.getBody();
        Sort sort = buildSort(request);

        List<LocationResponse> responses = service.viewExtLocation(body.getId(), AppHelper.validateNullString(body.getName()),
                AppHelper.validateNullString(body.getAddress()), parseEnum(body.getType(), LocationType.class),
                AppHelper.validateNullBoolean(body.getEnabled()), sort, request.getPageMetaData().getIndex(),
                request.getPageMetaData().getSize()).stream().map(AppHelper::mapToLocationResponse).toList();

        return Response.ok(responses).build();
    }


    @Path("/updateContact")
    @POST
    public Response updateContact(BaseHttpModel<ContactRequest> request) {
        setAuditUser(request.getBody().getUpdatedBy());
        ContactRequest body = request.getBody();

        boolean result = service.updateContact(body.getId(), AppHelper.validateNullString(body.getFullName()),
                AppHelper.validateNullString(body.getCompany()), parseEnum(body.getType(), ContactType.class), body.getEnabled());

        return Response.ok(result).build();
    }

    @Path("/updateLocation")
    @POST
    public Response updateLocation(BaseHttpModel<LocationRequest> request) {
        LocationRequest body = request.getBody();

        boolean result = service.updateLocation(body.getId(), AppHelper.validateNullString(body.getName()),
                AppHelper.validateNullString(body.getAddress()), parseEnum(body.getType(), LocationType.class),
                AppHelper.validateNullString(body.getDefaultPhone()), body.getEnabled(), body.isExtLocation());

        return Response.ok(result).build();
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

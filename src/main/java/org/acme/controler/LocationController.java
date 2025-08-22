package org.acme.controler;


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

@Path("/v1/location")
public class LocationController {

    @Inject
    LocationService service;

    @Path("/insertContact")
    @POST
    public Response insertContact(BaseHttpModel<ContactRequest> request) {
        MDC.put("createdBy", request.getBody().getCreatedBy());
        final Contact contact = AppHelper.generateContactEntity(request.getBody());
        final boolean isSuccess = service.insertContact(contact);
        request.getBody().setModificationInfo(AppHelper.generateModificationInfo(isSuccess, null, null));
        return Response.ok().entity(request).build();

    }

    @Path("/insertLocation")
    @POST
    public Response insertLocation(BaseHttpModel<LocationRequest> request) {
        MDC.put("createdBy", request.getBody().getCreatedBy());
        final Location location = AppHelper.generateLocationEntity(request.getBody());
        final boolean isSuccess = service.insertLocation(location);
        request.getBody().setModificationInfo(AppHelper.generateModificationInfo(isSuccess, null, null));
        return Response.ok().entity(request).build();
    }

    @Path("/insertExtLocation")
    @POST
    public Response insertExtLocation(BaseHttpModel<LocationRequest> request) {
        MDC.put("createdBy", request.getBody().getCreatedBy());
        final ExtLocation extLocation = AppHelper.generateExtLocationEntity(request.getBody());
        final boolean isSuccess = service.insertExtLocation(extLocation);
        request.getBody().setModificationInfo(AppHelper.generateModificationInfo(isSuccess, null, null));
        return Response.ok().entity(request).build();
    }

    @Path("/viewContact")
    @POST
    public Response viewContact(BaseHttpModel<ContactRequest> request) {
        final String fullName = request.getBody().getFullName();
        final String company = request.getBody().getCompany();
        final ContactType type = ContactType.valueOf(request.getBody().getType());
        final String locationId = request.getBody().getLocationId();
        final boolean isExtLocationId = request.getBody().isExtLocationId();
        final Boolean enabled = request.getBody().getEnabled();
        final Sort sort = AppHelper.generatedSortedRequest(request.getPageMetaData().getSortList(), "name", Sort.Direction.Ascending);
        final int index = request.getPageMetaData().getIndex();
        final int size = request.getPageMetaData().getSize();

        final List<ContactResponse> responses = service.viewContact(locationId, fullName, company, type, isExtLocationId, enabled, sort, index, size)
                .stream().map(AppHelper::mapToContactResponse)
                .toList();

        return Response.ok().entity(responses).build();
    }

    @Path("/viewLocation")
    @POST
    public Response viewLocation(BaseHttpModel<LocationRequest> request) {
        final String name = request.getBody().getName();
        final LocationType type = LocationType.valueOf(request.getBody().getType());
        final String address = request.getBody().getAddress();
        final Boolean enabled = request.getBody().getEnabled();
        final Sort sort = AppHelper.generatedSortedRequest(request.getPageMetaData().getSortList(), "name", Sort.Direction.Ascending);
        final int index = request.getPageMetaData().getIndex();
        final int size = request.getPageMetaData().getSize();

        final List<LocationResponse> responses = service.viewLocation(name, address, type, enabled, sort, index, size)
                .stream().map(AppHelper::mapToLocationResponse)
                .toList();

        return Response.ok().entity(responses).build();
    }

    @Path("/viewExtLocation")
    @POST
    public Response viewExtLocation(BaseHttpModel<LocationRequest> request) {
        final String locationId = request.getBody().getId();
        final String name = request.getBody().getName();
        final LocationType type = LocationType.valueOf(request.getBody().getType());
        final String address = request.getBody().getAddress();
        final Boolean enabled = request.getBody().getEnabled();
        final Sort sort = AppHelper.generatedSortedRequest(request.getPageMetaData().getSortList(), "name", Sort.Direction.Ascending);
        final int index = request.getPageMetaData().getIndex();
        final int size = request.getPageMetaData().getSize();

        final List<LocationResponse> responses = service.viewExtLocation(locationId, name, address, type, enabled, sort, index, size)
                .stream().map(AppHelper::mapToLocationResponse)
                .toList();

        return Response.ok().entity(responses).build();
    }

    @Path("/updateContact")
    @POST
    public Response updateContact(BaseHttpModel<ContactRequest> request) {
        MDC.put("createdBy", request.getBody().getUpdatedBy());
        final String id = request.getBody().getId();
        final String name = request.getBody().getFullName();
        final ContactType type = ContactType.valueOf(request.getBody().getType());
        final String company = request.getBody().getCompany();
        final Boolean enabled = request.getBody().getEnabled();
        final boolean responses = service.updateContact(id, name, company, type, enabled);

        return Response.ok().entity(responses).build();
    }

    @Path("/updateLocation")
    @POST
    public Response updateLocation(BaseHttpModel<LocationRequest> request) {
        final String id = request.getBody().getId();
        final String name = request.getBody().getName();
        final LocationType type = LocationType.valueOf(request.getBody().getType());
        final String address = request.getBody().getAddress();
        final String defaultPhone = request.getBody().getDefaultPhone();
        final Boolean enabled = request.getBody().getEnabled();
        final boolean isExtLocation = request.getBody().isExtLocation();

        final boolean responses = service.updateLocation(id, name, address, type, defaultPhone, enabled, isExtLocation);

        return Response.ok().entity(responses).build();
    }


}

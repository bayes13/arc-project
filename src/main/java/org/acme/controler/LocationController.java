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
import org.acme.model.location.ContactRequest;
import org.acme.model.location.ContactResponse;
import org.acme.model.location.LocationRequest;
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
        final Sort sort = AppHelper.generatedSortedRequest(request.getPageMetaData().getSortList(), "name", Sort.Direction.Ascending);
        final int index = request.getPageMetaData().getIndex();
        final int size = request.getPageMetaData().getSize();

        final List<ContactResponse> responses = service.viewContact(locationId, fullName, company, type, isExtLocationId, sort, index, size)
                .stream().map(AppHelper::mapToContactResponse)
                .toList();

        return Response.ok().entity(responses).build();
    }


}

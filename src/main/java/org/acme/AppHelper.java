package org.acme;

import io.quarkus.panache.common.Sort;
import org.acme.entity.location.Contact;
import org.acme.entity.location.ExtLocation;
import org.acme.entity.location.Location;
import org.acme.model.BaseModel;
import org.acme.model.PageMetaData;
import org.acme.model.enumerate.ContactType;
import org.acme.model.enumerate.LocationType;
import org.acme.model.location.ContactRequest;
import org.acme.model.location.ContactResponse;
import org.acme.model.location.LocationRequest;
import org.acme.model.location.LocationResponse;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AppHelper {

    private static final String ASCENDING = "Ascending";

    public static Contact generateContactEntity(ContactRequest request) {
        final Contact contact = new Contact();
        contact.setId(UUID.randomUUID());
        contact.setCompany(request.getCompany());
        contact.setType(ContactType.valueOf(request.getType()));
        contact.setFullName(request.getFullName());

        return contact;

    }

    public static String validateNullString(String request) {
        return Objects.nonNull(request) ? request : null;
    }

    public static boolean validateNullBoolean(Boolean request) {
        return Objects.nonNull(request) ? request : true;
    }

    public static Location generateLocationEntity(LocationRequest request) {
        final UUID locationId = UUID.randomUUID();
        final Location location = new Location();
        location.setId(locationId);
        location.setName(request.getName());
        location.setDefaultPhone(request.getDefaultPhone());
        location.setFullAddress(request.getAddress());
        location.setType(LocationType.valueOf(request.getType()));
        if (Objects.nonNull(request.getContactRequest())) {
            request.getContactRequest().setExtLocation(false);
            request.getContactRequest().setLocationId(locationId.toString());
            final Contact contact = generateContactEntity(request.getContactRequest());
            contact.setLocation(location);
            location.getContactList().add(contact);
        }
        return location;
    }

    public static ExtLocation generateExtLocationEntity(LocationRequest request) {
        final UUID extLocationId = UUID.randomUUID();
        final Location location = new Location();
        location.setId(UUID.fromString(request.getId()));
        final ExtLocation extLocation = new ExtLocation();
        extLocation.setId(extLocationId);
        extLocation.setName(request.getName());
        extLocation.setDefaultPhone(request.getDefaultPhone());
        extLocation.setFullAddress(request.getAddress());
        extLocation.setType(LocationType.valueOf(request.getType()));
        extLocation.setLocation(location);
        if (Objects.nonNull(request.getContactRequest())) {
            request.getContactRequest().setExtLocation(true);
            request.getContactRequest().setLocationId(extLocationId.toString());
            final Contact contact = generateContactEntity(request.getContactRequest());
            contact.setExtLocation(extLocation);
            extLocation.getContactList().add(contact);
        }
        return extLocation;
    }

    public static BaseModel.ModificationInfo generateModificationInfo(boolean success, String statusCode, String statusMessage) {
        return new BaseModel.ModificationInfo(success, statusCode, statusMessage);
    }

    public static Sort generatedSortedRequest(List<PageMetaData.Sort> sortRequestList, String defaultColumn, Sort.Direction defaultDirection) {
        if (Objects.isNull(sortRequestList) || sortRequestList.isEmpty()) {
            return Sort.by(defaultColumn, defaultDirection);
        } else {
            Sort sort = Sort.by();
            for (PageMetaData.Sort sortRequest : sortRequestList) {
                final String column = sortRequest.getColumn();
                final Sort.Direction direction = ASCENDING.equalsIgnoreCase(sortRequest.getDirection()) ? Sort.Direction.Ascending : Sort.Direction.Descending;
                sort.and(column, direction);
            }
            return sort;
        }
    }

    public static ContactResponse mapToContactResponse(Contact contact) {
        final ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(contact.getId().toString());
        contactResponse.setCompany(contact.getCompany());
        contactResponse.setType(contact.getType().name());
        contactResponse.setFullName(contact.getFullName());
        contactResponse.setCreatedBy(contact.getCreatedBy());
        contactResponse.setUpdatedBy(contact.getUpdatedBy());
        contactResponse.setCreatedTimestamp(contact.getCreatedTimestamp());
        contactResponse.setUpdatedTimestamp(contact.getUpdatedTimestamp());
        return contactResponse;
    }

    public static LocationResponse mapToLocationResponse(Location location) {
        final LocationResponse locationResponse = new LocationResponse();
        locationResponse.setId(location.getId().toString());
        locationResponse.setName(location.getName());
        locationResponse.setType(location.getType().name());
        locationResponse.setAddress(location.getFullAddress());
        locationResponse.setDefaultPhone(location.getDefaultPhone());
        locationResponse.setEnabled(location.isEnabled());
        locationResponse.setCreatedBy(location.getCreatedBy());
        locationResponse.setUpdatedBy(location.getUpdatedBy());
        locationResponse.setCreatedTimestamp(location.getCreatedTimestamp());
        locationResponse.setUpdatedTimestamp(location.getUpdatedTimestamp());
        return locationResponse;
    }

    public static LocationResponse mapToLocationResponse(ExtLocation extLocation) {
        final LocationResponse locationResponse = new LocationResponse();
        locationResponse.setId(extLocation.getId().toString());
        locationResponse.setName(extLocation.getName());
        locationResponse.setType(extLocation.getType().name());
        locationResponse.setAddress(extLocation.getFullAddress());
        locationResponse.setDefaultPhone(extLocation.getDefaultPhone());
        locationResponse.setEnabled(extLocation.isEnabled());
        locationResponse.setCreatedBy(extLocation.getCreatedBy());
        locationResponse.setUpdatedBy(extLocation.getUpdatedBy());
        locationResponse.setCreatedTimestamp(extLocation.getCreatedTimestamp());
        locationResponse.setUpdatedTimestamp(extLocation.getUpdatedTimestamp());
        return locationResponse;
    }

}

package org.acme.service;

import io.quarkus.panache.common.Sort;
import org.acme.entity.location.Contact;
import org.acme.entity.location.ExtLocation;
import org.acme.entity.location.Location;
import org.acme.model.enumerate.ContactType;
import org.acme.model.enumerate.LocationType;

import java.util.List;
import java.util.UUID;

public interface LocationService {

    boolean insertContact(Contact contact, boolean isExtLocation, UUID LocationId);

    List<Contact> viewContact(String locationId, String name, String company, ContactType type, boolean isExtLocation, Boolean enabled,
                              Sort sort, int index, int size);

    boolean insertLocation(Location location);

    boolean insertExtLocation(ExtLocation location);

    List<Location> viewLocation(String name, String address, LocationType type, Boolean enabled, Sort sort, int index, int size);

    List<ExtLocation> viewExtLocation(String locationId, String name, String address, LocationType type, Boolean enabled,
                                      Sort sort, int index, int size);

    boolean updateContact(String id, String name, String company, ContactType type, Boolean enabled);

    boolean updateLocation(String id,String name, String address, LocationType type, String defaultPhone, Boolean enabled, boolean isExtLocation);

}

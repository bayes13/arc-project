package org.acme.service;

import io.quarkus.panache.common.Sort;
import org.acme.entity.location.Contact;
import org.acme.entity.location.ExtLocation;
import org.acme.entity.location.Location;
import org.acme.model.enumerate.ContactType;
import org.acme.model.enumerate.LocationType;

import java.util.List;

public interface LocationService {

    boolean insertContact(Contact contact);

    List<Contact> viewContact(String locationId, String name, String company, ContactType type, boolean isExtLocation,
                              Sort sort, int index, int size);

    boolean insertLocation(Location location);

    boolean insertExtLocation(ExtLocation location);

    List<Location> viewLocation(String name, String address, LocationType type, Sort sort, int index, int size);

    List<ExtLocation> viewExtLocation(String locationId, String name, String address, LocationType type,
                                      Sort sort, int index, int size);

}

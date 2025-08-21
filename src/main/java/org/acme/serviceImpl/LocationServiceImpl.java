package org.acme.serviceImpl;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import org.acme.HibernateQueryHelper;
import org.acme.entity.location.Contact;
import org.acme.entity.location.ExtLocation;
import org.acme.entity.location.Location;
import org.acme.model.enumerate.ContactType;
import org.acme.model.enumerate.LocationType;
import org.acme.service.LocationService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class LocationServiceImpl implements LocationService {

    private static final String QUERY = "query";
    private static final String PARAM = "param";

    @Override
    @Transactional
    public boolean insertContact(Contact contact) {
        if (Objects.nonNull(contact.getExtLocation().getId())) {
            final ExtLocation extLocation = ExtLocation.findById(contact.getExtLocation().getId());
            contact.setExtLocation(extLocation);
            extLocation.getContactList().add(contact);
            extLocation.persistAndFlush();
        } else {
            final Location location = Location.findById(contact.getLocation().getId());
            contact.setLocation(location);
            location.getContactList().add(contact);
            location.persistAndFlush();
        }
        return true;
    }

    @Override
    @Transactional
    public List<Contact> viewContact(String locationId, String name, String company, ContactType type, boolean isExtLocation, Boolean enabled, Sort sort, int index, int size) {
        Map<String, Object> queryParam = HibernateQueryHelper.generateContactQuery("from Contact", name, UUID.fromString(locationId), company, type, isExtLocation, enabled);
        return Contact.find((String) queryParam.get(QUERY), sort, (Parameters) queryParam.get(PARAM)).page(index, size).list();
    }

    @Override
    @Transactional
    public boolean insertLocation(Location location) {
        location.persistAndFlush();
        return true;
    }

    @Override
    @Transactional
    public boolean insertExtLocation(ExtLocation extLocation) {
        final Location location = Location.findById(extLocation.getLocation().getId());
        location.getExtLocationList().add(extLocation);
        extLocation.setLocation(location);
        location.persistAndFlush();
        return true;
    }

    @Override
    @Transactional
    public List<Location> viewLocation(String name, String address, LocationType type, Boolean enabled, Sort sort, int index, int size) {
        Map<String, Object> queryParam = HibernateQueryHelper.generateLocationQuery("from Location", name, address, type, enabled);
        return Location.find((String) queryParam.get(QUERY), sort, (Parameters) queryParam.get(PARAM)).page(index, size).list();
    }

    @Override
    @Transactional
    public List<ExtLocation> viewExtLocation(String locationId, String name, String address, LocationType type, Boolean enabled, Sort sort, int index, int size) {
        Map<String, Object> queryParam = HibernateQueryHelper.generateExtLocationQuery("from ExtLocation", UUID.fromString(locationId), name, address, type, enabled);
        return ExtLocation.find((String) queryParam.get(QUERY), sort, (Parameters) queryParam.get(PARAM)).page(index, size).list();
    }


}

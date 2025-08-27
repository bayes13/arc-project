package org.acme.serviceImpl;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
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
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class LocationServiceImpl implements LocationService {

    private static final String QUERY = "query";
    private static final String PARAM = "param";

    @Override
    @Transactional
    public boolean insertContact(Contact contact, boolean isExtLocation, UUID locationId) {
        if (isExtLocation) {
            final ExtLocation extLocation = ExtLocation.findById(locationId);
            contact.setExtLocation(extLocation);
            extLocation.getContactList().add(contact);
            extLocation.persistAndFlush();
        } else {
            final Location location = Location.findById(locationId);
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
        extLocation.setLocation(location);
        extLocation.persist();
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

    @Override
    @Transactional
    public boolean updateContact(String id, String name, String company, ContactType type, Boolean enabled) {
        final Contact currentContact = Contact.findById(UUID.fromString(id));
        if (currentContact == null) {
            return false;
        }

        Optional.ofNullable(name).ifPresent(currentContact::setFullName);
        Optional.ofNullable(company).ifPresent(currentContact::setCompany);
        Optional.ofNullable(type).ifPresent(currentContact::setType);
        Optional.ofNullable(enabled).ifPresent(currentContact::setEnabled);

        return true;
    }

    @Override
    @Transactional
    public boolean updateLocation(String id, String name, String address, LocationType type, String defaultPhone, Boolean enabled, boolean isExtLocation) {
        if (isExtLocation) {
            final ExtLocation currentExtLocation = ExtLocation.findById(UUID.fromString(id));
            Optional.ofNullable(name).ifPresent(currentExtLocation::setName);
            Optional.ofNullable(address).ifPresent(currentExtLocation::setFullAddress);
            Optional.ofNullable(type).ifPresent(currentExtLocation::setType);
            Optional.ofNullable(defaultPhone).ifPresent(currentExtLocation::setDefaultPhone);
            Optional.ofNullable(enabled).ifPresent(currentExtLocation::setEnabled);
            return true;
        }

        final Location currentLocation = Location.findById(UUID.fromString(id));
        Optional.ofNullable(name).ifPresent(currentLocation::setName);
        Optional.ofNullable(address).ifPresent(currentLocation::setFullAddress);
        Optional.ofNullable(type).ifPresent(currentLocation::setType);
        Optional.ofNullable(defaultPhone).ifPresent(currentLocation::setDefaultPhone);
        Optional.ofNullable(enabled).ifPresent(currentLocation::setEnabled);
        return true;

    }


}

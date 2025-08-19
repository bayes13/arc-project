package org.acme;

import io.quarkus.panache.common.Parameters;
import org.acme.model.enumerate.ContactType;
import org.acme.model.enumerate.LocationType;

import java.util.*;

public class HibernateQueryHelper {

    public static Map<String, Object> generateContactQuery(String baseQuery, String name, UUID locationId, String company, ContactType type, boolean isExtLocation) {
        final Map<String, Object> objectMap = new HashMap<>();
        final Parameters parameters = new Parameters();
        final StringBuilder query = new StringBuilder(baseQuery);
        query.append(" where");
        if (isExtLocation) {
            query.append(" extLocation.id := locationId");
        } else {
            query.append(" location.id := locationId");
        }
        parameters.and("locationId", locationId);
        query.append(" and");
        if (hasNoEmptyFilter(name, company, type)) {
            if (Objects.nonNull(name)) {
                query.append(" name like :name and");
                parameters.and("name", likeParameter(name));
            }
            if (Objects.nonNull(name)) {
                query.append(" company like company and");
                parameters.and("company", likeParameter(company));
            }

            if (Objects.nonNull(type)) {
                query.append(" type := type and");
                parameters.and("type", type);
            }
            query.replace(query.length() - 3, query.length(), "");
        }

        objectMap.put("query", query.toString());
        objectMap.put("param", parameters);
        return objectMap;
    }

    public static Map<String, Object> generateLocationQuery(String baseQuery, String name, String address, LocationType type) {
        final Map<String, Object> objectMap = new HashMap<>();
        final Parameters parameters = new Parameters();
        final StringBuilder query = new StringBuilder(baseQuery);
        query.append(" where");
        if (hasNoEmptyFilter(name, address, type)) {
            if (Objects.nonNull(name)) {
                query.append(" name like :name and");
                parameters.and("name", likeParameter(name));
            }
            if (Objects.nonNull(name)) {
                query.append(" address like address and");
                parameters.and("address", likeParameter(address));
            }

            if (Objects.nonNull(type)) {
                query.append(" type := type and");
                parameters.and("type", type);
            }
            query.replace(query.length() - 3, query.length(), "");
        }

        objectMap.put("query", query.toString());
        objectMap.put("param", parameters);
        return objectMap;
    }

    public static Map<String, Object> generateExtLocationQuery(String baseQuery, UUID locationId, String name, String address, LocationType type) {
        final Map<String, Object> objectMap = new HashMap<>();
        final Parameters parameters = new Parameters();
        final StringBuilder query = new StringBuilder(baseQuery);
        query.append(" where");
        query.append(" location.id := locationId and");
        parameters.and("locationId", locationId);
        if (hasNoEmptyFilter(name, address, type)) {
            if (Objects.nonNull(name)) {
                query.append(" name like :name and");
                parameters.and("name", likeParameter(name));
            }
            if (Objects.nonNull(name)) {
                query.append(" address like address and");
                parameters.and("address", likeParameter(address));
            }

            if (Objects.nonNull(type)) {
                query.append(" type := type and");
                parameters.and("type", type);
            }
            query.replace(query.length() - 3, query.length(), "");
        }

        objectMap.put("query", query.toString());
        objectMap.put("param", parameters);
        return objectMap;
    }

    private static boolean hasNoEmptyFilter(Object... filters) {
        return Arrays.stream(filters).anyMatch(filter -> filter != null && !filter.toString().isEmpty());
    }

    private static String likeParameter(String value) {
        return String.join("", "%", value, "%");
    }
}

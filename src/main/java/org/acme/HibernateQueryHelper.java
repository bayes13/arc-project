package org.acme;

import io.quarkus.panache.common.Parameters;
import org.acme.model.enumerate.ContactType;
import org.acme.model.enumerate.LocationType;

import java.util.*;

public class HibernateQueryHelper {

    private static final String EXT_LOCATION_ID = "extLocation.id";
    private static final String LOCATION_ID = "location.id";
    private static final String QUERY = "query";
    private static final String PARAM = "param";

    public static Map<String, Object> buildBaseLocationQuery(String baseQuery, String locationField, UUID locationId, String name, String address, String company, Enum<?> type, Boolean enabled
    ) {
        final Map<String, Object> objectMap = new HashMap<>();
        final Parameters parameters = new Parameters();
        final StringBuilder query = new StringBuilder(baseQuery).append(" where 1=1");

        if (locationField != null && locationId != null) {
            query.append(" and ").append(locationField).append(" = :locationId");
            parameters.and("locationId", locationId);
        }

        if (Objects.nonNull(name)) {
            query.append(" and name like :name");
            parameters.and("name", likeParameter(name));
        }

        if (Objects.nonNull(address)) {
            query.append(" and address like :address");
            parameters.and("address", likeParameter(address));
        }

        if (Objects.nonNull(company)) {
            query.append(" and company like :company");
            parameters.and("company", likeParameter(company));
        }

        if (Objects.nonNull(type)) {
            query.append(" and type = :type");
            parameters.and("type", type);
        }

        if (Objects.nonNull(enabled)) {
            query.append(" and enabled = :enabled");
            parameters.and("enabled", enabled);
        }

        objectMap.put(QUERY, query.toString());
        objectMap.put(PARAM, parameters);
        return objectMap;
    }

    public static Map<String, Object> generateContactQuery(String baseQuery, String name, UUID locationId,
                                                           String company, ContactType type, boolean isExtLocation, Boolean enabled) {
        String locationField = isExtLocation ? EXT_LOCATION_ID : LOCATION_ID;
        return buildBaseLocationQuery(baseQuery, locationField, locationId, name, null, company, type, enabled);
    }

    public static Map<String, Object> generateLocationQuery(String baseQuery, String name, String address,
                                                            LocationType type, Boolean enabled) {
        return buildBaseLocationQuery(baseQuery, null, null, name, address, null, type, enabled);
    }

    public static Map<String, Object> generateExtLocationQuery(String baseQuery, UUID locationId, String name,
                                                               String address, LocationType type, Boolean enabled) {
        return buildBaseLocationQuery(baseQuery, LOCATION_ID, locationId, name, address, null, type, enabled);
    }

//    public static Map<String, Object> generateContactQuery(String baseQuery, String name, UUID locationId, String company, ContactType type, boolean isExtLocation, Boolean enabled) {
//        final Map<String, Object> objectMap = new HashMap<>();
//        final Parameters parameters = new Parameters();
//        final StringBuilder query = new StringBuilder(baseQuery);
//        query.append(" where");
//        if (isExtLocation) {
//            query.append(" extLocation.id := locationId");
//        } else {
//            query.append(" location.id := locationId");
//        }
//        parameters.and("locationId", locationId);
//        query.append(" and");
//        if (hasNoEmptyFilter(name, company, type)) {
//            if (Objects.nonNull(name)) {
//                query.append(" name like :name and");
//                parameters.and("name", likeParameter(name));
//            }
//            if (Objects.nonNull(name)) {
//                query.append(" company like company and");
//                parameters.and("company", likeParameter(company));
//            }
//
//            if (Objects.nonNull(type)) {
//                query.append(" type := type and");
//                parameters.and("type", type);
//            }
//            if (Objects.nonNull(enabled)) {
//                query.append(" enabled := enabled and");
//                parameters.and("enabled", enabled);
//            }
//
//            query.replace(query.length() - 3, query.length(), "");
//        }
//
//        objectMap.put("query", query.toString());
//        objectMap.put("param", parameters);
//        return objectMap;
//    }
//
//    public static Map<String, Object> generateLocationQuery(String baseQuery, String name, String address, LocationType type, Boolean enabled) {
//        final Map<String, Object> objectMap = new HashMap<>();
//        final Parameters parameters = new Parameters();
//        final StringBuilder query = new StringBuilder(baseQuery);
//        query.append(" where");
//        if (hasNoEmptyFilter(name, address, type)) {
//            if (Objects.nonNull(name)) {
//                query.append(" name like :name and");
//                parameters.and("name", likeParameter(name));
//            }
//            if (Objects.nonNull(name)) {
//                query.append(" address like address and");
//                parameters.and("address", likeParameter(address));
//            }
//
//            if (Objects.nonNull(type)) {
//                query.append(" type := type and");
//                parameters.and("type", type);
//            }
//
//            if (Objects.nonNull(enabled)) {
//                query.append(" enabled := enabled and");
//                parameters.and("enabled", enabled);
//            }
//
//            query.replace(query.length() - 3, query.length(), "");
//        }
//
//        objectMap.put("query", query.toString());
//        objectMap.put("param", parameters);
//        return objectMap;
//    }
//
//    public static Map<String, Object> generateExtLocationQuery(String baseQuery, UUID locationId, String name, String address, LocationType type, Boolean enabled) {
//        final Map<String, Object> objectMap = new HashMap<>();
//        final Parameters parameters = new Parameters();
//        final StringBuilder query = new StringBuilder(baseQuery);
//        query.append(" where");
//        query.append(" location.id := locationId and");
//        parameters.and("locationId", locationId);
//        if (hasNoEmptyFilter(name, address, type)) {
//            if (Objects.nonNull(name)) {
//                query.append(" name like :name and");
//                parameters.and("name", likeParameter(name));
//            }
//            if (Objects.nonNull(name)) {
//                query.append(" address like address and");
//                parameters.and("address", likeParameter(address));
//            }
//
//            if (Objects.nonNull(type)) {
//                query.append(" type := type and");
//                parameters.and("type", type);
//            }
//            if (Objects.nonNull(enabled)) {
//                query.append(" enabled := enabled and");
//                parameters.and("enabled", enabled);
//            }
//
//            query.replace(query.length() - 3, query.length(), "");
//        }
//
//        objectMap.put("query", query.toString());
//        objectMap.put("param", parameters);
//        return objectMap;
//    }

    private static boolean hasNoEmptyFilter(Object... filters) {
        return Arrays.stream(filters).anyMatch(filter -> filter != null && !filter.toString().isEmpty());
    }

    private static String likeParameter(String value) {
        return String.join("", "%", value, "%");
    }
}

package com.ns.util;

public class En {

    // Short names for ZUL commands' args
    public static final String A1 = "a1", A2 = "a2", A3 = "a3", A4 = "a4";

    // Auxiliary methods -----------------------------------------------------------------------------------------------
    public static boolean isAnyNull(Object... objects) {
        for(Object obj : objects)
            if (obj == null)
                return true;

        return false;
    }

    // Get enum name by enum ordinal and class, if within the range. (1 -> "dos")
    public static <E extends Enum<E>> String enumName(Integer enumOrdinal, Class<E> enumClass) {
        return enumOrdinal != null
                && enumOrdinal >= 0
                && enumOrdinal < enumClass.getEnumConstants().length ?
                enumClass.getEnumConstants()[enumOrdinal].name() : "";
    }

    // Get enum names as a String. ("[ uno dos tres ]")
    public static <E extends Enum<E>> String enumNames(Class<E> eClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Enum<E> enumVal : eClass.getEnumConstants())
            sb
                    .append(" ")
                    .append(enumVal.toString());

        return sb.append(" ]").toString();
    }

    // Get enum ordinal by enum name and class, if exists. ("uno" -> 0)
    public static <E extends Enum<E>> Integer enumOrdinal(String eName, Class<E> eClass) {
        for (Enum<E> enumVal : eClass.getEnumConstants())
            if (enumVal.name().equals(eName))
                return enumVal.ordinal();

        return null;
    }

}

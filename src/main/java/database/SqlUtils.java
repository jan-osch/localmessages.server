package database;

public class SqlUtils {
    public static String addParam(String baseSql, String parameterName, String value) {
        return baseSql.replaceFirst(":" + parameterName, value);
    }
}
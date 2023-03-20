package edu.sdr.dc.utils;

public class QueryUtils {

    public static String createInsertStatement(final String tableName, String... arg) {

        String query = "";

        if (arg.length > 0) {
            String columns = "";
            String values = "";

            for (int index = 0; index < arg.length - 1; index += 2) {
                columns += arg[index] + ", ";
                values += "'" + arg[index + 1] + "'" + ", ";
            }

            columns = columns.substring(0, columns.length() - 2);
            values = values.substring(0, values.length() - 2);

            query = "INSERT INTO " + tableName +  "(" + columns + ") VALUES(" + values + ")";
        }

        return query;
    }

}

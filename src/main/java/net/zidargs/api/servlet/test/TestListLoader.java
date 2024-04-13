package net.zidargs.api.servlet.test;

import net.zidargs.api.util.mysql.MySQLConnector;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestListLoader {

    private static final String queryTemplate = "SELECT * FROM test LIMIT %d OFFSET %d";

    private final MySQLConnector mySQLConnector = MySQLConnector.getInstance();

    public void loadList(List<TestEntityData> entityDataList, int page, int pageSize) {
        int offset = page * pageSize;
        String query = String.format(queryTemplate, pageSize, offset);
        mySQLConnector.executeStatement(query, resultSet -> {
            while (resultSet.next()) {
                String key = resultSet.getString("id");
                String displayName = resultSet.getString("name");
                Date date = resultSet.getDate("date");

                entityDataList.add(new TestEntityData(
                        key,
                        displayName,
                        date
                ));
            }
        });
    }

}

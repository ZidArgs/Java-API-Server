package net.zidargs.api.servlet.test;

import net.zidargs.api.util.mysql.MySQLConnector;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestListLoader {

    private static final String queryTemplate = "SELECT * FROM test LIMIT ? OFFSET ?";

    private final MySQLConnector mySQLConnector = MySQLConnector.getInstance();

    public void loadList(List<TestEntityData> entityDataList, int page, int pageSize) {
        int offset = page * pageSize;
        mySQLConnector.executeStatement(queryTemplate, statement -> {
            statement.setInt(1, page);
            statement.setInt(2, offset);
        }, resultSet -> {
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

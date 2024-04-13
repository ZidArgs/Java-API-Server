package net.zidargs.api.util.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetConsumer {

    void consume(ResultSet resultSet) throws SQLException;

}

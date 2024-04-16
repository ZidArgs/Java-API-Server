package net.zidargs.api.util.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementConsumer {

    void consume(PreparedStatement statement) throws SQLException;

}

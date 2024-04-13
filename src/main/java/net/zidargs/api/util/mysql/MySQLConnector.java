package net.zidargs.api.util.mysql;

import java.sql.*;

public class MySQLConnector {

    static MySQLConfig mySQLConfig = MySQLConfig.getInstance();

    private static final MySQLConnector instance = new MySQLConnector(mySQLConfig.getUrl());

    private Connection conn;

    private MySQLConnector(String dbUrl) {
        try {
            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void executeStatement(String sqlStatement) {
        executeStatement(sqlStatement, resultSet -> {});
    }

    public void executeStatement(String sqlStatement, ResultSetConsumer resultSetConsumer) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            if (statement.execute(sqlStatement)) {
                resultSet = statement.getResultSet();
                resultSetConsumer.consume(resultSet);
            }
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqlEx) {
                    // ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) {
                    // ignore
                }
            }
        }
    }

    public static MySQLConnector getInstance() {
        return instance;
    }

}

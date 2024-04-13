package net.zidargs.api.util.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MySQLConfig {

    private String url;
    private static final MySQLConfig instance = new MySQLConfig();
    private static final String urlTemplate = "jdbc:mysql://%s/%s?user=%s&password=%s";

    private MySQLConfig() {
        loadPropertiesFile();
    }

    private void loadPropertiesFile() {
        Properties prop = new Properties();
        try (InputStream inputStream = MySQLConfig.class.getResourceAsStream("/mysql-connection.properties")) {
            prop.load(inputStream);

            final String url = prop.getProperty("mysql_url");
            final String database = prop.getProperty("mysql_database");
            final String username = prop.getProperty("mysql_user");
            final String password = prop.getProperty("mysql_password");

            this.url = String.format(urlTemplate, url, database, username, password);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public String getUrl() {
        return url;
    }

    public static MySQLConfig getInstance() {
        return instance;
    }

}

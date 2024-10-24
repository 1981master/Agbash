package com.agbash.connection;

import com.agbash.service.AppConfig;
import com.agbash.service.Config;
import com.agbash.service.ServiceMail;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import org.yaml.snakeyaml.Yaml;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
        
        
                Yaml yaml = new Yaml();
        
        // Load the YAML file
        InputStream inputStream = ServiceMail.class
            .getClassLoader()
            .getResourceAsStream("com/agbash/icon/application.yml");

        AppConfig appConfig = yaml.loadAs(inputStream, AppConfig.class);

        Config config = appConfig.getConfig();
        
        String server = "localhost";
        String port = "3306";
        String database = config.getDb();
        String userName = "root";
        String password = config.getPass();
        connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

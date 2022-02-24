package com.graphic.training.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {

    private static Connection SINGLETON_INSTANCE;

    private ConnectionManager() {
    }

    private static void loadDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            System.err.println("Classe de Driver introuvable");
        }
    }

    public static void closeConnection() {
        if (SINGLETON_INSTANCE != null) {
            try {
                SINGLETON_INSTANCE.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SINGLETON_INSTANCE = null;
            }
        }
    }

    public static Connection getConnection() {
        if (SINGLETON_INSTANCE == null) {
            ClassLoader classLoader = ConnectionManager.class.getClassLoader();
            try (InputStream input = classLoader.getResourceAsStream("application.properties")) {
                Properties properties = new Properties();
                properties.load(input);

                String driverClassName = properties.getProperty("database.driverClass");
                loadDriver(driverClassName);

                String url = properties.getProperty("database.url");
                String user = properties.getProperty("database.user");
                String password = properties.getProperty("database.password");
                SINGLETON_INSTANCE = DriverManager.getConnection(url, user, password);
                SINGLETON_INSTANCE.setAutoCommit(false);

            } catch (SQLException e) {
                System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
            } catch (FileNotFoundException e) {
                System.err.println("fichier de configuration non trouvé");
            } catch (IOException e) {
                System.err.println("Une erreur IO est survenue");
            }
        }
        return SINGLETON_INSTANCE;
    }


}

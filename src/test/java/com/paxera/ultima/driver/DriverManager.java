package com.paxera.ultima.driver;

import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.SQLException;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<WebDriver>();

    public DriverManager() {
    }

    public static WebDriver getWebDriver() {
        return webDriverThreadLocal.get();
    }

    public static void setWebDriver(WebDriver webDriver) {
        webDriverThreadLocal.set(webDriver);
    }

    public static void unLoad() {
        webDriverThreadLocal.remove();
    }

    public static Connection getConnection(String dbConnectionURL, String dbUserName, String dbPassword) throws SQLException {
        Connection connection = null;
        try {
            // Load the driver (optional for newer versions of JDBC)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            connection = java.sql.DriverManager.getConnection(dbConnectionURL, dbUserName, dbPassword);
            System.out.println("Connection established successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }

        return connection;
    }

}

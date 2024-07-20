package com.rd.Database;
import org.jdbi.v3.core.Jdbi;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnection {
    private static Jdbi jdbi;

    static {
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
            } else {
                prop.load(input);
                String url = prop.getProperty("url");
                String username = prop.getProperty("username");
                String password = prop.getProperty("password");
                jdbi = Jdbi.create(url, username, password);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static Jdbi getJdbi() {
        return jdbi;
    }
}

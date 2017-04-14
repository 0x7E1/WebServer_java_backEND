package com.server.web_server.dbService;

import com.server.web_server.dbService.dao.UsersDAO;
import com.server.web_server.dbService.dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author mr_robot
 * @since 3/10/17
 */
public class DBService {
    private final Connection connection;

    public DBService() { connection = getMySqlConnection(); }

    public UsersDataSet getUser(String name) throws DBException {
        try {
            return (new UsersDAO(connection).getUserByName(name));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addUser(UsersDataSet user) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(user.getLogin(), user.getPassword());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void createTable() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void cleanUp() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void printConnectInfo() {
        try {
            System.err.println("\nDB name: " + connection.getMetaData().getDatabaseProductName());
            System.err.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.err.println("Driver: " + connection.getMetaData().getDriverName());
            System.err.println("Autocommit: " + (connection.getAutoCommit() ? "enable\n" : "disable\n"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMySqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("mysqldb?").
                    append("user=root&").
                    append("password=psvm_stringA13");

            return DriverManager.getConnection(url.toString());

        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

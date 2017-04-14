package com.server.web_server.dbService.dao;

import com.server.web_server.dbService.dataSets.UsersDataSet;
import com.server.web_server.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author mr_robot
 * @since 3/10/17
 */
public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("SELECT * FROM users WHERE login=" + name, result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public UsersDataSet getUserByName(String name) throws SQLException {
        return  executor.execQuery("SELECT * FROM users WHERE login='" + name + "'", result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    public void insertUser(String name, String password) throws SQLException {
        executor.execUpdate("INSERT INTO users (login, password) VALUES ('" + name + "', '" + password + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id));");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE users");
    }
}

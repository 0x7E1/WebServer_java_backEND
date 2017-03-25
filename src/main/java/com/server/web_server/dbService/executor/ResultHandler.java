package com.server.web_server.dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mr_robot
 * @since 3/10/17
 */
public interface ResultHandler<T> {
    T handle(ResultSet result) throws SQLException;
}

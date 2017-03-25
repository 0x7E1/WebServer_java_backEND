package com.server.web_server.main;

import com.server.web_server.dbService.DBException;
import com.server.web_server.dbService.DBService;
import com.server.web_server.dbService.dataSets.UsersDataSet;

/**
 * @author mr_robot
 * @since 02/23/17
 */

public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("Pavel");
            System.out.println("Added user ID: " + userId);


            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

            dbService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}

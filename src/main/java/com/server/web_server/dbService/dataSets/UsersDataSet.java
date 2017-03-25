package com.server.web_server.dbService.dataSets;

/**
 * @author mr_robot
 * @since 3/10/17
 */

@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private long id;
    private String name;

    public UsersDataSet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
       return "UserDataSet{" +
               "id:" + id +
               ", name:'" + name + '\'' +
               '}';
    }
}

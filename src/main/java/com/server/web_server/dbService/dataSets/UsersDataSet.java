package com.server.web_server.dbService.dataSets;

/**
 * @author mr_robot
 * @since 3/10/17
 */

@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private long id;
    private String login;
    private String password;

    public UsersDataSet(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
       return "UserDataSet {" +
               "id: " + id +
               ", login: '" + login + "'" +
               ", password: '" + password + "'\n" +
               "}";
    }
}

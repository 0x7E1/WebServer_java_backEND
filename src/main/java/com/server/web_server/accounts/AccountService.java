package com.server.web_server.accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mr_robot
 * @since 02/23/17
 */

public class AccountService {

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    // Add new users
    public void addNewUser(UserProfile userProfile) {}
    public void addSession(String sessionId, UserProfile userProfile) {}

    // Get user by Login or SessionID
    public UserProfile getUserByLogin(String login) { return loginToProfile.get(login); }
    public UserProfile getUserBySessionId(String sessionId) { return sessionIdToProfile.get(sessionId); }

    // Delete session
    public void deleteSession(String sessionId) {sessionIdToProfile.remove(sessionId); }

}

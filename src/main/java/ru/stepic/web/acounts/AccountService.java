package ru.stepic.web.acounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class AccountService {

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;


    @Autowired
    public AccountService(Map<String, UserProfile> loginToProfile, Map<String, UserProfile> sessionIdToProfile) {
        this.loginToProfile = loginToProfile;
        this.sessionIdToProfile = sessionIdToProfile;
    }


    public void addNewUser(UserProfile userProfile){
        loginToProfile.put(userProfile.getLoginIndex(1), userProfile);
    }

    public UserProfile getUserByLogin(String login){
        return loginToProfile.get(login);
    }
    public UserProfile getUserBySessionId(String sessionId){
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile){
        sessionIdToProfile.put(sessionId, userProfile);
    }
    public void deleteSession(String sessionId){
        sessionIdToProfile.remove(sessionId);
    }
}

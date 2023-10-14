package ru.stepic.web.acounts;

import org.springframework.stereotype.Component;

@Component
public class UserProfile {
    private final String [] login;
    private final String [] pass;
    private final String [] email;

    public UserProfile() {
        this.login = new String[]{"admin", "test"};
        this.pass = new String[]{"pass1", "pass2"};
        this.email = new String[]{"admin@pass", "test@pass"};
    }

//    public UserProfile(String login) {
//        this.login = login;
//        this.pass = login;
//        this.email = login;
//    }


    public String[] getLogin() {
        return login;
    }

    public String[] getPass() {
        return pass;
    }

    public String[] getEmail() {
        return email;
    }

    public String getLoginIndex(Integer index) {
        if (login.length >= index)
        return login[index-1];

        System.out.println("login.length<index индекс больше чем массив логинов");
        return null;
    }

    public String getPassIndex(Integer index) {
        if (pass.length >= index)
            return pass[index-1];

        System.out.println("pass.length<index индекс больше чем массив логинов");
        return null;
    }

    public String getEmailIndex(Integer index) {
        if (email.length >= index)
            return email[index-1];

        System.out.println("email.length<index индекс больше чем массив логинов");
        return null;
    }

}

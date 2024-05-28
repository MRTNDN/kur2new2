package com.westernyey.kur2.services;

public class AuthService {

    public static boolean authenticate(String username, String password) {
        UserDAO userDAO = new UserDAO();
        return userDAO.validateUser(username, password);
    }

    public static boolean register(String username, String password) {
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(username, password);
        return true; // Возвращаем true, чтобы показать успешную регистрацию
    }
}

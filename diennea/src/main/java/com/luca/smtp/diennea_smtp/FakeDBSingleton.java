package com.luca.smtp.diennea_smtp;


import com.luca.smtp.diennea_smtp.util.User;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class FakeDBSingleton {
    HashMap<String, User> fakeDB = new HashMap<>();

    private static FakeDBSingleton instance = null; // riferimento all' istanza

    private FakeDBSingleton() {
        this.createUser();
    }

    public static FakeDBSingleton getInstance() {
        if(instance == null){
            synchronized(FakeDBSingleton.class) {
                if( instance == null ){
                    instance = new FakeDBSingleton();
                }
            }
        }
        return instance;
    }

    private void createUser(){
        User user = new User();
        user.setEmail("lucarossi147@gmail.com");
        user.setPassword("a");
        this.fakeDB.put(user.getEmail(), user);
    }

    public boolean authenticate (String email, String password){
        if (fakeDB.containsKey(email)){
            User user = fakeDB.get(email);
            return user.getEmail().equals(email) && user.getPassword().equals(password);
        }
        return false;
    }

    public String getToken (String email){
       if (getUserIfExists(email).isPresent()){
           User user = getUserIfExists(email).get();
           String token = createToken();
           user.setToken(token);
           return token;
       }
       return null;
    }

    private Optional<User> getUserIfExists (String email){
        if (fakeDB.containsKey(email)){
            return Optional.of(fakeDB.get(email));
        }
        return Optional.empty();
    }

    private String createToken(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public boolean isLoggedIn(String email, String token){
        if(getUserIfExists(email).isPresent()){
            User user = getUserIfExists(email).get();
            return user.getToken().equals(token);
        }
        return false;
    }
}

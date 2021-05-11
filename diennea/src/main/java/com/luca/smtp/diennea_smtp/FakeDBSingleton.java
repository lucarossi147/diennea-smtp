package com.luca.smtp.diennea_smtp;


import com.luca.smtp.diennea_smtp.util.User;

import java.util.HashMap;
import java.util.Optional;

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
        user.setToken("poop");
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
//        byte[] array = new byte[10]; // length is bounded by 10
//        new Random().nextBytes(array);
//        return new String(array, StandardCharsets.UTF_8);
        return "poop";
    }

    public boolean isLoggedIn(String email, String token){
        if(getUserIfExists(email).isPresent()){
            User user = getUserIfExists(email).get();
            return user.getToken().equals(token);
        }
        return false;
    }
}

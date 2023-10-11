package controllers;

import models.Client;

public class AuthController {
    Client client;
    String host;
    public AuthController() {
        client = new Client();
        host = "http://[::1]:8000/api/";
        
    }    
    public void login() {
        String endpoint = "login";
        String url = this.host + endpoint;
        String body = "{ \"name\": \"janos\", " +
         "\"password\": \"titok\" }";
        System.out.println(body);
        System.out.println(client.post(url, body).join());        
    }
    public void register() {
        String endpoint = "register";
        String url = this.host + endpoint;
        String body = "{ \"name\": \"janos\", " +
        "\"email\": \"janos@zold.lan\", " +
        "\"password\": \"titok\", " +
        "\"password_confirmation\": \"titok\" }";
        System.out.println(body);
        System.out.println(client.post(url, body).join());        
    }
}

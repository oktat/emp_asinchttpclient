package controllers;

import models.Client;

public class EmployeeController {
    Client client;
    String host;
    String endpoint;
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjk3MDUzMDQ2LCJleHAiOjE2OTcxMzk0NDZ9.S_TENQKeajo4KxdaJBUSymv5IJQBhfufCE4suGidGNo";

    public EmployeeController() {
        client = new Client();
        host = "http://[::1]:8000/api/";
        endpoint = "employees";
    }
    public void index() {        
        String uri = this.host + this.endpoint;
        System.out.println(client.get(uri).join());        
    }
    public void create() {
        String url = this.host + this.endpoint;
        String body = "{ \"name\": \"Rendes Emese\", " +
         "\"city\": \"Nyíregyháza\", " +
         "\"salary\": 378 }";
        System.out.println(body);
        System.out.println(client.post(url, body, token).join());
    }
    public void update() {
        String url = this.host + this.endpoint + "/2";
        String body = "{ \"name\": \"Csőr Ferenc\", " +
         "\"city\": \"Hatvan\", " +
         "\"salary\": 375 }";
        System.out.println(body);
        System.out.println(client.put(url, body, token).join());
    }
    public void delete() {
        String url = this.host + this.endpoint + "/3";
        System.out.println(client.delete(url, token).join());
    }
}

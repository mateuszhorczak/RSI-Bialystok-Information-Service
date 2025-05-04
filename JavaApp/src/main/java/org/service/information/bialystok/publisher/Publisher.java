package org.service.information.bialystok.publisher;

import jakarta.xml.ws.Endpoint;
import org.service.information.bialystok.service.EventServiceImpl;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/eventservice", new EventServiceImpl());
        System.out.println("Web service published at http://localhost:8080/eventservice");
    }
}

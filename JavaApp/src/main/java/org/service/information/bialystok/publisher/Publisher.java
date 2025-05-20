package org.service.information.bialystok.publisher;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import jakarta.xml.ws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.soap.MTOMFeature;
import org.service.information.bialystok.service.EventServiceImpl;

import javax.net.ssl.SSLContext;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

public class Publisher {
    public static void main(String[] args) throws Exception {
        // 1. Załaduj keystore
        char[] password = "changeit".toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(Publisher.class.getResourceAsStream("/keystore.p12"), password);

        // 2. Skonfiguruj KeyManager
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password);

        // 3. Utwórz SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        // 4. Utwórz i skonfiguruj serwer HTTPS
        HttpsServer server = HttpsServer.create(new InetSocketAddress(8443), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));

        // 5. Opublikuj endpoint
        Endpoint endpoint = Endpoint.create(
                new EventServiceImpl(),
                new MTOMFeature(true) // Wymuś MTOM
        );
        endpoint.publish(server.createContext("/eventservice"));

        // 6. Uruchom serwer
        server.start();
        System.out.println("Service published at https://localhost:8443/eventservice");
    }
}
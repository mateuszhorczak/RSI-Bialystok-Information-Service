package org.service.information.bialystok.handler;

import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.util.Set;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        try {
            Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            String direction = isOutbound != null && isOutbound ? "OUT" : "IN";

            SOAPMessage msg = context.getMessage();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            msg.writeTo(out);

            System.out.println("\n=== SOAP " + direction + "BOUND MESSAGE ===");
            System.out.println(out.toString("UTF-8"));

        } catch (Exception e) {
            System.err.println("Error handling SOAP message: ");
            e.printStackTrace(); // Tymczasowo dla debugowania
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("=== SOAP FAULT ===");
        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("Closing handler");
    }
}

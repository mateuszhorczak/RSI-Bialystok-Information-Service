package org.service.information.bialystok.handler;

import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPBinding;

import javax.xml.namespace.QName;
import java.util.Set;

public class MTOMHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
       // Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
       // if (outbound != null && outbound) {
        //    SOAPBinding binding = (SOAPBinding) context.get(SOAPBinding.);
        //    if (binding != null) {
        //        binding.setMTOMEnabled(true);
        //    }
      //  }
       // return true;
        System.out.println("Handling MTOMHandler message");
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {}

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}
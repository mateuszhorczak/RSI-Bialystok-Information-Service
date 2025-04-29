package org.service.information.bialystok.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlMimeType;
import org.service.information.bialystok.model.Event;

import java.time.LocalDate;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface EventService {
    @WebMethod
    List<Event> getEventsByDay(@WebParam(name = "date") LocalDate date);

    @WebMethod
    List<Event> getEventsByWeek(@WebParam(name = "week") int week, @WebParam(name = "year") int year);

    @WebMethod
    Event getEventInfo(@WebParam(name = "eventName") String eventName);

    @WebMethod
    void addEvent(@WebParam(name = "event") Event event);

    @WebMethod
    void updateEvent(@WebParam(name = "event") Event event);

    @WebMethod
    @XmlMimeType("application/pdf")
    byte[] getEventsReportPDF(@WebParam(name = "month") int month, @WebParam(name = "year") int year);
}

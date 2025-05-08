package org.service.information.bialystok.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.service.information.bialystok.model.Event;

import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface EventService {
    @WebMethod
    List<Event> getEventsByDate(@WebParam(name = "date") String date);

    @WebMethod
    List<Event> getEventsByWeek(@WebParam(name = "week") int week, @WebParam(name = "year") int year);

    @WebMethod
    List<Event> getEventsByName(@WebParam(name = "name") String name);

    @WebMethod
    void addEvent(@WebParam(name = "event") Event event);

    @WebMethod
    void updateEvent(@WebParam(name = "event") Event event);

    @WebMethod
    byte[] getEventsReportPDF(@WebParam(name = "month") int month, @WebParam(name = "year") int year);

    @WebMethod
    Event getEventById(@WebParam(name = "id") int id);
}

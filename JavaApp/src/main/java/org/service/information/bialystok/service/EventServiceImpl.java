package org.service.information.bialystok.service;

import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.HandlerChain;
import jakarta.xml.ws.soap.MTOM;
import org.service.information.bialystok.model.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.service.information.bialystok.service.EventService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@MTOM(enabled = true, threshold = 1024)
@HandlerChain(file = "handler-chain.xml")
public class EventServiceImpl implements EventService {
    private List<Event> events = new ArrayList<>();

    @Override
    public List<Event> getEventsByDay(LocalDate date) {
        return events.stream()
                .filter(event -> event.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsByWeek(int week, int year) {
        return events.stream()
                .filter(event -> event.getWeek() == week && event.getYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public Event getEventInfo(String eventName) {
        return events.stream()
                .filter(event -> event.getName().equals(eventName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public void updateEvent(Event event) {
        Event existingEvent = getEventInfo(event.getName());
        if (existingEvent != null) {
            events.remove(existingEvent);
            events.add(event);
        }
    }

    @Override
    public byte[] getEventsReportPDF(int month, int year) {
        // Tu można dodać logikę generowania PDF, np. za pomocą iText
        //return new byte[0]; // Placeholder
            List<Event> filteredEvents = events.stream()
                    .filter(event -> event.getMonth() == month && event.getYear() == year)
                    .collect(Collectors.toList());

            try {
                EventsReportPdfGenerator generator = new EventsReportPdfGenerator();
                return generator.generate(filteredEvents);
            } catch (IOException e) {
                e.printStackTrace();
                return new byte[0];
            }
        }

}


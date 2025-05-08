package org.service.information.bialystok.service;

import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.HandlerChain;
import jakarta.xml.ws.soap.MTOM;
import org.service.information.bialystok.model.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.service.information.bialystok.service.EventService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@MTOM(enabled = true, threshold = 1024)
@HandlerChain(file = "handler-chain.xml")
public class EventServiceImpl implements EventService {
    private List<Event> events = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Event> getEventsByDate(String date) {
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
    public List<Event> getEventsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
//            return Collections.emptyList();
            return events.stream().toList();
        }
        String searchTerm = name.toLowerCase();

        return events.stream()
                .filter(event -> event.getName() != null &&
                        event.getName().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    @Override
    public void addEvent(Event event) {
        event.setId(nextId);
        events.add(event);
        nextId++;
    }

    @Override
    public void updateEvent(Event event) {
        Event existingEvent = getEventById(event.getId());
        if (existingEvent != null) {
            events.remove(existingEvent);
            events.add(event);
        }
    }

    @Override
    public byte[] getEventsReportPDF(int month, int year) {
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

    @Override
    public Event getEventById(int id) {
        return events.stream()
                .filter(event -> event.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
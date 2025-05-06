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
            return Collections.emptyList();
        }
        String searchTerm = name.toLowerCase();

        return events.stream()
                .filter(event -> event.getName() != null &&
                        event.getName().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public void updateEvent(Event event) { // TODO: to nie bedzie dzialac, trzeba dodac id i nowa metoda by pobierało po id
        List<Event> existingEvent = getEventsByName(event.getName());
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


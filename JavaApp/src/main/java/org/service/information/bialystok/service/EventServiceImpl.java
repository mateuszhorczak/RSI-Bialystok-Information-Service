package org.service.information.bialystok.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.HandlerChain;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.ws.soap.MTOM;
import org.service.information.bialystok.model.Event;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;;

@WebService(endpointInterface = "org.service.information.bialystok.service.EventService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@MTOM(enabled = true, threshold = 0)
//@HandlerChain(file = "handler-chain.xml") TODO: bez tego dziala, a z tym nie - naprawic
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
    @XmlMimeType("application/pdf")
    public byte[] getEventsReportPDF(int month, int year) {
        try {
            List<Event> filtered = events.stream()
                    .filter(e -> e.getMonth() == month && e.getYear() == year)
                    .collect(Collectors.toList());

            // Log the details of the PDF generation TODO: remove
            System.out.println("Generating PDF for month: " + month + ", year: " + year);
            System.out.println("Events count: " + filtered.size());
            filtered.forEach(e -> System.out.println(" - " + e.getName()));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();
            document.add(new Paragraph("Raport wydarzen - " + month + "/" + year));

            for (Event e : filtered) {
                document.add(new Paragraph(e.getName() + " - " + e.getDate()));
            }

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Błąd generowania PDF", e);
        }
    }
}

package org.service.information.bialystok.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {
    @XmlElement(defaultValue = "0")
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private String type;
    @XmlElement
    private String date;
    @XmlElement
    private int week;
    @XmlElement
    private int month;
    @XmlElement
    private int year;
    @XmlElement
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Event() {
    }

    public Event(int id, String name, String type, String date, int week, int month, int year, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.week = week;
        this.month = month;
        this.year = year;
        this.description = description;
    }
}

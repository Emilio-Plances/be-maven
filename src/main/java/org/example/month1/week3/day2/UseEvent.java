package org.example.month1.week3.day2;

import org.example.dao.EventDao;
import org.example.entities.Event;
import org.example.entities.TypeEvent;

import java.time.LocalDate;

public class UseEvent {
    public static void main(String[] args) {
        EventDao event=new EventDao();

        LocalDate data=LocalDate.of(2024,1,28);
        Event e=new Event("Prove Concerto",data,"Le prove di un bel concerto", TypeEvent.PUBLIC,500);

        System.out.println(event.addEvent(e));

        System.out.println(event.getEventById(1));
        event.deleteEvent(1);
    }
}

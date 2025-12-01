package dao;

import model.Event;
import java.util.List;

public interface EventDao {
    void save(Event event);
    List<Event> findAll();
}

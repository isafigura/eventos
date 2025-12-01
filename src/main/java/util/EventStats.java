package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Event; // não esqueça este import!

public class EventStats {
    public static Map<String, Integer> countByCategory(List<Event> events) {
        Map<String, Integer> map = new HashMap<>();
        for (Event e : events) {
            map.merge(e.getCategory(), 1, Integer::sum);
        }
        return map;
    }
}

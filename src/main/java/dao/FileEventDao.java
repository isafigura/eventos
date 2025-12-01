package dao;

import model.Event;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileEventDao implements EventDao {

    private static final Path PATH = Paths.get("data/events.txt");

    public FileEventDao() {
        try {
            Files.createDirectories(PATH.getParent());
            if (Files.notExists(PATH)) Files.createFile(PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> findAll() {
        List<Event> list = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(PATH);

            for (String line : lines) {
                String[] p = line.split(";");

                // Formato: name ; date ; description ; category
                if (p.length == 4) {
                    Event e = new Event(p[0], p[1]);
                    e.setDescription(p[2]);
                    e.setCategory(p[3]); // agora lê a categoria
                    list.add(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH.toFile(), true))) {
            writer.write(
                    event.getName() + ";" +
                            event.getDate() + ";" +
                            event.getDescription() + ";" +
                            event.getCategory()  // salva a categoria
            );
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

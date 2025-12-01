package test;

import dao.EventDao;
import dao.FileEventDao;
import model.Event;
import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class FileEventDaoTest {

    private EventDao eventDao;
    private final File file = new File("data/events.txt");

    @Before
    public void setup() throws IOException {
        // Cria pasta e limpa arquivo antes do teste
        file.getParentFile().mkdirs();
        if (file.exists()) file.delete();
        file.createNewFile();

        eventDao = new FileEventDao();
    }

    @Test
    public void testSaveEvent() throws IOException {
        String title = "Festival de Música";
        String date = "2025-12-15";
        String description = "Um festival com bandas locais";
        String category = "Cultura";

        Event event = new Event(title, date);
        event.setDescription(description);
        event.setCategory(category);
        eventDao.save(event);

        // Lê o arquivo para verificar se foi salvo
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            assertNotNull("Arquivo não deve estar vazio", line);

            String[] parts = line.split(";");
            assertEquals(title, parts[0]);
            assertEquals(date, parts[1]);
            assertEquals(description, parts[2]);
            assertEquals(category, parts[3]);

            System.out.println("Linha salva no arquivo: " + line);
        }
    }

    @After
    public void cleanup() {
        // Limpa o arquivo após o teste
        if (file.exists()) file.delete();
    }
}


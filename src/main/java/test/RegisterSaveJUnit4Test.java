package test;

import dao.FileUserDao;
import dao.UserDao;
import model.User;
import org.junit.*;
import util.HashUtil;

import java.io.*;
import static org.junit.Assert.*;

public class RegisterSaveJUnit4Test {

    private UserDao userDao;
    private final File file = new File("data/users.txt");

    @Before
    public void setup() throws IOException {
        file.getParentFile().mkdirs();
        if (file.exists()) file.delete();
        file.createNewFile();

        userDao = new FileUserDao();
    }

    @Test
    public void testRegisterAndSaveMD5() throws IOException {
        String name = "Isabela";
        String email = "isabela@hotmail.com";
        String passwordInput = "123";

        // Simula o RegisterController
        String hashedPassword = HashUtil.md5(passwordInput);
        System.out.println("Simulando cadastro:");
        System.out.println("Senha original: " + passwordInput);
        System.out.println("Senha MD5: " + hashedPassword);

        User user = new User(name, email, hashedPassword);
        userDao.save(user);

        // Lê o arquivo manualmente
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            assertNotNull("Arquivo não deve estar vazio", line);

            String[] parts = line.split(";");
            assertEquals(name, parts[0]);
            assertEquals(email, parts[1]);
            assertEquals(hashedPassword, parts[2]);

            System.out.println("Linha salva no arquivo: " + line);
        }
    }

    @After
    public void cleanup() {
        if (file.exists()) file.delete();
    }
}


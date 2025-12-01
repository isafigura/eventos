package dao;

import model.User;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUserDao implements UserDao {

    private static final Path PATH = Paths.get("data/users.txt");

    public FileUserDao() {
        try {
            Files.createDirectories(PATH.getParent());
            if (Files.notExists(PATH)) Files.createFile(PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(PATH);

            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 3)
                    list.add(new User(parts[0], parts[1], parts[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH.toFile(), true))) {
            writer.write(user.getName() + ";" + user.getEmail() + ";" + user.getPassword());
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByEmail(String email) {
        return findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}

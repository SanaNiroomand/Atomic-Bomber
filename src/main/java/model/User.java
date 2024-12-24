package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.ApplicationController;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();
    public static User loggedInUser = null;
    private String username;
    private String password;
    private int avatarNumber;
    private int topScore;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        avatarNumber = ApplicationController.random.nextInt(1, 5);
        allUsers.add(this);
        saveUsers();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        saveUsers();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        saveUsers();
    }

    public int getAvatarNumber() {
        return avatarNumber;
    }

    public void setAvatarNumber(int avatarNumber) {
        this.avatarNumber = avatarNumber;
        saveUsers();
    }

    public int getTopScore() {
        return topScore;
    }

    public void checkAndSetTopScore(int topScore) {
        if (this.topScore > topScore) {
            this.topScore = topScore;
            saveUsers();
        }
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void saveUsers() {
        try {
            ApplicationController.fileWriter = new FileWriter("/home/sana-niroomand/IdeaProjects/AtomicBomber/data/users.json");
            ApplicationController.fileWriter.write(new Gson().toJson(allUsers));
            ApplicationController.fileWriter.close();
            ApplicationController.fileWriter = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadUsers() {
        String userData;
        try {
            userData = new String(Files.readAllBytes(Paths.get("data/users.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!userData.isEmpty()) {
            allUsers = new Gson().fromJson(userData,
                    new TypeToken<List<User>>() {
                    }.getType()
            );
        }
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.username.equals(username)) return user;
        }
        return null;
    }

    public static void deleteUser(User user) {
        allUsers.remove(user);
        saveUsers();
    }
}

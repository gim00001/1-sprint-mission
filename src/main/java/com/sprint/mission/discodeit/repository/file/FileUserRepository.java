package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository
public class FileUserRepository implements UserRepository {
    private static final String FILE_PATH = "user.db";
    private final Map<UUID, User> store;

    public FileUserRepository() {
        this.store = load();
    }

    // 저장
    @Override
    public User save(User user) {
        store.put(user.getId(), user);
        saveToFile();
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return store.values().stream()
                .filter(user -> Objects.equals(user.getUsername(), username))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return store.values().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean existsByUsername(String username) {
        return store.values().stream()
                .anyMatch(user -> Objects.equals(user.getUsername(), username));
    }

    @Override
    public boolean existsByEmail(String email) {
        return store.values().stream()
                .anyMatch(user -> Objects.equals(user.getEmail(), email));
    }

    @Override
    public void deleteById(UUID id) {
        store.remove(id);
        saveToFile();
    }

    // --- 파일 저장/로딩 로직 ---

    @SuppressWarnings("unchecked")
    private Map<UUID, User> load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<UUID, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("User DB 파일 로딩 실패: " + e.getMessage());
            return new HashMap<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(store);
        } catch (IOException e) {
            throw new RuntimeException("User DB 파일 저장 오류: " + e.getMessage(), e);
        }
    }
}
package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import tn.esprit.spring.entities.User;

class UserRepositoryMock {
    private final ArrayList<User> users = new ArrayList<>();
    private long currentId = 0;

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++currentId);
            users.add(user);
        } else {
            users.replaceAll(existingUser -> 
                existingUser.getId().equals(user.getId()) ? user : existingUser);
        }
        return user;
    }

    public void deleteById(Long id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }

    public Optional<User> findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}

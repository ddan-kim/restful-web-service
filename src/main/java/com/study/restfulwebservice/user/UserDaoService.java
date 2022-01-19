package com.study.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "K", new Date(), "pass1", "501010-1111111"));
        users.add(new User(2, "A", new Date(), "pass2", "701010-1111111"));
        users.add(new User(3, "E", new Date(), "pass3", "901010-1111111"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }

    public User updateById(int id, User user) {
        User ordUser = this.findOne(id);

        if (ordUser != null) {
            ordUser.setName(user.getName());
        }

        return user;
    }
}

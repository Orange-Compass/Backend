package OrangeCompass.OrangeCompass.user.repository;

import OrangeCompass.OrangeCompass.user.domain.User;

import java.util.Collection;

public interface UserRepository {
    int addNewUser(User user);
    User findByEmail(String currentUserEmail);
}

package OrangeCompass.OrangeCompass.user.repository;

import OrangeCompass.OrangeCompass.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public int addNewUser(User user) {
        String sql = "INSERT INTO User (username, email, password, birth, type, login_type) values(:username,:email,:password,:birth, :type, :loginType)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public User findByEmail(String currentUserEmail) {
        String sql = "SELECT * FROM User WHERE email = :email";
        SqlParameterSource namedParameters = new MapSqlParameterSource("email",currentUserEmail);
        List<User> userList = namedParameterJdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<User>(User.class));
        //TODO:예외 처리
        return userList.get(0);
    }
}

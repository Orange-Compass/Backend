package OrangeCompass.OrangeCompass.user.repository;

import OrangeCompass.OrangeCompass.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
}

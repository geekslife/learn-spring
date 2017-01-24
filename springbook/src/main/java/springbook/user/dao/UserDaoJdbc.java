package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by geekslife on 2017. 1. 22..
 */
public class UserDaoJdbc implements UserDao {
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    JdbcTemplate jdbcTemplate;

    RowMapper<User> userMapper =
            (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLevel(Level.valueOf(rs.getInt("level")));
                user.setLogin(rs.getInt("login"));
                user.setRecommend(rs.getInt("recommend"));
                return user;
            };

    public void add(User user) {
        jdbcTemplate.update("insert into users (id, name, password, level, login, recommend) values (?,?,?,?,?,?)",
                user.getId(), user.getName(), user.getPassword(),user.getLevel().getValue(),user.getLogin(),user.getRecommend());
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from users");
    }


    public User get(String id) {
        return jdbcTemplate.queryForObject(
                "select * from users where id=?", new Object[] { id }, userMapper);
    }

    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public List<User> getAll() {
        return jdbcTemplate.query("select * from users order by id", userMapper);
    }
}

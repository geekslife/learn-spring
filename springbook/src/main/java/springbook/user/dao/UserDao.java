package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class UserDao {

    JdbcContext jdbcContext;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    DataSource dataSource;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    private User user;
    private Connection c;

    public void add(User user) throws ClassNotFoundException, SQLException {
        jdbcContext.workWithStatementStrategy(
                c -> {
                        PreparedStatement ps = c.prepareStatement(
                                "insert into users(id,name,password) values(?,?,?)");
                        ps.setString(1, user.getId());
                        ps.setString(2, user.getName());
                        ps.setString(3, user.getPassword());
                        return ps;
                }
        );
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        c = jdbcContext.getConnection();
        PreparedStatement ps =  this.c.prepareStatement("select * from users where id = ? " );
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            this.user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
        } else {
            throw new EmptyResultDataAccessException(1);
        }

        rs.close();
        ps.close();
        this.c.close();

        return this.user;
    }

    public void deleteAll() throws SQLException {
        jdbcContext.workWithStatementStrategy( c -> c.prepareStatement("delete from users") );
    }

    public int getCount() throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection c = null;
        try{
            c = jdbcContext.getConnection();
            ps = c.prepareStatement("select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs!=null) {
                try { rs.close(); } catch (SQLException e) {}
            }
            if (ps!=null) {
                try { ps.close(); } catch (SQLException e) {}
            }
            if (c!= null) {
                try { c.close(); } catch (SQLException e) {}
            }
        }
    }
}

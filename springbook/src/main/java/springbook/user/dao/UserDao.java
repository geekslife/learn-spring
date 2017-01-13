package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class UserDao {
    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private User user;
    private Connection c;

    public void add(User user) throws ClassNotFoundException, SQLException {
        c = dataSource.getConnection();
        PreparedStatement ps =  c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2, user.getName());
        ps.setString(3,user.getPassword());
        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        c = dataSource.getConnection();
        PreparedStatement ps =  this.c.prepareStatement("select * from users where id = ? " );
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        this.user = new User();
        this.user.setId( rs.getString("id"));
        this.user.setName( rs.getString("name"));
        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        this.c.close();

        return this.user;
    }
}

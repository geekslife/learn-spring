package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.*;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class UserDao {
    ConnectionMaker connectionMaker;
    private User user;
    private Connection c;

    public UserDao() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        c = connectionMaker.makeNewConnection();
        PreparedStatement ps =  c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2, user.getName());
        ps.setString(3,user.getPassword());
        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        this.c = connectionMaker.makeNewConnection();
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

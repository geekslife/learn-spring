package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class NUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return null;
    }
}

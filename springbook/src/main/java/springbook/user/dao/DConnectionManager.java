package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class DConnectionManager implements ConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        String connectionUrl = "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1;MVCC=TRUE";
        Connection c = DriverManager.getConnection(connectionUrl,"sa","");
        return c;
    }
}

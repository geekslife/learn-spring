package springbook.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by geekslife on 2017. 1. 15..
 */
public class JdbcContext {
    DataSource dataSource;

    // 코드에 의한 DI..
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException { return dataSource.getConnection(); }

    // 전략 패턴의 익명 클래스를 은닉..
    public void executeSql(final String query, String... args) throws SQLException {
        workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement(query);
                for( int i=0; i < args.length; i++ ) {
                    ps.setString(i+1, args[i]);
                }
                return ps;
            }
        });
    }

    // 전략 패턴..
    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps=null;
        try {
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps!=null) { try { ps.close(); } catch (SQLException e) { } }
            if (c!= null) { try { c.close(); } catch (SQLException e) {} }
        }
    }

}

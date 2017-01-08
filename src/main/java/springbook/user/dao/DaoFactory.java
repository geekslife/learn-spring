package springbook.user.dao;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public ConnectionMaker connectionMaker() {
        return new DConnectionManager();
    }
}

package springbook.user.domain;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class User {


    int login;
    public int getLogin() { return login; }
    public void setLogin(int login) { this.login = login; }

    int recommend;
    public int getRecommend() { return recommend; }
    public void setRecommend(int recommend) { this.recommend = recommend; }

    private Level level;
    public void setLevel(Level level) { this.level = level; }
    public Level getLevel() { return level; }


    String id;
    String name;
    String password;

    public User() {}

    public User(String id, String name, String password, Level level, int login, int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

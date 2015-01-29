package freeart;

public class User implements java.io.Serializable
{
    private Integer id;
    private String login;
    private String password;

    public User()
    {
    }

    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return this.login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}

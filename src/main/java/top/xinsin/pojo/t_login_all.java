package top.xinsin.pojo;

//登录实体类
public class t_login_all {
    private int sa_id; //编号
    private boolean isCan; //是否可以登录
    private String sa_name; //名字
    private String sa_account; //账号
    private String sa_password; //密码
    private String sa_lastLogin; //最后登录时间
    private String sa_authority; //权限

    public t_login_all() {
    }
    //用于传回前端的JSON格式化对象
    public t_login_all(int id,boolean isLogin, String name, String nowTime, String auth) {
        this.sa_id = id;
        this.isCan = isLogin;
        this.sa_name = name;
        this.sa_lastLogin = nowTime;
        this.sa_authority = auth;
    }

    //用于前端传回用户密码验证
    public t_login_all(String sa_account, String sa_password) {
        this.sa_account = sa_account;
        this.sa_password = sa_password;
    }

    public t_login_all(int sa_id, String sa_name, String sa_account, String sa_password, String sa_authority, String sa_lastLogin) {
        this.sa_id = sa_id;
        this.sa_name = sa_name;
        this.sa_account = sa_account;
        this.sa_password = sa_password;
        this.sa_lastLogin = sa_lastLogin;
        this.sa_authority = sa_authority;
    }

    public boolean isCan() {
        return isCan;
    }

    public void setCan(boolean isCan) {
        this.isCan = isCan;
    }

    public int getSa_id() {
        return sa_id;
    }

    public void setSa_id(int sa_id) {
        this.sa_id = sa_id;
    }

    public String getSa_name() {
        return sa_name;
    }

    public void setSa_name(String sa_name) {
        this.sa_name = sa_name;
    }

    public String getSa_account() {
        return sa_account;
    }

    public void setSa_account(String sa_account) {
        this.sa_account = sa_account;
    }

    public String getSa_password() {
        return sa_password;
    }

    public void setSa_password(String sa_password) {
        this.sa_password = sa_password;
    }

    public String getSa_lastLogin() {
        return sa_lastLogin;
    }

    public void setSa_lastLogin(String sa_lastLogin) {
        this.sa_lastLogin = sa_lastLogin;
    }

    public String getSa_authority() {
        return sa_authority;
    }

    public void setSa_authority(String sa_authority) {
        this.sa_authority = sa_authority;
    }

    @Override
    public String toString() {
        return "t_login_all{" +
                "sa_id=" + sa_id +
                ", sa_name='" + sa_name + '\'' +
                ", sa_account='" + sa_account + '\'' +
                ", sa_password='" + sa_password + '\'' +
                ", sa_lastLogin='" + sa_lastLogin + '\'' +
                ", sa_authority=" + sa_authority +
                '}';
    }

}

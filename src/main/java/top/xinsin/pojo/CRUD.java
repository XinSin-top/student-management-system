package top.xinsin.pojo;

public class CRUD {
    private int operationType;
    private int sa_id;
    private String sa_name;
    private String sa_account;
    private String sa_password;
    private String sa_authority;

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
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

    public String getSa_authority() {
        return sa_authority;
    }

    public void setSa_authority(String sa_authority) {
        this.sa_authority = sa_authority;
    }
}

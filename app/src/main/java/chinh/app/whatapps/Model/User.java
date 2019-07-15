package chinh.app.whatapps.Model;

public class User {
    private String phone;
    private String name;
    private String pass;
    private String sex;
    private String profileImageUrl;

    public User() {
    }

    public User(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public User(String phone, String name, String pass, String sex, String profileImageUrl) {
        this.phone = phone;
        this.name = name;
        this.pass = pass;
        this.sex = sex;
        this.profileImageUrl = profileImageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}

package dto;

public class User {

    String userID;
    String userName;
    String email;
    String phone;
    String image;
    String password;
    String status;
    String statusPromotion;
    String dateCreate;
    float rank;
    String roleID;
    String roleName;

    public User() {
    }

    public User(String userID, String userName, String email, String phone, String image, String password, String status, String statusPromotion, String dateCreate, float rank, String roleID, String roleName) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.password = password;
        this.status = status;
        this.statusPromotion = statusPromotion;
        this.dateCreate = dateCreate;
        this.rank = rank;
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public User(String userID, String userName, String email, String phone, String image, String password, String status, String statusPromotion, String dateCreate, float rank, String roleID) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.password = password;
        this.status = status;
        this.statusPromotion = statusPromotion;
        this.dateCreate = dateCreate;
        this.rank = rank;
        this.roleID = roleID;
    }

    public User(String userID, String userName, String password, String roleID, String roleName) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public User(String userID, String userName, String email, String phone, String image, String password, String roleID) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.password = password;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusPromotion() {
        return statusPromotion;
    }

    public void setStatusPromotion(String statusPromotion) {
        this.statusPromotion = statusPromotion;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

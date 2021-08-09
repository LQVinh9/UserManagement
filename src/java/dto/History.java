package dto;

public class History {

    String promotionHistoryID;
    String date;
    float rank;
    String status;
    String userID;
    String userName;
    String image;

    public History() {
    }

    public History(String promotionHistoryID, String date, float rank, String status, String userID) {
        this.promotionHistoryID = promotionHistoryID;
        this.date = date;
        this.rank = rank;
        this.status = status;
        this.userID = userID;
    }

    public History(String promotionHistoryID, String date, float rank, String status, String userID, String userName, String image) {
        this.promotionHistoryID = promotionHistoryID;
        this.date = date;
        this.rank = rank;
        this.status = status;
        this.userID = userID;
        this.userName = userName;
        this.image = image;
    }

    public String getPromotionHistoryID() {
        return promotionHistoryID;
    }

    public void setPromotionHistoryID(String promotionHistoryID) {
        this.promotionHistoryID = promotionHistoryID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

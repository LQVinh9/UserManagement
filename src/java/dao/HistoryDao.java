package dao;

import dto.History;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.log4j.Logger;
import util.DBUtil;

public class HistoryDao {

    private static final Logger LOGGER = Logger.getLogger(HistoryDao.class);

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public Vector<History> getAllHistory() throws SQLException, Exception {
        Vector<History> listHistory = new Vector<History>();

        try {
            con = DBUtil.getConnnection();
            if (con != null) {
                String sql = "SELECT promotionHistoryID,date,PromotionHistory.rank,PromotionHistory.status,PromotionHistory.userID,Users.userName,Users.image "
                        + "FROM PromotionHistory "
                        + "INNER JOIN Users ON Users.userID=PromotionHistory.userID "
                        + "ORDER BY date desc";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    listHistory.add(new History(rs.getString("promotionHistoryID"), rs.getString("date").substring(0, 19), rs.getFloat("rank"),
                            rs.getString("status"), rs.getString("userID"), rs.getString("userName"), rs.getString("image")));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return listHistory;
    }

    public boolean createHistory(History history) throws SQLException, Exception {
        boolean check = false;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "Insert PromotionHistory Values(?,?,?,?,?)";

                ps = con.prepareStatement(sql);
                ps.setString(1, history.getPromotionHistoryID());
                ps.setString(2, history.getDate());
                ps.setFloat(3, history.getRank());
                ps.setString(4, history.getStatus());
                ps.setString(5, history.getUserID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return check;
    }

    public History getHistoryByID(String promotionHistoryID) throws SQLException, Exception {
        History history = null;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "SELECT promotionHistoryID,date,rank,status,userID "
                        + "FROM PromotionHistory "
                        + "Where promotionHistoryID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, promotionHistoryID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String date = rs.getString("date").substring(0, 19);
                    float rank = rs.getFloat("rank");
                    String status = rs.getString("status");
                    String userID = rs.getString("userID");

                    history = new History(promotionHistoryID, date, rank, status, userID);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return history;
    }

    public Vector<History> getAllHistoryByUser(String userID) throws SQLException, Exception {
        Vector<History> listHistory = new Vector<History>();

        try {
            con = DBUtil.getConnnection();
            if (con != null) {
                String sql = "SELECT promotionHistoryID,date,rank,status,userID "
                        + "FROM PromotionHistory "
                        + "WHERE userID=? "
                        + "ORDER BY date desc";

                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    listHistory.add(new History(rs.getString("promotionHistoryID"), rs.getString("date").substring(0, 19),
                            rs.getFloat("rank"), rs.getString("status"), rs.getString("userID")));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return listHistory;
    }
}

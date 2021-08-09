package dao;

import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.log4j.Logger;
import util.DBUtil;

public class UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

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

    public Vector<User> getAllUser() throws SQLException, Exception {
        Vector<User> listUser = new Vector<User>();

        try {
            con = DBUtil.getConnnection();
            if (con != null) {
                String sql = "SELECT userID,userName,email,phone,image,password,status,statusPromotion,dateCreate,rank,Users.roleID,roleName "
                        + "FROM Users "
                        + "INNER JOIN Role ON Users.roleID=Role.roleID";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    listUser.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"), rs.getString("phone"),
                            rs.getString("image"), rs.getString("password"), rs.getString("status"), rs.getString("statusPromotion"),
                            rs.getString("dateCreate").substring(0, 19), rs.getFloat("rank"), rs.getString("roleID"), rs.getString("roleName")));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return listUser;
    }

    public Vector<User> getAllAdmin() throws SQLException, Exception {
        Vector<User> listAdmin = new Vector<User>();

        try {
            con = DBUtil.getConnnection();
            if (con != null) {
                String sql = "SELECT userID,userName,email,phone,image,password,status,statusPromotion,dateCreate,rank,Users.roleID,roleName "
                        + "FROM Users "
                        + "INNER JOIN Role ON Users.roleID=Role.roleID "
                        + "WHERE Users.roleID='A'";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    listAdmin.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"), rs.getString("phone"),
                            rs.getString("image"), rs.getString("password"), rs.getString("status"), rs.getString("statusPromotion"),
                            rs.getString("dateCreate").substring(0, 19), rs.getFloat("rank"), rs.getString("roleID"), rs.getString("roleName")));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return listAdmin;
    }

    public Vector<User> getAllSub() throws SQLException, Exception {
        Vector<User> listSub = new Vector<User>();

        try {
            con = DBUtil.getConnnection();
            if (con != null) {
                String sql = "SELECT userID,userName,email,phone,image,password,status,statusPromotion,dateCreate,rank,Users.roleID,roleName "
                        + "FROM Users "
                        + "INNER JOIN Role ON Users.roleID=Role.roleID "
                        + "WHERE Users.roleID='S'";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    listSub.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"), rs.getString("phone"),
                            rs.getString("image"), rs.getString("password"), rs.getString("status"), rs.getString("statusPromotion"),
                            rs.getString("dateCreate").substring(0, 19), rs.getFloat("rank"), rs.getString("roleID"), rs.getString("roleName")));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return listSub;
    }

    public Vector<User> getAllUserSearch(String nameSearch) throws SQLException, Exception {
        Vector<User> listUser = new Vector<User>();

        try {
            con = DBUtil.getConnnection();
            if (con != null) {
                String sql = "SELECT userID,userName,email,phone,image,password,status,statusPromotion,dateCreate,rank,Users.roleID,roleName "
                        + "FROM Users "
                        + "INNER JOIN Role ON Users.roleID=Role.roleID "
                        + "WHERE userName LIKE ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + nameSearch + "%");

                rs = ps.executeQuery();

                while (rs.next()) {
                    listUser.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"), rs.getString("phone"),
                            rs.getString("image"), rs.getString("password"), rs.getString("status"), rs.getString("statusPromotion"),
                            rs.getString("dateCreate").substring(0, 19), rs.getFloat("rank"), rs.getString("roleID"), rs.getString("roleName")));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return listUser;
    }

    public User checkLogin(String userID, String password) throws Exception {
        User user = null;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "Select userID,userName,Users.roleID,roleName From Users "
                        + "INNER JOIN Role ON Users.roleID=Role.roleID "
                        + "Where userID=? AND password=? AND status='Active'";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);

                rs = ps.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("userID");
                    String name = rs.getString("userName");
                    String roleID = rs.getString("roleID");
                    String roleName = rs.getString("roleName");
                    user = new User(email, name, password, roleID, roleName);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return user;
    }

    public User checkLoginAccountNotActivated(String userID, String password) throws Exception {
        User user = null;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "Select userID,userName,Users.roleID,roleName From Users "
                        + "INNER JOIN Role ON Users.roleID=Role.roleID "
                        + "Where userID=? AND password=? AND status='Inactive'";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);

                rs = ps.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("userID");
                    String name = rs.getString("userName");
                    String roleID = rs.getString("roleID");
                    String roleName = rs.getString("roleName");
                    user = new User(email, name, password, roleID, roleName);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return user;
    }

    public boolean createUser(User user) throws SQLException, Exception {
        boolean check = false;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "Insert Users Values(?,?,?,?,?,?,?,?,?,?,?)";

                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUserID());
                ps.setString(2, user.getUserName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPhone());
                ps.setString(5, user.getImage());
                ps.setString(6, user.getPassword());
                ps.setString(7, user.getStatus());
                ps.setString(8, user.getStatusPromotion());
                ps.setString(9, user.getDateCreate());
                ps.setFloat(10, user.getRank());
                ps.setString(11, user.getRoleID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteUser(String userID) throws SQLException, Exception {
        boolean check = false;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "UPDATE Users SET status='Inactive' WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean activeUser(String userID) throws SQLException, Exception {
        boolean check = false;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "UPDATE Users SET status='Active' WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return check;
    }

    public User getUserByID(String userID) throws SQLException, Exception {
        User user = null;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "SELECT userID,userName,email,phone,image,password,status,statusPromotion,dateCreate,rank,roleID  "
                        + "From Users Where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String image = rs.getString("image");
                    String password = rs.getString("password");
                    String status = rs.getString("status");
                    String statusPromotion = rs.getString("statusPromotion");
                    String dateCreate = rs.getString("dateCreate").substring(0, 19);
                    float rank = rs.getFloat("rank");
                    String roleID = rs.getString("roleID");

                    user = new User(userID, userName, email, phone, image, password, status, statusPromotion, dateCreate, rank, roleID);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return user;
    }

    public boolean updateUser(User user) throws SQLException, Exception {
        boolean check = false;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "UPDATE Users SET userName=?,password=?,email=?,phone=?,roleID=?,image=? "
                        + "WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPhone());
                ps.setString(5, user.getRoleID());
                ps.setString(6, user.getImage());
                ps.setString(7, user.getUserID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean addPromotion(String userID) throws SQLException, Exception {
        boolean check = false;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "UPDATE Users SET statusPromotion='True',rank=5.0 "
                        + "WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return check;
    }

    public Vector<User> getAllUserHavePromotion() throws SQLException, Exception {
        Vector<User> listUser = new Vector<User>();

        try {
            con = DBUtil.getConnnection();
            if (con != null) {
                String sql = "SELECT userID,userName,email,phone,image,password,status,statusPromotion,dateCreate,rank,Users.roleID,roleName "
                        + "FROM Users "
                        + "INNER JOIN Role ON Users.roleID=Role.roleID "
                        + "WHERE statusPromotion = 'True'";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    listUser.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"), rs.getString("phone"),
                            rs.getString("image"), rs.getString("password"), rs.getString("status"), rs.getString("statusPromotion"),
                            rs.getString("dateCreate").substring(0, 19), rs.getFloat("rank"), rs.getString("roleID"), rs.getString("roleName")));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return listUser;
    }

    public boolean updatePromotion(String userID, float rank) throws SQLException, Exception {
        boolean check = false;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "UPDATE Users SET rank=? "
                        + "WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setFloat(1, rank);
                ps.setString(2, userID);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteUserPromotion(String userID) throws SQLException, Exception {
        boolean check = false;
        try {
            con = DBUtil.getConnnection();

            if (con != null) {
                String sql = "UPDATE Users SET statusPromotion='False',rank=0 "
                        + "WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return check;
    }
}

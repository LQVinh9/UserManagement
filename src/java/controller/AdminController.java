/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HistoryDao;
import dao.UserDao;
import dto.History;
import dto.User;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

/**
 *
 * @author Vinh
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String EDIT = "edit.jsp";
    private static final String PROMOTION = "promotion.jsp";
    private static final String HISTORY_ADMIN = "historyAdmin.jsp";
    private static final String WELCOME = "welcome.jsp";

    private static final String ADMIN_CONTROLLER = "AdminController";

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RND = new SecureRandom();

    private static final Logger LOGGER = Logger.getLogger(AdminController.class);

    private String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(RND.nextInt(AB.length())));
        }
        return sb.toString();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            User userSession = (User) session.getAttribute("user");

            if (userSession.getRoleID().equals("A")) {
                String action = request.getParameter("action");

                UserDao userDao = new UserDao();
                HistoryDao historyDao = new HistoryDao();

                if (action == null) {
                    Vector<User> listUser = userDao.getAllUser();
                    request.setAttribute("listUser", listUser);

                    url = ADMIN;
                } else if (action.equals("Admin")) {
                    Vector<User> listUser = userDao.getAllAdmin();
                    request.setAttribute("listUser", listUser);

                    url = ADMIN;
                } else if (action.equals("Sub")) {
                    Vector<User> listUser = userDao.getAllSub();
                    request.setAttribute("listUser", listUser);

                    url = ADMIN;
                } else if (action.equals("Search")) {
                    String searchName = request.getParameter("searchName");
                    Vector<User> listUser = userDao.getAllUserSearch(searchName);
                    request.setAttribute("listUser", listUser);

                    url = ADMIN;
                } else if (action.equals("delete")) {
                    String userID = request.getParameter("userID");
                    userDao.deleteUser(userID);

                    url = ADMIN_CONTROLLER + "?messDelete=DeleteSuccessful";
                    response.sendRedirect(url);
                    return;
                } else if (action.equals("active")) {
                    String userID = request.getParameter("userID");
                    userDao.activeUser(userID);

                    url = ADMIN_CONTROLLER + "?messActive=ActiveSuccessful";
                    response.sendRedirect(url);
                    return;
                } else if (action.equals("Edit")) {
                    String userID = request.getParameter("userID");
                    User user = userDao.getUserByID(userID);
                    request.setAttribute("user", user);

                    url = EDIT;
                } else if (action.equals("addPromotion")) {
                    String userID = request.getParameter("userID");
                    userDao.addPromotion(userID);

                    String promotionHistoryID;
                    do {
                        promotionHistoryID = randomString(5);
                    } while (historyDao.getHistoryByID(promotionHistoryID) != null);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String date = dtf.format(now);
                    float rank = 5;
                    String status = "Added";

                    History history = new History(promotionHistoryID, date, rank, status, userID);
                    historyDao.createHistory(history);

                    url = ADMIN_CONTROLLER + "?messAdd=AddSuccessful";
                    response.sendRedirect(url);
                    return;
                } else if (action.equals("listPromotion")) {
                    Vector<User> listUser = userDao.getAllUserHavePromotion();
                    request.setAttribute("listUser", listUser);

                    url = PROMOTION;
                } else if (action.equals("updatePromotion")) {
                    String[] userID = request.getParameterValues("userID");
                    String[] rank = request.getParameterValues("rank");

                    for (int i = 0; i < userID.length; i++) {
                        User user = userDao.getUserByID(userID[i]);

                        if (user.getRank() != Float.parseFloat(rank[i])) {
                            userDao.updatePromotion(userID[i], Float.parseFloat(rank[i]));

                            String promotionHistoryID;
                            do {
                                promotionHistoryID = randomString(5);
                            } while (historyDao.getHistoryByID(promotionHistoryID) != null);
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            String date = dtf.format(now);
                            float rankPromotion = Float.parseFloat(rank[i]);
                            String status = "Updated";

                            History history = new History(promotionHistoryID, date, rankPromotion, status, userID[i]);
                            historyDao.createHistory(history);
                        }
                    }
                    url = ADMIN_CONTROLLER + "?action=listPromotion&messUpdate=updateSuccessful";
                    response.sendRedirect(url);
                    return;
                } else if (action.equals("deletePromotion")) {
                    String userID = request.getParameter("userID");
                    userDao.deleteUserPromotion(userID);

                    String promotionHistoryID;
                    do {
                        promotionHistoryID = randomString(5);
                    } while (historyDao.getHistoryByID(promotionHistoryID) != null);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String date = dtf.format(now);
                    float rank = 0;
                    String status = "Deleted";

                    History history = new History(promotionHistoryID, date, rank, status, userID);
                    historyDao.createHistory(history);

                    url = ADMIN_CONTROLLER + "?action=listPromotion&messDelete=deleteSuccessful";
                    response.sendRedirect(url);
                    return;
                } else if (action.equals("history")) {
                    Vector<History> listHistory = historyDao.getAllHistory();
                    request.setAttribute("listHistory", listHistory);

                    url = HISTORY_ADMIN;
                }
            } else {
                url = WELCOME;
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            LOGGER.error(e);
            request.setAttribute("errorMess", e.toString());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

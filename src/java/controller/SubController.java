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
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Vinh
 */
@WebServlet(name = "SubController", urlPatterns = {"/SubController"})
public class SubController extends HttpServlet {

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
    private static final String SUB = "sub.jsp";
    private static final String HISTORY = "history.jsp";
    private static final String EDIT = "edit.jsp";
    private static final String WELCOME = "welcome.jsp";

    private static final Logger LOGGER = Logger.getLogger(SubController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            User userSession = (User) session.getAttribute("user");

            if (userSession.getRoleID().equals("S")) {
                String action = request.getParameter("action");

                UserDao userDao = new UserDao();
                HistoryDao historyDao = new HistoryDao();

                if (action == null) {
                    String userID = userSession.getUserID();
                    User user = userDao.getUserByID(userID);
                    request.setAttribute("user", user);

                    url = SUB;
                } else if (action.equals("history")) {
                    String userID = request.getParameter("userID");
                    Vector<History> listHistory = historyDao.getAllHistoryByUser(userID);
                    request.setAttribute("listHistory", listHistory);

                    url = HISTORY;
                } else if (action.equals("Edit")) {
                    String userID = request.getParameter("userID");
                    User user = userDao.getUserByID(userID);
                    request.setAttribute("user", user);

                    url = EDIT;
                }
            } else {
                url = WELCOME;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            request.setAttribute("errorMess", e.toString());
        } finally {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import dto.ErrorUser;
import dto.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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
@WebServlet(name = "InsertController", urlPatterns = {"/InsertController"})
public class InsertController extends HttpServlet {

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
    private static final String INSERT = "insert.jsp";
    private static final String WELCOME = "welcome.jsp";

    private static final String ADMIN_CONTROLLER = "AdminController";

    private static final Logger LOGGER = Logger.getLogger(InsertController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            User userSession = (User) session.getAttribute("user");

            if (userSession.getRoleID().equals("A")) {
                UserDao userDao = new UserDao();

                String userID = request.getParameter("userID").trim();
                String userName = request.getParameter("userName").trim();
                String password = request.getParameter("password").trim();
                String passwordConfirm = request.getParameter("passwordConfirm").trim();
                String email = request.getParameter("email").trim();
                String phone = request.getParameter("phone").trim();
                String roleID = request.getParameter("roleID").trim();

                ErrorUser errorUser = new ErrorUser();

                boolean check = true;
                if (userID.length() == 0) {
                    errorUser.setUserID("User ID không được rỗng");
                    check = false;
                }
                if (userDao.getUserByID(userID) != null) {
                    errorUser.setUserID("User ID đã tồn tại");
                    check = false;
                }
                if (userName.length() == 0) {
                    errorUser.setUserName("User name không được rỗng");
                    check = false;
                }
                if (userName.length() > 40) {
                    errorUser.setUserName("User name không được lớn hơn 40 kí tự");
                    check = false;
                }
                if (password.length() < 8) {
                    errorUser.setPassword("Password phải từ 8 kí tự trở đi");
                    check = false;
                }
                if (password.length() > 40) {
                    errorUser.setPassword("Password phải bé hơn 40 kí tự");
                    check = false;
                }
                if (!password.equals(passwordConfirm)) {
                    errorUser.setPasswordConfirm("Password không khớp nhau");
                    check = false;
                }
                if (isValidEmail(email) == false) {
                    errorUser.setEmail("Email không đúng định dạng");
                    check = false;
                }
                if (email.length() > 40) {
                    errorUser.setEmail("Email không lớn hơn 40 kí tự");
                    check = false;
                }
                if (isValidPhone(phone) == false) {
                    errorUser.setPhone("Phone phải là số");
                    check = false;
                }
                if (phone.length() < 10 || phone.length() > 12) {
                    errorUser.setPhone("Phone phải từ 10 đến 12 số");
                    check = false;
                }

                if (check == false) {
                    request.setAttribute("errorUser", errorUser);

                    url = INSERT;
                } else {
                    String image;
                    image = uploadFile(request);

                    String status = "Active";
                    String statusPromotion = "False";
                    float rank = 0;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String dateCreate = dtf.format(now);

                    User user = new User(userID, userName, email, phone, image, toHexString(getSHA(password)), status, statusPromotion, dateCreate, rank, roleID);
                    userDao.createUser(user);

                    url = ADMIN_CONTROLLER + "?messInsert=InsertSuccessful";
                    response.sendRedirect(url);
                    return;
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

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean isValidPhone(String email) {
        String regex = "^[0-9]*$";
        return email.matches(regex);
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName;

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);

            String applicationPath;
            String basePath;

            File outputFilePath;
            int read;
            byte[] bytes;
            applicationPath = request.getServletContext().getRealPath("");
            applicationPath = applicationPath.substring(0, applicationPath.length() - 10);
            basePath = applicationPath + "web\\image\\";

            outputFilePath = new File(basePath + fileName);
            inputStream = filePart.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);
            read = 0;
            bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            applicationPath = request.getServletContext().getRealPath("");
            basePath = applicationPath + "image\\";

            outputFilePath = new File(basePath + fileName);
            inputStream = filePart.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);
            read = 0;
            bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            fileName = "";
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return fileName;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
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

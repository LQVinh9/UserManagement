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
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

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
    private static final String EDIT = "edit.jsp";
    private static final String ADMIN_CONTROLLER = "AdminController";
    private static final String SUB_CONTROLLER = "SubController";
    private static final String WELCOME = "welcome.jsp";

    private static final Logger LOGGER = Logger.getLogger(UpdateController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String url = ERROR;

        try {
            UserDao userDao = new UserDao();

            HttpSession session = request.getSession();
            User userSession = (User) session.getAttribute("user");

            String userID = request.getParameter("userID").trim();

            if (userSession.getUserID().equals(userID) || userSession.getRoleID().equals("A")) {
                String userName = request.getParameter("userName").trim();
                String password = request.getParameter("password").trim();
                String passwordConfirm = request.getParameter("passwordConfirm").trim();
                String email = request.getParameter("email").trim();
                String phone = request.getParameter("phone").trim();
                String roleID = request.getParameter("roleID");
                String image = request.getParameter("imageOld");
                String passwordAccount = request.getParameter("passwordAccount").trim();

                ErrorUser errorUser = new ErrorUser();

                if (roleID == null) {
                    roleID = userSession.getRoleID();
                }

                boolean check = true;
                if (userName.length() == 0) {
                    errorUser.setUserName("User name kh??ng ???????c r???ng");
                    check = false;
                }
                if (userName.length() > 40) {
                    errorUser.setUserName("User name kh??ng ???????c l???n h??n 40 k?? t???");
                    check = false;
                }
                if (!password.equals(passwordConfirm)) {
                    errorUser.setPasswordConfirm("Password kh??ng kh???p nhau");
                    check = false;
                }
                if (password.length() != 0) {
                    if (password.length() < 8) {
                        errorUser.setPassword("Password ph???i t??? 8 k?? t??? tr??? ??i");
                        check = false;
                    }
                    if (password.length() > 40) {
                        errorUser.setPassword("Password ph???i b?? h??n 40 k?? t???");
                        check = false;
                    }
                }
                if (isValidEmail(email) == false) {
                    errorUser.setEmail("Email kh??ng ????ng ?????nh d???ng");
                    check = false;
                }
                if (email.length() > 40) {
                    errorUser.setEmail("Email kh??ng l???n h??n 40 k?? t???");
                    check = false;
                }
                if (isValidPhone(phone) == false) {
                    errorUser.setPhone("Phone ph???i l?? s???");
                    check = false;
                }
                if (phone.length() < 10 || phone.length() > 12) {
                    errorUser.setPhone("Phone ph???i t??? 10 ?????n 12 s???");
                    check = false;
                }
                if (!toHexString(getSHA(passwordAccount)).equals(userSession.getPassword())) {
                    errorUser.setPasswordAccount("Password kh??ng ????ng");
                    check = false;
                }

                if (check == false) {
                    User user = new User(userID, userName, email, phone, image, password, roleID);

                    request.setAttribute("errorUser", errorUser);
                    request.setAttribute("user", user);

                    url = EDIT;
                } else {
                    Part filePart = request.getPart("photo");
                    if (getFileName(filePart).equals("")) {
                        image = request.getParameter("imageOld");
                    } else {
                        image = uploadFile(request);
                    }

                    User user;
                    if (password.length() == 0) {
                        User userOld = userDao.getUserByID(userID);
                        password = userOld.getPassword();
                        user = new User(userID, userName, email, phone, image, password, roleID);
                    } else {
                        user = new User(userID, userName, email, phone, image, toHexString(getSHA(password)), roleID);
                    }
                    userDao.updateUser(user);

                    if (userSession.getRoleID().equals("A")) {
                        url = ADMIN_CONTROLLER + "?messUpdate=UpdateSuccessful";
                    } else {
                        url = SUB_CONTROLLER + "?messUpdate=UpdateSuccessful";
                    }
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

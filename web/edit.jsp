<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Edit</title>

        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />

        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link href="css/styles.css" rel="stylesheet" />
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <style>
            .card{
                margin-bottom: 600px;
            }
            table { 
                border-collapse: separate;
                border-spacing: 18px;
            }
        </style>

        <script type="text/javascript">
            function displayImg() {
                var image = document.getElementById('image');
                image.src = document.getElementById('photo').value;
            }
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#image')
                                .attr('src', e.target.result)

                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
    </head>
    <body>
        <c:if test="${(sessionScope.user.userID eq param.userID) or (sessionScope.user.roleID eq 'A')}">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container">
                    <a class="navbar-brand" href="${sessionScope.user.roleName}Controller">User Management</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="${sessionScope.user.roleName}Controller">
                                    Home
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <c:if test="${sessionScope.user.roleID eq 'A'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="insert.jsp">
                                        Insert
                                        <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="AdminController?action=listPromotion">
                                        List
                                        <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="AdminController?action=history">
                                        History
                                        <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            </c:if>
                            <li class="nav-item active">
                                <a class="nav-link" href="AdminController?action=Edit&userID=${sessionScope.user.userID}">
                                    Edit
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="LogoutController">Logout</a>
                            </li>

                            <li class="nav-item">
                                <i class="fa fa-user" style="font-size:40px;color:white"></i>
                            </li>
                            <li class="nav-item">
                                <p class="nav-link">${sessionScope.user.userName} (${sessionScope.user.roleName})<p>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="container">
                <div class="topnav"></div>
                <div class="row">
                    <script src="https://use.fontawesome.com/c560c025cf.js"></script>
                    <div class="container">
                        <div class="card shopping-cart">
                            <div class="card-header bg-dark text-light">
                                Update Sub
                                <div class="clearfix"></div>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-2 text-center">
                                        <img class="img-responsive" id="image" src="image/${requestScope.user.image}" alt="prewiew" width="180" height="160">
                                    </div>
                                    <form action="UpdateController" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="userID" value="${requestScope.user.userID}">
                                        <input type="hidden" name="imageOld" value="${requestScope.user.image}">
                                        <div class="col-16 text-sm-center col-sm-16 text-md-left col-md-16">
                                            <table style="width:100%">
                                                <tr>
                                                    <td><font color="Blue">Name: </font></td>
                                                    <td><input type="text" name="userName" value="${requestScope.user.userName}" size="40" required></td>
                                                    <td><i><font color="red">${requestScope.errorUser.userName}</font></i></td>
                                                </tr>
                                                <tr>
                                                    <td><font color="Blue">Password: </font></td>
                                                    <td><input type="password" name="password" value="${param.password}" size="40" placeholder="empty = old password"></td>
                                                    <td><i><font color="red">${requestScope.errorUser.password}</font></i></td>
                                                </tr>
                                                <tr>
                                                    <td><font color="Blue">Password confirm: </font></td>
                                                    <td><input type="password" name="passwordConfirm" value="${param.passwordConfirm}" size="40" placeholder="empty = old password"></td>
                                                    <td><i><font color="red">${requestScope.errorUser.passwordConfirm}</font></i></td>
                                                </tr>
                                                <tr>
                                                    <td><font color="Blue">Email: </font></td>
                                                    <td><input type="text" name="email" value="${requestScope.user.email}" size="40" required></td>
                                                    <td><i><font color="red">${requestScope.errorUser.email}</font></i></td>
                                                </tr>
                                                <tr>
                                                    <td><font color="Blue">Phone: </font></td>
                                                    <td><input type="text" name="phone" value="${requestScope.user.phone}" size="40" required></td>
                                                    <td><i><font color="red">${requestScope.errorUser.phone}</font></i></td>
                                                </tr>
                                                <c:if test="${requestScope.user.userID ne sessionScope.user.userID}">
                                                    <tr>
                                                        <td><font color="Blue">Role: </font></td>
                                                        <td><input type="radio" name="roleID" value="S" checked> Sub <input type="radio" name="roleID" value="A"> Admin</td>
                                                    </tr>
                                                </c:if>
                                                <tr>
                                                    <td><font color="Blue">Image: </font></td>
                                                    <td><input type="file" name="photo"onchange="readURL(this);" accept="image/gif, image/jpeg, image/png" /></td>
                                                </tr>
                                                <tr>
                                                        <c:if test="${sessionScope.user.roleName eq 'Admin'}">
                                                            <td><font color="red">Password admin: </font></td>
                                                        </c:if>
                                                        <c:if test="${sessionScope.user.roleName eq 'Sub'}">
                                                            <td><font color="red">Your password: </font></td>
                                                        </c:if>
                                                    <td><input type="password" name="passwordAccount" value="${param.passwordAccount}" size="40" placeholder="Must not be empty!" required></td>
                                                    <td><i><font color="red">${requestScope.errorUser.passwordAccount}</font></i></td>
                                                </tr>
                                            </table>
                                            <input class="btnAdd" type="submit" value="Update">
                                        </div>
                                        <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                                            <div class="col-16 col-sm-16 col-md-10 text-right"></div>
                                        </div>
                                    </form> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="py-5 bg-dark">
                <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Vinh SE140313</p></div>
            </footer>
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="js/scripts.js"></script>
        </c:if>
        <c:if test="${(sessionScope.user.userID ne param.userID) and (sessionScope.user.roleID ne 'A')}">
            <c:redirect url = "welcome.jsp"/>
        </c:if>
    </body>
</html>
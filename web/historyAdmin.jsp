<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>History</title>

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
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.user.roleID eq 'A'}">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container">
                    <a class="navbar-brand" href="AdminController">User Management</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="AdminController">
                                    Home
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
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
                                <a class="nav-link active" href="AdminController?action=history">
                                    History
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
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
                                History
                                <div class="clearfix"></div>
                            </div>
                            <div class="card-body">
                                <c:if test="${empty requestScope.listHistory}">
                                    <i><h3><font color="red">Không có history</font></h3></i>
                                </c:if>
                                <c:forEach items="${requestScope.listHistory}" var="history">
                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-2 text-center">
                                            <img class="img-responsive" src="image/${history.image}" alt="prewiew" width="120" height="105">
                                        </div>
                                        <div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                                            <h5><font color="Blue">Name: </font>${history.userName}</h5>
                                            <h5><font color="Blue">ID: </font>${history.userID}</h5>
                                            <h5><font color="Blue">Status: </font>
                                                <c:if test="${history.status eq 'Added'}">
                                                    <font color="Green">${history.status}</font>
                                                </c:if>
                                                <c:if test="${history.status eq 'Updated'}">
                                                    <font color="Brown">${history.status}</font>
                                                </c:if>
                                                <c:if test="${history.status eq 'Deleted'}">
                                                    <font color="Red">${history.status}</font>
                                                </c:if>
                                            </h5>
                                            <h5><font color="Blue">Rank: </font>${history.rank}</h5>
                                            <h5><font color="Blue">Date: </font>${history.date}</h5>
                                        </div>
                                    </div>
                                    <hr>
                                </c:forEach>
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
        <c:if test="${sessionScope.user.roleID ne 'A'}">
            <c:redirect url = "welcome.jsp"/>
        </c:if>
    </body>
</html>
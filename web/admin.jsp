<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Admin</title>

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
                            <li class="nav-item active">
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
                                <a class="nav-link" href="AdminController?action=history">
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
                <div class="topnav">
                    <button class="btnAll" onclick="location.href = 'AdminController'">All</button>
                    <button class="btnAdmin" onclick="location.href = 'AdminController?action=Admin'">Admin</button>
                    <button class="btnSub" onclick="location.href = 'AdminController?action=Sub'">Sub</button>
                    <div class="search-container">
                        <form action="AdminController" method="get">
                            <input name="action" value="Search" type="hidden">
                            <input type="text" name="searchName" value="${param.searchName}" placeholder="Search name.." >
                            <button type="submit"><i class="fa fa-search"></i></button>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <script src="https://use.fontawesome.com/c560c025cf.js"></script>
                    <div class="container">
                        <div class="card shopping-cart">
                            <div class="card-header bg-dark text-light">
                                <c:if test="${empty param.action}">All</c:if>
                                ${param.action}
                                <div class="clearfix"></div>
                            </div>
                            <div class="card-body">
                                <c:if test="${empty requestScope.listUser}">
                                    <i><h3><font color="red">Không có user tương ứng</font></h3></i>
                                </c:if>
                                <c:forEach items="${requestScope.listUser}" var="user">
                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-2 text-center">
                                            <img class="img-responsive" src="image/${user.image}" alt="prewiew" width="120" height="105">
                                        </div>
                                        <div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                                            <h4 class="product-name"><strong>${user.userName}</strong></h4>
                                            <font color="Blue">ID: </font>${user.userID}
                                            <font color="Blue">Email: </font>${user.email}
                                            <font color="Blue">Phone: </font>${user.phone}
                                            <br>
                                            <font color="Blue">Status promotion: </font>${user.statusPromotion}
                                            <font color="Blue">Rank: </font>${user.rank}
                                            <font color="Blue">Date create: </font>${user.dateCreate}
                                            <br>
                                            <font color="Blue">Role: </font>${user.roleName}
                                            <font color="Blue">Status: </font>${user.status}
                                        </div>
                                        <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                                            <div class="col-20 col-sm-20 col-md-20 text-right">
                                                <c:if test="${user.roleID eq 'S'}">
                                                    <button class="btnEdit" onclick="location.href = 'AdminController?action=Edit&userID=${user.userID}'">Edit</button>
                                                    <c:if test="${user.status eq 'Active'}">
                                                        <button class="btnDelete" onclick="location.href = 'AdminController?action=delete&userID=${user.userID}'">Delete</button>
                                                    </c:if>
                                                    <c:if test="${user.status eq 'Inactive'}">
                                                        <button class="btnAccept" onclick="location.href = 'AdminController?action=active&userID=${user.userID}'">Active</button>
                                                    </c:if>
                                                    <c:if test="${user.statusPromotion eq 'False'}">
                                                        <button class="btnAdd" onclick="location.href = 'AdminController?action=addPromotion&userID=${user.userID}'">Add Promotion</button>
                                                    </c:if>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <hr>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>               
            <c:if test="${not empty param.messAdd}">
                <script> swal("Add promotion successful!", "", "success");</script>
            </c:if>
            <c:if test="${not empty param.messDelete}">
                <script> swal("Delete successful!", "", "success");</script>
            </c:if>
            <c:if test="${not empty param.messActive}">
                <script> swal("Active successful!", "", "success");</script>
            </c:if>
            <c:if test="${not empty param.messInsert}">
                <script> swal("Insert successful!", "", "success");</script>
            </c:if>
            <c:if test="${not empty param.messUpdate}">
                <script> swal("Update successful!", "", "success");</script>
            </c:if>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Sub</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="405687524425-qvsif465v942gb7elgr1643nqtteqdk2.apps.googleusercontent.com">

        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/shop-homepage.css" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src='https://www.google.com/recaptcha/api.js?hl=vi'></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

        <style>
            .container row{
                margin-top: 120px;
                max-width: 600px;
                height: 330px;
                border: 1px solid #9C9C9C;
                background-color: #EAEAEA;
            }
            footer {
                position:absolute;
                bottom:0;
                width:100%;
                height:60px;   
                background:#6cf;
            }

            .glyphicon {  margin-bottom: 10px;margin-right: 10px;}
            small {
                display: block;
                line-height: 1.428571429;
                color: #999;
            }
        </style>
    </script>
</head>
<body>
    <c:if test="${sessionScope.user.roleID eq 'S'}">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <h1 class="navbar-brand">User Management</h1>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="well well-sm">
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <img src="image/${requestScope.user.image}" alt="" class="img-rounded img-responsive" />
                            </div>
                            <div class="col-sm-6 col-md-8">
                                <h4>${requestScope.user.userName}</h4>

                                <p>
                                    <i class="glyphicon glyphicon-user"></i><font color="Blue">Role: </font>${sessionScope.user.roleName}
                                    <br>
                                    <i class="glyphicon glyphicon-envelope"></i><font color="Blue">Email: </font>${requestScope.user.email}
                                    <br>
                                    <i class="glyphicon glyphicon-earphone"></i><font color="Blue">Phone: </font>${requestScope.user.phone}
                                    <br>
                                    <i class="glyphicon glyphicon-gift"></i><font color="Blue">Status Promotion: </font>${requestScope.user.statusPromotion}
                                    <br>
                                    <i class="glyphicon glyphicon-calendar"></i><font color="Blue">Promotion creation date: </font>${requestScope.user.dateCreate}
                                    <br>
                                    <i class="glyphicon glyphicon-star"></i><font color="Blue">Rank: </font>${requestScope.user.rank}</p>
                                <!-- Split button -->
                                <div class="btn-group">
                                    <button type="button" onclick="location.href = 'SubController?action=Edit&userID=${user.userID}'" class="btn btn-primary">Edit </button>
                                </div>
                                <div class="btn-group">
                                    <button type="button" onclick="location.href = 'SubController?action=history&userID=${requestScope.user.userID}'" class="btn btn-success">History </button>
                                </div>
                                <div class="btn-group">
                                    <button type="button" onclick="location.href = 'LogoutController'" class="btn btn-danger">Logout </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <c:if test="${requestScope.user.statusPromotion eq 'True'}">
                        <div class="col-sm-12 col-md-12">
                            <div class="alert alert-success">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                <span class="glyphicon glyphicon-ok"></span> <strong>Bạn hiện đang có khuyến mãi</strong>
                                <hr class="message-inner-separator">
                                <p>Rank khuyến mãi hiện tại của bạn là ${requestScope.user.rank}</p>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <c:if test="${not empty param.messUpdate}">
            <script> swal("Update successful!", "", "success");</script>
        </c:if>
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; Vinh SE140313</p>
            </div>
        </footer>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    </c:if>
    <c:if test="${sessionScope.user.roleID ne 'S'}">
        <c:redirect url = "welcome.jsp"/>
    </c:if>
</body>
</html>
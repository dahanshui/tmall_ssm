<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<div class="navitagorDiv">
    <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
        <img style="margin-left:10px;margin-right:0px" class="pull-left" src="img/site/tmallbuy.png" height="45px">
        <a class="navbar-brand" href="#nowhere">天猫后台</a>

        <a class="navbar-brand" href="admin_category_list">分类管理</a>
        <a class="navbar-brand" href="admin_user_list">用户管理</a>
        <a class="navbar-brand" href="admin_administrator_list">管理员管理</a>
        <a class="navbar-brand" href="admin_order_list">订单管理</a>
        <span class="pull-right" style="margin-right: 20px">
		<a class="navbar-brand" href="#nowhere">
            <c:if test="${ !empty Administrator}">
                <span class="	glyphicon glyphicon-user"></span>${Administrator.aname}
            </c:if>
             <c:if test="${empty Administrator}">
                 <a class="navbar-brand" href="loginAdminPage">
                     <span class="	glyphicon glyphicon-user"></span>未登录</a>
             </c:if>
                </a>
		<a class="navbar-brand" href="admin_user_drop"><span class="glyphicon glyphicon-log-out">退出</span></a>
        </span>
    </nav>
    <div style="clear: both"></div>
</div>
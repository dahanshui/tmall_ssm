<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<html>
<head>
    <title>管理员登录</title>
    <script>
        $(function () {
            $("#loginAdminForm").submit(function () {
                if (!checkEmpty("aname", "账号")) {
                    return false;
                }
                if (!checkEmpty("apassword", "密码")) {
                    return false;
                }
                return true;
            });
            <c:if test="${ !empty msg}">
            $("div.loginAdminErrorMessage").show();
            $("div.loginAdminErrorMessage").html("${msg}");
            </c:if>
            $("td.loginAdminPageTD input").keyup(function () {
                $("div.loginAdminErrorMessage").hide();
            });
        });
    </script>
</head>
<body>
<div class="loginAdminPageDiv">
    <form action="doAdminLogin" method="post" id="loginAdminForm">
        <div class="loginAdminPageInfoDiv">
            <div class="panel panel-info">
                <div class="loginAdminErrorDiv panel-heading">
                    <div class="panel-heading loginAdminMessageHeader">管理员登录</div>
                    <div class="loginAdminErrorMessage"></div>
                </div>
                <div class="panel-body">
                    <table class="loginAdminPageTable">
                        <tr>
                            <td width="150px" class="loginAdminPageTD"><span class="glyphicon glyphicon-user"></span>账号:
                            </td>
                            <td width="280px" class="loginAdminPageTD"><input type="text" name="aname"
                                                                              class="form-control" id="aname"></td>
                        </tr>
                        <tr>
                            <td class="loginAdminPageTD"><span class="glyphicon glyphicon-lock"></span>密码:</td>
                            <td class="loginAdminPageTD"><input type="password" name="apassword" class="form-control"
                                                                id="apassword"></td>
                        </tr>
                        <tr>
                            <td colspan="2" class="loginAdminPageTD" width="100%">
                                <button class="btn btn-success btn-block" type="submit" style="font-size: 16px">登录
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="loginAdminPageTD" width="100%" style="padding: 10px">
                                <a href="goAdminRegister"><span class="glyphicon glyphicon-log-out"
                                                                style="font-size: 14px;">点击注册</span></a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>

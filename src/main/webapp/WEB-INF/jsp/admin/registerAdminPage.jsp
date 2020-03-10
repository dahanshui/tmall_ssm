<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<html>
<head>
    <title>管理员注册</title>
    <script>
        $(function () {
            $("#registerAdminForm").submit(function () {
                if (!checkEmpty("aname", "账户名")) {
                    return false;
                }
                if (!checkEmpty("apassword", "密码")) {
                    return false;
                }
                if (!checkEmpty("code", "验证码")) {
                    return false;
                }
                return true;
            });
            <c:if test="${ !empty msg}">
            $("div.registerAdminErrorMessage").show();
            $("div.registerAdminErrorMessage").html("${msg}");
            </c:if>
            $("td.registerAdminPageTD input").keyup(function () {
                $("div.registerAdminErrorMessage").hide();
            });
        });
    </script>
</head>
<body>
<div class="registerAdminPageDiv">
    <form action="doAdminRegister" method="post" id="registerAdminForm">
        <div class="panel panel-info">
            <div class="registerAdminErrorDiv panel-heading">
                <div class="panel-heading registerAdminMessageHeader">管理员注册</div>
                <div class="registerAdminErrorMessage"></div>
            </div>
            <div class="panel-body">
                <table class="registerAdminPageTable">
                    <tr>
                        <td width="150px" class="registerAdminPageTD"><span class="glyphicon glyphicon-user"></span>设置账户名:
                        </td>
                        <td width="280px" class="registerAdminPageTD"><input type="text" name="aname"
                                                                             class="form-control" id="aname"></td>
                    </tr>
                    <tr>
                        <td class="registerAdminPageTD"><span class="glyphicon glyphicon-lock"></span>设置密码:</td>
                        <td class="registerAdminPageTD"><input type="password" name="apassword" class="form-control"
                                                               id="apassword"></td>
                    </tr>
                    <tr>
                        <td class="registerAdminPageTD"><span class="glyphicon glyphicon-certificate"></span>注册验证码:</td>
                        <td class="registerAdminPageTD"><input type="text" name="code" class="form-control" id="code">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="registerAdminPageTD" width="100%">
                            <button class="btn btn-success btn-block" type="submit" style="font-size: 16px">提交</button>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
</div>
</body>
</html>

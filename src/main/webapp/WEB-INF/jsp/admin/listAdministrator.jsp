<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<html>
<head>
    <title>管理员管理</title>
</head>
<body>
<div class="workingArea">
    <h1 class="label label-info">管理员管理</h1>

    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>管理员名称</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ats}" var="at">
                <tr>
                    <td>${at.id}</td>
                    <td>${at.aname}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>


</div>

<%@include file="../include/admin/adminFooter.jsp" %>
</body>
</html>

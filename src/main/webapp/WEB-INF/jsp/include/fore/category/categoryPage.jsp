<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>模仿天猫官网-${c.name}</title>
<div>
    <div>
        <img src="img/category/${c.id}.jpg">
        <%@include file="sortBar.jsp" %>
        <%@include file="productsByCategory.jsp" %>
        <div style="text-align: center">
            <%@include file="../../admin/adminPage.jsp" %>
        </div>
    </div>
</div>


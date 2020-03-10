<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    $(function () {
        $("ul.pagination li.disabled a").click(function () {
            return false;
        });
    });

</script>


<nav>
    <ul class="pagination">
        <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
            <a href="?start=0${page.param}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
            <a href="?start=${page.start-page.count}${page.param}" aria-label="Previous">
                <span aria-hidden="true">&lsaquo;</span>
            </a>
        </li>

        <c:choose>
            <c:when test="${page.totalPage < 5}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${page.totalPage}"/>
            </c:when>
            <c:otherwise>
                <c:set var="begin" value="${page.currentPage-2}"/>
                <c:set var="end" value="${page.currentPage+2}"/>
                <c:if test="${begin<=0}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:if>
                <c:if test="${end>page.totalPage}">
                    <c:set var="begin" value="${page.totalPage-4}"/>
                    <c:set var="end" value="${page.totalPage}"/>
                </c:if>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
            <c:if test="${status.count>=begin and status.count<=end}">
                <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
                    <a href="?start=${status.index*page.count}${page.param}"
                       <c:if test="${status.index*page.count==page.start}">class="current"</c:if>
                    >${status.count}</a>
                </li>
            </c:if>
        </c:forEach>

        <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
            <a href="?start=${page.start+page.count}${page.param}" aria-label="Next">
                <span aria-hidden="true">&rsaquo;</span>
            </a>
        </li>
        <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
            <a href="?start=${page.last}${page.param}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>

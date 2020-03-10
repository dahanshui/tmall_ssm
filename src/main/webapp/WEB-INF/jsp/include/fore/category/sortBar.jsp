<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("input.sortBarPrice").keyup(function () {
            var num = $(this).val();
            if (0 == num.length) {
                $("div.productUnit").show();
                return;
            }
            num = parseInt(num);
            if (isNaN(num)) {
                num = 0;
            }
            if (num <= 0) {
                num = 0;
            }
            $(this).val(num);
            var start = $("input.beginPrice").val();
            var end = $("input.endPrice").val();
            if (!isNaN(start) && !isNaN(end)) {
                $("div.productUnit").hide();
                $("div.productUnit").each(function () {
                    var price = $(this).attr("price");
                    price = new Number(price);
                    if (price >= start && price <= end) {
                        $(this).show();
                    }
                });
            }
        });
    });
</script>
<div class="categorySortBar">
    <table class="categorySortBarTable categorySortTable">
        <tr>
            <td<c:if test="${'all'==param.sort || empty param.sort}">class
            ="grayColumn"</c:if>>
            <a href="?cid=${c.id}&sort=all">综合<span class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td<c:if test="${'review'==param.sort}">class
            ="grayColumn"</c:if>>
            <a href="?cid=${c.id}&sort=review">人气<span class="glyphicon glyphicon-arrow-down"></span></a> </td>
            <td<c:if test="${'date'==param.sort}">class
            ="grayColumn"</c:if>>
            <a href="?cid=${c.id}&sort=date">新品<span class="glyphicon glyphicon-arrow-down"></span></a> </td>
            <td<c:if test="${'saleCount'==param.sort}">class
            ="grayColumn"</c:if>>
            <a href="?cid=${c.id}&sort=saleCount">销量<span class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td<c:if test="${'price'==param.sort}">class
            ="grayColumn"</c:if>>
            <a href="?cid=${c.id}&sort=price">价格<span class="glyphicon glyphicon-resize-vertical"></span></a> </td>
            <td<c:if test="${'image'==param.sort}">class
            ="grayColumn"</c:if>>
            <a href="?cid=${c.id}&sort=image">图片<span class="glyphicon glyphicon-resize-vertical"></span></a> </td>
        </tr>
    </table>
    <table class="categorySortBarTable">
        <tr>
            <td><input type="text" placeholder="请输入" class="sortBarPrice beginPrice"></td>
            <td class="grayColumn priceMiddleColumn">-</td>
            <td><input type="text" placeholder="请输入" class="sortBarPrice endPrice"></td>
        </tr>
    </table>
</div>

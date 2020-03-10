<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="categoryProducts">
    <c:forEach items="${c.products}" var="p" varStatus="st">
        <div class="productUnit" price="${p.promotePrice}">
            <div class="productUnitFrame">
                <a href="foreproduct?pid=${p.id}">
                    <img class="productImage" src="img/productSingle_middle/${p.firstProductImage.id}.jpg">
                </a>
                <span class="productPrice"><fmt:formatNumber type="number" value="${p.promotePrice}"
                                                             minFractionDigits="2"/> </span>
                <a href="foreproduct?pid=${p.id}" class="productPrice">${fn:substring(p.name, 0, 50)}</a>
                <a href="foreproduct?pid=${p.id}" class="tmallLink">天猫专卖</a>
                <div class="show1 productInfo">
                    <span class="monthDeal ">月成交<span class="productDealNumber">${p.saleCount}笔</span></span>
                    <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                    <span class="wangwang">
                        <a href="#nowhere" class="wangwanglink"><img src="img/site/wangwang.png"></a>
                    </span>
                </div>
            </div>
        </div>
    </c:forEach>
    <div style="clear: both"></div>
</div>

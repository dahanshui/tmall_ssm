<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="confirmPayPageDiv">
    <div class="confirmPayImageDiv">
        <img src="img/site/comformPayFlow.png">
        <div class="confirmPayTime1"><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
        <div class="confirmPayTime2"><fmt:formatDate value="${o.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
        <div class="confirmPayTime3"><fmt:formatDate value="${o.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
    </div>
    <div class="confirmPayOrderInfoDiv">
        <div class="confirmPayOrderInfoText">我已收到货，同意支付宝付款</div>
    </div>
    <div class="confirmPayOrderItemDiv">
        <div class="confirmPayOrderItemText">订单信息</div>
        <table class="confirmPayOrderItemTable">
            <thead>
            <th colspan="2">宝贝</th>
            <th>单价</th>
            <th>数量</th>
            <th>商品总价</th>
            <th>运费</th>
            </thead>
            <tbody>
            <c:forEach items="${o.orderItems}" var="oi">
                <tr>
                    <td><img src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg"></td>
                    <td class="confirmPayOrderItemProductLink">
                        <a href="foreproduct?pid=${oi.product.id}">${oi.product.name}</a>
                    </td>
                    <td>
                        ￥<fmt:formatNumber type="number" value="${oi.product.promotePrice}" minFractionDigits="2"/>
                    </td>
                    <td>${oi.number}</td>
                    <td>
                        <span class="conformPayProductPrice">
                                ￥<fmt:formatNumber type="number" value="${oi.product.promotePrice*oi.number}"
                                                   minFractionDigits="2"/>
                        </span>
                    </td>
                    <td width="10%"><span>快递:0.00</span></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="confirmPayOrderItemText pull-right">
            实付款：<span class="confirmPayOrderItemSumPrice">￥<fmt:formatNumber type="number" value="${o.total}"
                                                                             minFractionDigits="2"/> </span>
        </div>
    </div>
    <div class="confirmPayOrderDetailDiv">
        <table class="confirmPayOrderDetailTable">
            <tr>
                <td>订单编号：</td>
                <td>${o.orderCode}</td>
            </tr>
            <tr>
                <td>卖家昵称：</td>
                <td>天猫商铺 <span class="confirmPayOrderDetailWangWangGif"></span></td>
            </tr>
            <tr>
                <td>收货信息：</td>
                <td>${o.address} ${o.receiver} ${o.mobile} ${o.userMessage}</td>
            </tr>
            <tr>
                <td>成交时间：</td>
                <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </table>
    </div>
    <div class="confirmPayButtonDiv">
        <div class="confirmPayWarning">请收到货后再确认收货，负责造成得损失将由您自行承担！</div>
        <a href="foreorderConfirmed?oid=${o.id}">
            <button class="btn btn-warning confirmPayButton">确认支付</button>
        </a>
    </div>
</div>

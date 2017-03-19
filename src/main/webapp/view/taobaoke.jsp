<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/css/themes/default/jquery.mobile-1.4.5.min.css">
    <script src="../static/js/jquery.js"></script>
    <script src="../static/js/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>
    <div role="main" class="ui-content jqm-content">

        <h1>淘宝客生成平台</h1>

        <c:forEach items="${list}" var="taobaoke" >
        <ul data-role="listview">
            <li><img src="${taobaoke.productPic}" width="400px" height="300px"></li>
            <li>${taobaoke.productName}</li>
            <li>原价${taobaoke.origin}【券后${taobaoke.discount}元】包邮</li>
            <li>【领券下单地址】<a href="${taobaoke.couponShortUrl}" target="_blank">${taobaoke.couponShortUrl}</a></li>
            <li>复制这条信息，打开[手机淘宝]，即可领券购买!${taobaoke.couponTaokouling}</li>
        </ul>
        </c:forEach>

    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/css/themes/default/jquery.mobile-1.4.5.min.css">
    <script src="../static/js/jquery.js"></script>
                    <script src="../static/js/jquery.mobile-1.4.5.min.js"></script>
    <script   language="javascript" type="text/javascript">
        $(document).ready(function () {
            $('#button1').click(function () {
                var userStr = '';
                var productStr = '';
                $('#userGroup :checkbox:checked').each(function () {
                    userStr += $(this).attr("id") + ",";
                });
                $('#productGroup1 :checkbox:checked').each(function () {
                    productStr += $(this).attr("id") + ",";
                });

                $.ajax({
                    type: "POST",
                    url: "submit3hour",
                    dataType: "json",
                    scriptCharset: 'utf-8',
                    data: { "userStr1": userStr, "product1Box": productStr},
                    success: function (data) {
                        $('#positionWindow p').html(data.data);
                        $('#showLog').click();
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
                    }
                });
//                $('#userStr1').val(userStr);
//                $("#form1").submit();
            });

            $('#button2').click(function () {
                var userStr = '';
                var productStr = '';
                $('#userGroup :checkbox:checked').each(function () {
                    userStr += $(this).attr("id") + ",";
                });
                $('#productGroup2 :checkbox:checked').each(function () {
                    productStr += $(this).attr("id") + ",";
                });

                $.ajax({
                    type: "POST",
                    url: "submitWednesDay",
                    dataType: "json",
                    scriptCharset: 'utf-8',
                    data: { "userStr2": userStr, "product2Box": productStr},
                    success: function (data) {
                        $('#positionWindow p').html(data.data);
                        $('#showLog').click();
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
                    }
                });
//                $('#userStr2').val(userStr);
//                $("#form2").submit();
            });
            $('#buttonStop').click(function () {
                $.get("stop");
            });

            // 指定websocket路径
//            var websocket = new WebSocket('ws://42.62.119.14:8080/log/socket');
//            websocket.onmessage = function(event) {
//                // 接收服务端的实时日志并添加到HTML页面中
//                $("#log-container div").append(event.data);
//                // 滚动条滚动到最低部
//                $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
//            };
        });
    </script>
</head>
<body>
<div data-role="page" class="jqm-demos" data-quicklinks="true">

    <div role="main" class="ui-content jqm-content">

        <h1>活动平台</h1>
        <fieldset data-role="controlgroup" id="userGroup">
            <c:forEach items="${userList}" var="user" >
                <input type="checkbox" name="${user.userId}" id="${user.userId}"/>
                <label for="${user.userId}">${user.userName}</label>
            </c:forEach>
        </fieldset>

        <div data-demo-html="true">
            <div data-role="tabs" id="tabs">
                <div data-role="navbar">
                    <ul>
                        <li><a href="#one" data-ajax="false">下午三点</a></li>
                        <li><a href="#two" data-ajax="false">周三五折</a></li>
                        <li><a href="#three" data-ajax="false">周三</a></li>
                    </ul>
                </div>
                <div id="one">
                    <form id="form1" action="submit3hour" method="post">
                        <input id="userStr1" name="userStr1" type="hidden">
                        <fieldset data-role="controlgroup" id="productGroup1" name="productGroup1" data-iconpos="right">
                            <c:forEach items="${product1List}" var="product1">
                                <input type="checkbox" id="${product1.productNo}" name="product1Box" value="${product1.productNo}"/>
                                <label for="${product1.productNo}">${product1.productName}</label>
                            </c:forEach>
                        </fieldset>
                        <button id="button1" class="ui-btn ui-shadow" type="button">提交</button>
                    </form>
                </div>
                <div id="two">
                    <form id="form2" action="submitWednesDay" method="post">
                        <input id="userStr2" name="userStr2" type="hidden">
                        <fieldset data-role="controlgroup" id="productGroup2" name="productGroup2" data-iconpos="right">
                            <c:forEach items="${product2List}" var="product2">
                                <input type="checkbox" id="${product2.productNo}" name="product2Box" value="${product2.productNo}"/>
                                <label for="${product2.productNo}">${product2.productName}</label>
                            </c:forEach>
                        </fieldset>
                        <button id="button2" class="ui-btn ui-shadow" type="button">提交</button>
                    </form>
                </div>
                <div id="three">

                </div>
            </div>
            <button id="buttonStop" class="ui-btn ui-shadow" type="button">停止所有</button>
        </div>
        </br>
        <a id="showLog" href="#positionWindow" class="ui-btn ui-shadow" data-rel="popup" data-position-to="window">日志显示</a>
        <div data-role="popup" id="positionWindow" class="ui-content" data-theme="a">
            <p>no log</p>
        </div>
    </div>
</body>
</html>

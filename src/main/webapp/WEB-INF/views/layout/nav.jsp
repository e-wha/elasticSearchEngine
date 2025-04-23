<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/nav.css' />">
</head>
<body>
<nav class="main-nav">
    <ul class="lnb_nav">
        <!-- 여행지 -->
        <li class="lnb_nav_item n1">
            <a href="#" class="lnb_nav_link" id="lnb_nav_link1"><span>여행지</span></a>
            <ul class="depth2">
                <li><a href="main.do"><span>관광지</span></a></li>
                <li><a href="festivalList.do"><span>축제행사</span></a></li>
            </ul>
        </li>

        <!-- 지역별여행 -->
        <li class="lnb_nav_item n2">
            <a href="#" class="lnb_nav_link" id="lnb_nav_link2"><span>지역별여행</span></a>
            <ul class="depth2">
                <li><a href="tourByArea.do?area=자갈치"><span>자갈치</span></a></li>
                <li><a href="tourByArea.do?area=영도"><span>영도</span></a></li>
                <li><a href="tourByArea.do?area=광안리"><span>광안리</span></a></li>
                <li><a href="tourByArea.do?area=해운대"><span>해운대</span></a></li>
                <li><a href="tourByArea.do?area=동부산"><span>동부산</span></a></li>
            </ul>
        </li>

        <!-- 여행준비 -->
        <li class="lnb_nav_item n3">
            <a href="#" class="lnb_nav_link" id="lnb_nav_link3"><span>여행준비</span></a>
            <ul class="depth2">
                <li><a href="myBusan.do"><span>나만의 부산여행</span></a></li>
                <li><a href="infoCenter.do"><span>관광안내소</span></a></li>
            </ul>
        </li>
    </ul>
</nav>
</body>
</html>
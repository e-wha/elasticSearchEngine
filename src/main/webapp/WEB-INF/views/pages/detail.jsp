<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관광지 상세</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/detail.css' />">
</head>
<body>
<jsp:include page="../layout/nav.jsp" />

<main class="tour-detail">
    <!-- 타이틀, 주소, 등록일, 수정일 -->
    <div class="tour-header">
        <h1>${tour.title}</h1>
        <p class="address"><b>주소</b> ${tour.addr}</p>
        <hr>
        <p class="dates">
            <span>최초 등록일: ${tour.created_time}</span>
            <span>최종 수정일: ${tour.modified_time}</span>
        </p>
        <a href="tourShare.do?id=${tour.contentId}" class="share-btn">
            <img src="<c:url value='<%--https://www.svgrepo.com/show/343228/paper-plane-alt.svg--%>' />" alt="공유 아이콘" class="share-icon" />
            공유하기
        </a>
    </div>

    <!-- #태그 -->
    <div class="tour-tags">
        <c:forEach var="tag" items="${tour.tags}">
            <span class="tag">#${tag}</span>
        </c:forEach>
    </div>

    <!-- 사진 -->
    <div class="tour-image">
        <img src="${tour.thumbnail}" alt="${tour.title}" />
    </div>

    <!-- 하이퍼링크로 변경된 서브타이틀 -->
    <div class="tour-nav-links">
        <ul>
            <li><a href="#introduction">소개</a></li>
            <li><a href="#info">이용정보</a></li>
            <li><a href="#nearby">주변관광지</a></li>
        </ul>
    </div>

    <!-- 소개 -->
    <section id="introduction" class="tour-section">
        <h2>소개</h2>
        <p>${tour.introduction}</p>
    </section>

    <!-- 이용정보 -->
    <section id="info" class="tour-section">
        <h2>이용정보</h2>
        <p>${tour.info}</p>
    </section>

    <!-- 주변관광지 -->
    <section id="nearby" class="tour-section">
        <h2>주변관광지</h2>
        <c:forEach var="nearbyTour" items="${tour.nearbyTours}">
            <p><a href="tourDetail.do?id=${nearbyTour.contentId}">${nearbyTour.title}</a></p>
        </c:forEach>
    </section>

</main>

<jsp:include page="../layout/footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>축제행사</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css' />">
</head>
<body>
<!-- nav 영역 -->
<jsp:include page="../layout/nav.jsp" />

<main>
    <c:set var="monthParam" value="${param.subtitles}" />

    <!-- 서브타이틀 영역 -->
    <div class="subtitles">
        <ul id="monthList">
            <!-- 1월부터 12월까지 버튼은 여기에 JavaScript로 추가됩니다. -->
            <li><a href="javascript:;" class="btn" onclick="toggleSubTitle('전체')">전체</a></li>

            <!-- 1월부터 12월까지 버튼을 반복문으로 생성 -->
            <c:forEach var="month" begin="1" end="12">
                <li><a href="javascript:;" class="btn" onclick="toggleSubTitle('${month}월')">${month}월</a></li>
            </c:forEach>
        </ul>
    </div>

    <!-- 필터 영역 -->
    <div class="filters">
        <form action="festival.do" method="get" class="filters-form" id="tagForm">
            <!-- 서브타이틀 값 hidden -->
            <input type="hidden" id="subtitles" name="month" value="${fn:join(paramValues.subtitles, ',')}" />

            <!-- 지역 선택 -->
            <select name="area">
                <option value="">부산 전체</option>
                <c:forEach var="area" items="${areaList}">
                    <option value="${area}" <c:if test="${param.area == area}">selected</c:if>>${area}</option>
                </c:forEach>
            </select>

            <!-- 검색어 입력 -->
            <input type="text" name="keyword" placeholder="검색어를 입력해주세요." value="${param.keyword}" />

            <!-- 검색 버튼 -->
            <button type="submit">
                <img src="<c:url value='https://cdn-icons-png.flaticon.com/128/4254/4254104.png' />" alt="돋보기 아이콘" />
            </button>
        </form>
    </div>

    <!-- 정렬/페이징 정보 -->
    <div class="list-info">
                <span class="page-info">
                    총 <span class="number">${totalList}</span>건 | 페이지 <span class="number">${currentPage}</span>/<span class="number">${totalPages}</span>
                </span>
        <div class="sort">
            <a href="festival.do?sort=date">등록일순</a>|<a href="festival.do?sort=title">제목순</a>
        </div>
    </div>

    <!-- 관광지 목록 -->
    <div class="grid">
        <c:forEach var="tour" items="${tourList}">
            <div class="tour-card">
                <a href="tourDetail.do?id=${tour.contentId}" class="tour-link">
                    <img src="${tour.thumbnail}" alt="${tour.title}" />
                    <h3>${tour.title}</h3>
                </a>
                <p>
                    <c:forEach var="tag" items="${tour.tags}">
                        <span>#${tag}</span>
                    </c:forEach>
                </p>
                <a href="tourShare.do?id=${tour.contentId}">
                    <p><img src="<c:url value='https://www.svgrepo.com/show/343228/paper-plane-alt.svg' />" alt="공유 아이콘" class="share-icon"/>공유하기</p>
                </a>
            </div>
        </c:forEach>
    </div>

    <!-- 페이지네이션 -->
    <div class="pagination">
        <!-- 이전 버튼 -->
        <c:url var="prevUrl" value="festival.do">
            <c:param name="page" value="${currentPage - 1}" />
            <c:param name="area" value="${param.area}" />
            <c:param name="category" value="${param.category}" />
            <c:param name="keyword" value="${param.keyword}" />
            <c:param name="tags" value="${fn:join(paramValues.tags, ',')}" />
            <c:param name="sort" value="${param.sort}" />
        </c:url>
        <a href="${hasPrev ? prevUrl : 'javascript:void(0);'}" class="${!hasPrev ? 'disabled' : ''}">&lt;</a>

        <!-- 페이지 번호 -->
        <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <c:url var="pageUrl" value="festival.do">
                <c:param name="page" value="${i}" />
                <c:param name="area" value="${param.area}" />
                <c:param name="category" value="${param.category}" />
                <c:param name="keyword" value="${param.keyword}" />
                <c:param name="tags" value="${fn:join(paramValues.tags, ',')}" />
                <c:param name="sort" value="${param.sort}" />
            </c:url>
            <a href="${pageUrl}" class="${i == currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>

        <!-- 다음 버튼 -->
        <c:url var="nextUrl" value="festival.do">
            <c:param name="page" value="${currentPage + 1}" />
            <c:param name="area" value="${param.area}" />
            <c:param name="category" value="${param.category}" />
            <c:param name="keyword" value="${param.keyword}" />
            <c:param name="tags" value="${fn:join(paramValues.tags, ',')}" />
            <c:param name="sort" value="${param.sort}" />
        </c:url>
        <a href="${hasNext ? nextUrl : 'javascript:void(0);'}" class="${!hasNext ? 'disabled' : ''}">&gt;</a>
    </div>
</main>

<!-- footer 영역 -->
<jsp:include page="../layout/footer.jsp" />

<!-- toggleTag.js 파일 불러오기 -->
<script src="<c:url value='../resources/js/festivalToggleTag.js' />"></script>

</body>
</html>
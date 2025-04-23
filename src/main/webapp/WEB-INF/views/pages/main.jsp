<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>부산 여행</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tags.css' />">
</head>
<body>
<!-- nav 영역 -->
<jsp:include page="../layout/nav.jsp" />

<main>
    <c:set var="tagParams" value="${paramValues.tags}" />

    <!-- 서브타이틀 영역 -->
    <div class="subtitles">
        <ul>
            <li class="item">
                <a href="javascript:;" class="btn" onclick="toggleSubTitle('전체')">전체</a>
            </li>
            <li class="item">
                <a href="javascript:;" class="btn" onclick="toggleSubTitle('문화')">문화</a>
            </li>
            <li class="item">
                <a href="javascript:;" class="btn" onclick="toggleSubTitle('자연')">자연</a>
            </li>
            <li>
                <a href="javascript:;" class="btn" onclick="toggleSubTitle('체험')">체험</a>
            </li>
        </ul>
    </div>

    <!-- 필터 영역 -->
    <div class="filters">
        <form action="main.do" method="get" class="filters-form" id="tagForm">
            <!-- 서브타이틀 값 hidden -->
            <input type="hidden" id="category" name="category" value="${fn:join(paramValues.category, ',')}" />

            <!-- 지역 선택 -->
            <select name="sigunguCode">
                <option value="">부산 전체</option>
                <c:forEach var="area" items="${areaList}">
                    <option value="${area}" <c:if test="${param.sigunguCode == area}">selected</c:if>>${area}</option>
                </c:forEach>
            </select>

            <!-- 카테고리 선택 -->
            <select name="category">
                <option value="">분류 전체</option>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category}" <c:if test="${param.category == category}">selected</c:if>>${category}</option>
                </c:forEach>
            </select>

            <!-- 검색어 입력 -->
            <input type="text" name="title" placeholder="검색어를 입력해주세요." value="${param.title}" />

            <!-- 검색 버튼 -->
            <button type="submit">
                <img src="<c:url value='https://cdn-icons-png.flaticon.com/128/4254/4254104.png' />" alt="돋보기 아이콘" />
            </button>

            <!-- 태그 값 hidden -->
            <input type="hidden" id="tags" name="tags" value="${fn:join(paramValues.tags, ',')}" />
        </form>
    </div>

    <!-- 정렬/페이징 정보 -->
    <div class="list-info">
                <span class="page-info">
                    총 <span class="number">${totalList}</span>건 | 페이지 <span class="number">${currentPage}</span>/<span class="number">${totalPages}</span>
                </span>
        <div class="sort">
            <a href="main.do?sort=0">등록일순</a>|<a href="main.do?sort=1">제목순</a>
        </div>
    </div>

    <!-- 태그 버튼 영역 -->
    <div class="tag-checkboxes">
        <a href="javascript:;" class="btn" onclick="toggleTag('SNS명소')">#SNS명소</a>
        <a href="javascript:;" class="btn" onclick="toggleTag('가족과함께')">#가족과함께</a>
        <a href="javascript:;" class="btn" onclick="toggleTag('사진찍기좋은')">#사진찍기좋은</a>
        <a href="javascript:;" class="btn" onclick="toggleTag('데이트명소')">#데이트명소</a>
        <a href="javascript:;" class="btn" onclick="toggleTag('문화공간')">#문화공간</a>
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
        <c:url var="prevUrl" value="main.do">
            <c:param name="page" value="${currentPage - 1}" />
            <c:param name="area" value="${param.sigunguCode}" />
            <c:param name="category" value="${param.category}" />
            <c:param name="keyword" value="${param.title}" />
            <c:param name="tags" value="${fn:join(paramValues.tags, ',')}" />
            <c:param name="sort" value="${param.sort}" />
        </c:url>
        <a href="${hasPrev ? prevUrl : 'javascript:void(0);'}" class="${!hasPrev ? 'disabled' : ''}">&lt;</a>

        <!-- 페이지 번호 -->
        <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <c:url var="pageUrl" value="main.do">
                <c:param name="page" value="${i}" />
                <c:param name="area" value="${param.sigunguCode}" />
                <c:param name="category" value="${param.category}" />
                <c:param name="keyword" value="${param.title}" />
                <c:param name="tags" value="${fn:join(paramValues.tags, ',')}" />
                <c:param name="sort" value="${param.sort}" />
            </c:url>
            <a href="${pageUrl}" class="${i == currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>

        <!-- 다음 버튼 -->
        <c:url var="nextUrl" value="main.do">
            <c:param name="page" value="${currentPage + 1}" />
            <c:param name="area" value="${param.sigunguCode}" />
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
<script src="<c:url value='../resources/js/toggleTag.js' />"></script>

</body>
</html>
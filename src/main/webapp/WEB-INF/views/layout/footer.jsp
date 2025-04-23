<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/footer.css' />">
</head>
<body>
<footer>
    <div class="footer-container">
        <!-- 이메일, 전화 문의, 이용약관, 개인정보 처리방침 -->
        <div class="footer-row">
            <div class="footer-left">
                <span class="footer-show email">이메일 문의  </span>
                <span class="footer-email">XXXX@XXXX.com</span>
                <span class="footer-show separator">|</span>
                <span class="footer-show phone">전화 문의  </span>
                <span class="footer-phone">+82-10-1234-5678</span>
            </div>
            <div class="footer-right">
                <a href="/terms.do" class="footer-link strong">이용약관</a>
                <span class="footer-show separator">|</span>
                <a href="/privacy.do" class="footer-link strong">개인정보 처리방침</a>
            </div>
        </div>

        <!-- 주소 정보 -->
        <hr class="footer-divider">
        <p class="footer-address">[47351] 부산점 : 부산 부산진구 중앙대로 627 삼비빌딩 2F, 12F</p>

        <!-- 저작권 정보 -->
        <p class="footer-credit">© 2025 부산관광, 2조-2차 팀프로젝트</p>
    </div>
</footer>
</body>
</html>

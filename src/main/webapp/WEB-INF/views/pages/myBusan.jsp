<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>나만의 부산 여행</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/myBusan.css' />">
    <script src="<c:url value='/resources/js/myBusan.js' />"></script>
</head>
<body>
<jsp:include page="../layout/nav.jsp" />

<main>
    <h1>나만의 부산 여행</h1>
    <div class="customized">
        <div class="guide">원하는 항목을 선택하면, 추천 여행지를 확인할 수 있습니다.</div>

        <!-- 나이 필터 -->
        <div class="section">
            <div class="checkbox_title">나이</div>
            <div class="checkbox_wrap">
                <div class="c-checkbox">
                    <input type="checkbox" name="age_10" value="Y" class="c-checkbox_input" id="age_10">
                    <label for="age_10" class="c-checkbox_label">20대 미만</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="age_20" value="Y" class="c-checkbox_input" id="age_20">
                    <label for="age_20" class="c-checkbox_label">20대</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="age_30" value="Y" class="c-checkbox_input" id="age_30">
                    <label for="age_30" class="c-checkbox_label">30대</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="age_40" value="Y" class="c-checkbox_input" id="age_40">
                    <label for="age_40" class="c-checkbox_label">40대</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="age_50" value="Y" class="c-checkbox_input" id="age_50">
                    <label for="age_50" class="c-checkbox_label">50대</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="age_60" value="Y" class="c-checkbox_input" id="age_60">
                    <label for="age_60" class="c-checkbox_label">60대 이상</label>
                </div>
            </div>
        </div>

        <!-- 함께 가는 사람 필터 -->
        <div class="section">
            <div class="checkbox_title">함께 가는 사람</div>
            <div class="checkbox_wrap">
                <div class="c-checkbox">
                    <input type="checkbox" name="pers_alone" value="Y" class="c-checkbox_input" id="pers_alone">
                    <label for="pers_alone" class="c-checkbox_label">나혼자</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="pers_frend" value="Y" class="c-checkbox_input" id="pers_frend">
                    <label for="pers_frend" class="c-checkbox_label">친구</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="pers_lover" value="Y" class="c-checkbox_input" id="pers_lover">
                    <label for="pers_lover" class="c-checkbox_label">연인</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="pers_family" value="Y" class="c-checkbox_input" id="pers_family">
                    <label for="pers_family" class="c-checkbox_label">가족</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="pers_ople" value="Y" class="c-checkbox_input" id="pers_ople">
                    <label for="pers_ople" class="c-checkbox_label">어르신</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="pers_child" value="Y" class="c-checkbox_input" id="pers_child">
                    <label for="pers_child" class="c-checkbox_label">아이</label>
                </div>
            </div>
        </div>

        <!-- 여행 기간 필터 -->
        <div class="section">
            <div class="checkbox_title">여행 기간</div>
            <div class="checkbox_wrap">
                <div class="c-checkbox">
                    <input type="checkbox" name="perd_thday" value="Y" class="c-checkbox_input" id="perd_thday">
                    <label for="perd_thday" class="c-checkbox_label">당일치기</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="perd_nights_1" value="Y" class="c-checkbox_input" id="perd_nights_1">
                    <label for="perd_nights_1" class="c-checkbox_label">1박 2일</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="perd_nights_2" value="Y" class="c-checkbox_input" id="perd_nights_2">
                    <label for="perd_nights_2" class="c-checkbox_label">2박 3일</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="perd_nights_3" value="Y" class="c-checkbox_input" id="perd_nights_3">
                    <label for="perd_nights_3" class="c-checkbox_label">3박 4일</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="perd_onemm" value="Y" class="c-checkbox_input" id="perd_onemm">
                    <label for="perd_onemm" class="c-checkbox_label">한달살아보기</label>
                </div>
            </div>
        </div>

        <!-- 여행 목적 필터 -->
        <div class="section">
            <div class="checkbox_title">여행 목적</div>
            <div class="checkbox_wrap">
                <div class="c-checkbox">
                    <input type="checkbox" name="purps_nature" value="Y" class="c-checkbox_input" id="purps_nature">
                    <label for="purps_nature" class="c-checkbox_label">자연/경치</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="purps_rest" value="Y" class="c-checkbox_input" id="purps_rest">
                    <label for="purps_rest" class="c-checkbox_label">휴식/힐링</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="purps_food" value="Y" class="c-checkbox_input" id="purps_food">
                    <label for="purps_food" class="c-checkbox_label">미식</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="purps_leport" value="Y" class="c-checkbox_input" id="purps_leport">
                    <label for="purps_leport" class="c-checkbox_label">레포츠/체험</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="purps_driv" value="Y" class="c-checkbox_input" id="purps_driv">
                    <label for="purps_driv" class="c-checkbox_label">드라이브</label>
                </div>
                <div class="c-checkbox">
                    <input type="checkbox" name="purps_shpp" value="Y" class="c-checkbox_input" id="purps_shpp">
                    <label for="purps_shpp" class="c-checkbox_label">쇼핑</label>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../layout/footer.jsp" />
</body>
</html>
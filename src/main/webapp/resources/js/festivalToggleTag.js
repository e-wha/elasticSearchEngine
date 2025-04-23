// 월 선택 토글
function toggleSubTitle(month) {
    const hiddenInput = document.getElementById('subtitles');  // 월을 전달할 hidden input
    const buttons = document.querySelectorAll('.subtitles .btn');  // 월 선택 버튼들

    // 이미 선택된 월이라면 아무 동작도 하지 않음
    if (hiddenInput.value === month) {
        return;
    } else {
        // 새로운 월을 선택했을 때
        hiddenInput.value = month;  // 선택된 월 값을 설정
        buttons.forEach(btn => {
            // 버튼 클릭 시 해당 버튼만 active 클래스 추가
            if (btn.textContent.trim() === month) {
                btn.classList.add('active');
            } else {
                btn.classList.remove('active');
            }
        });
    }

    // 폼 제출
    document.getElementById('tagForm').submit();  // 폼 자동 제출
}

// 초기 로딩 시 active 상태 설정
document.addEventListener("DOMContentLoaded", function () {
    const subtitleInput = document.getElementById('subtitles');  // subtitles input 값
    const subtitleValue = subtitleInput?.value.trim();  // URL에서 전달된 값

    // URL에서 전달된 값에 맞춰 버튼에 active 클래스 추가
    if (subtitleValue) {
        document.querySelectorAll('.subtitles a').forEach(button => {
            const text = button.textContent.trim();
            if (text === subtitleValue) {
                button.classList.add('active');  // 선택된 서브타이틀에 active 클래스 추가
            }
        });
    }
});
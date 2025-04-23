/*
function toggleTag(tag) {
    const hiddenInput = document.getElementById('tags');
    let tags = hiddenInput.value ? hiddenInput.value.split(',') : [];
    const button = document.querySelector(`[onclick="toggleTag('${tag}')"]`);

    // 버튼에 'active' 클래스 추가/제거
    if (tags.includes(tag)) {
        // 이미 선택된 태그라면 제거
        tags = tags.filter(t => t !== tag); // 배열에서 해당 태그 제거
        button.classList.remove('active'); // 'active' 클래스 제거
    } else {
        // 선택되지 않았다면 추가
        tags.push(tag); // 배열에 추가
        button.classList.add('active'); // 'active' 클래스 추가
    }

    // 히든 input에 tags 값을 업데이트
    hiddenInput.value = tags.join(',');

    // 폼을 자동으로 제출
    document.getElementById('tagForm').submit(); // 폼 제출 (검색)
}

document.addEventListener("DOMContentLoaded", function() {
    // 서버에서 전달된 tags 파라미터를 JavaScript에서 처리할 수 있도록 올바르게 전달해야 합니다.
    const tagsFromURL = document.getElementById('tags').value ? document.getElementById('tags').value.split(',') : [];  // URL에서 전달된 tags 값

    // 버튼을 찾고 URL에 있는 태그를 반영
    const buttons = document.querySelectorAll('.tag-checkboxes a');

    buttons.forEach(button => {
        const tag = button.textContent.trim().replace('#', ''); // 버튼의 텍스트에서 # 제거
        if (tagsFromURL.includes(tag)) {
            button.classList.add('active'); // URL에 있는 태그는 'active' 클래스 추가
        }
    });
});*/

// 태그 토글
function toggleTag(tag) {
    const hiddenInput = document.getElementById('tags');
    let tags = hiddenInput.value ? hiddenInput.value.split(',') : [];
    const button = document.querySelector(`[onclick="toggleTag('${tag}')"]`);

    // 버튼에 'active' 클래스 추가/제거
    if (tags.includes(tag)) {
        // 이미 선택된 태그라면 제거
        tags = tags.filter(t => t !== tag); // 배열에서 해당 태그 제거
        button.classList.remove('active'); // 'active' 클래스 제거
    } else {
        // 선택되지 않았다면 추가
        tags.push(tag); // 배열에 추가
        button.classList.add('active'); // 'active' 클래스 추가
    }

    // 히든 input에 tags 값을 업데이트
    hiddenInput.value = tags.join(',');

    // 폼을 자동으로 제출
    document.getElementById('tagForm').submit(); // 폼 제출 (검색)
}

// 초기 로딩 시 active 상태 설정
document.addEventListener("DOMContentLoaded", function () {
    const tagsFromURL = document.getElementById('tags').value.split(',') || [];  // URL에서 전달된 tags 값

    // 버튼을 찾고 URL에 있는 태그를 반영
    const buttons = document.querySelectorAll('.tag-checkboxes a');

    buttons.forEach(button => {
        const tag = button.textContent.trim().replace('#', ''); // 버튼의 텍스트에서 # 제거
        if (tagsFromURL.includes(tag)) {
            button.classList.add('active'); // URL에 있는 태그는 'active' 클래스 추가
        }
    });
});

// 서브타이틀 토글 (중복 선택 방지 없이 선택 상태 유지)
function toggleSubTitle(subtitle) {
    const hiddenInput = document.getElementById('subtitles');
    const categorySelect = document.querySelector("select[name='category']");
    const buttons = document.querySelectorAll('.subtitles .btn');  // 서브타이틀 버튼

    // 서브타이틀을 이미 선택했다면, 선택 상태 유지
    if (hiddenInput.value === subtitle) {
        return; // 이미 선택된 상태라면 아무 동작도 하지 않음
    } else {
        // 새로운 서브타이틀을 선택했을 때
        hiddenInput.value = subtitle;  // 선택된 서브타이틀 값 설정
        categorySelect.value = subtitle;  // 카테고리 값 설정
        buttons.forEach(btn => {
            // 버튼 클릭 시 해당 버튼만 active 클래스 추가
            if (btn.textContent.trim() === subtitle) {
                btn.classList.add('active');
            } else {
                btn.classList.remove('active');
            }
        });
    }

    // 'name' 속성을 빈 문자열로 설정하여 URL에서 제거 효과
    hiddenInput.setAttribute('name', '');

    // 폼 제출
    document.getElementById('tagForm').submit();
}

// 초기 로딩 시 active 상태 설정
document.addEventListener("DOMContentLoaded", function () {
    // 서브타이틀 값 초기화
    const subtitleInput = document.getElementById('subtitles');
    const subtitleValue = subtitleInput?.value.trim();

    if (subtitleValue) {
        // 서브타이틀 버튼에 active 클래스 추가
        document.querySelectorAll('.subtitles a').forEach(button => {
            const text = button.textContent.trim();
            if (text === subtitleValue) {
                button.classList.add('active');  // 선택된 서브타이틀에 active 클래스 추가
            }
        });

        // category와 맞춰주기
        const categorySelect = document.querySelector("select[name='category']");
        if (categorySelect && categorySelect.value !== subtitleValue) {
            categorySelect.value = subtitleValue;  // 카테고리 셀렉트 박스 값 동기화
        }

        // name 속성 제거로 URL에서 제거
        subtitleInput.removeAttribute('name');
    }
});
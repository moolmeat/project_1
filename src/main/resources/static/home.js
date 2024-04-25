document.addEventListener('DOMContentLoaded', function() {
    // 로그인 버튼 클릭 시 이동
    document.getElementById('login-btn').addEventListener('click', function(event) {
        event.preventDefault();
        // 로그인 페이지로 이동
        window.location.href = "/login";
    });
});
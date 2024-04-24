document.addEventListener('DOMContentLoaded', function() {
    // 회원가입 버튼 클릭 시 이동
    document.getElementById('registerButton').addEventListener('click', function(event) {
        event.preventDefault();
        // 회원가입 페이지로 이동
        window.location.href = "/member/join";
    });

    // 로그인 폼 제출 시 처리
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var username = document.getElementById('loginUsername').value;
        var password = document.getElementById('loginPassword').value;
        // 여기에 로그인 처리 로직을 추가합니다.
    });

    // 회원가입 폼 제출 시 처리
    document.getElementById('registerForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var username = document.getElementById('registerUsername').value;
        var password = document.getElementById('registerPassword').value;
        // 여기에 회원가입 처리 로직을 추가합니다.
    });
});

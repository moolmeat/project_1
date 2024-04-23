document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('registerForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var username = document.getElementById('registerUsername').value;
        var password = document.getElementById('registerPassword').value;
        // 여기에 회원가입 처리 로직을 추가합니다.
    });

    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var username = document.getElementById('loginUsername').value;
        var password = document.getElementById('loginPassword').value;
        // 여기에 로그인 처리 로직을 추가합니다.
    });
});
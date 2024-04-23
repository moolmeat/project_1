document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('registerForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var id = document.getElementById('registerID').value;
        var password = document.getElementById('registerPassword').value;
        var email = document.getElementById('registerEmail').value;
        var nickname = document.getElementById('registerNickname').value;

        // 여기에 회원가입 처리 로직을 추가합니다.
    });
});
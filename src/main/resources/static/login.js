document.addEventListener('DOMContentLoaded', function() {
    // 회원가입 버튼 클릭 시 이동
    document.getElementById('registerButton').addEventListener('click', function(event) {
        event.preventDefault();
        // 회원가입 페이지로 이동
        window.location.href = "/member/join";
    });
}
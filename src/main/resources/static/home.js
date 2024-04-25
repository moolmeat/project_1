document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('login-btn').addEventListener('click', function(event) {
        event.preventDefault();
        window.location.href = "/login";
    });
});
function refresh_code() {
    // 获取用户输入的邮箱地址
    var email = $("#your-email").val();

    // 发送 GET 请求
    var path = CONTEXT_PATH + "/user/forget/code?email=" + encodeURIComponent(email);
    $.get(path, function (data) {
        // 将响应的内容显示在页面上
        document.getElementById('resetMessage').text(data.msg);

    });
}

// 在页面加载完成时检查是否有 resetMsg，如果有则显示弹窗，并设置定时器隐藏
document.addEventListener('DOMContentLoaded', function () {
    var resetMsgElement = document.getElementById('resetMessage');

    // 如果存在 resetMsg，则显示弹窗
    if (resetMsgElement) {
        // 设置定时器，在 5 秒后隐藏弹窗
        setTimeout(function () {
            resetMsgElement.style.display = 'none';
        }, 5000);
    }
});


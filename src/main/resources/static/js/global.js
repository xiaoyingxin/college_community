var CONTEXT_PATH = "/community";

window.alert = function(message) {
	if(!$(".alert-box").length) {
		$("body").append(
			'<div class="modal alert-box" tabindex="-1" role="dialog">'+
				'<div class="modal-dialog" role="document">'+
				'<div class="modal-content">'+
					'<div class="modal-header">'+
						'<h5 class="modal-title">提示</h5>'+
						'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
							'<span aria-hidden="true">&times;</span>'+
						'</button>'+
					'</div>'+
					'<div class="modal-body">'+
						'<p></p>'+
					'</div>'+
					'<div class="modal-footer">'+
						'<button type="button" class="btn btn-secondary" data-dismiss="modal">确定</button>'+
					'</div>'+
					'</div>'+
				'</div>'+
			'</div>'
		);
	}

    var h = $(".alert-box").height();
	var y = h / 2 - 100;
	if(h > 600) y -= 100;
    $(".alert-box .modal-dialog").css("margin", (y < 0 ? 0 : y) + "px auto");
	
	$(".alert-box .modal-body p").text(message);
	$(".alert-box").modal("show");

	// 在页面加载完成时检查是否有 resetMsg，如果有则显示弹窗，并设置定时器隐藏
	document.addEventListener('DOMContentLoaded', function() {
		var resetMsgElement = document.getElementById('resetMessage');

		// 如果存在 resetMsg，则显示弹窗
		if (resetMsgElement) {
			// 设置定时器，在 5 秒后隐藏弹窗
			setTimeout(function() {
				resetMsgElement.style.display = 'none';
			}, 5000);
		}
	});
	function refresh_code() {
		// 获取用户输入的邮箱地址
		var email = $("#your-email").val();

		// 发送 GET 请求
		var path = CONTEXT_PATH + "/user/forget/code?email=" + encodeURIComponent(email);
		$.get(path, function(data) {
			// 将响应的内容显示在页面上
			displayResponse(data.msg);
		});
	}

	function displayResponse(response) {
		// 创建一个弹窗或者显示一个元素，将响应的内容设置进去
		var responseElement = document.createElement('div');
		responseElement.innerHTML = response;

		// 可以使用一些样式使得弹窗看起来更好看
		responseElement.style.padding = '10px';
		responseElement.style.border = '1px solid #ccc';
		responseElement.style.borderRadius = '5px';
		responseElement.style.marginTop = '10px';

		// 将弹窗或元素添加到页面中
		document.body.appendChild(responseElement);

		// 设置定时器，在一定时间后移除弹窗或元素
		setTimeout(function() {
			responseElement.remove();
		}, 5000); // 5秒后移除
	}
}

function logout() {
    $.ajax({
        type: 'POST',//方法类型
        dataType: 'json',//预期服务器返回的数据类型
        url: '/logout',//url
        success: function (data) {
            if (data.success) {
                layer.msg("退出登录",{icon:6,time:1000},function () {
                    location.href = '/login';
                })
            } else {
                xl.alert(data.msg);
            }
        }
    })
}
$(".profile-element ul").mousedown(function(){
	setTimeout(function(){$(".dropdown-toggle").trigger("click");},100);
})

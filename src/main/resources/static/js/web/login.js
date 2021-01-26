$(function () {
    $('#switch_qlogin').click(function () {
        $('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_bottom').animate({left: '0px', width: '70px'});
        $('#qlogin').css('display', 'none');
        $('#web_qr_login').css('display', 'block');
    });
    $('#switch_login').click(function () {
        $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_bottom').animate({left: '154px', width: '70px'});
        $('#qlogin').css('display', 'block');
        $('#web_qr_login').css('display', 'none');
    });
});

function logintab() {
    scrollTo(0);
    $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
    $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
    $('#switch_bottom').animate({left: '154px', width: '96px'});
    $('#qlogin').css('display', 'none');
    $('#web_qr_login').css('display', 'block');
}

//刷新验证码
function reloadCaptcha() {
    $('#captcha').attr('src', '/images/captcha?' + Math.random());
}

//忘记密码
function ForgotPass() {
    var ob = {
        title: '<label>密码管理</label>',
        width: 900,
        height: 500,
        url: '/user/forgotPass'
    }
    xl.open(ob)
}
var countdown = 60;
function settime() { //发送验证码倒计时
    var obj = $("#send-captcha");
    if (countdown == 0) {
        obj.attr('disabled',false);
        obj.val("发送验证码");
        countdown = 60;
        return true;
    } else {
        obj.attr('disabled',true);
        obj.val("重新发送(" + countdown + "S)");
        countdown--;
        setTimeout(function() {
            settime(obj)
        },1000)
    }
}
//发送验证码
function sendCaptcha() {
    var email = $("#email").val();
    if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($('#email').val())) || $('#email').val() == null || $('#email').val() == "") {
        xl.alert("请输入正确格式的邮箱格式!");
    } else {
        $.ajax({
            url: '/email/captcha',
            dataType: 'json',
            data: {email: email},
            type: 'post',
            success: function (data) {
                if (data.success) {
                    settime();
                } else {
                    xl.alert("发送验证码失败，请重试!");
                }
            }
        })
    }
}

//登录
function submitAuth() {
    var username = $('#u').val();
    var password = $('#p').val();
    var code = $('#auth-code').val();
    if (username == null || username == "") {
        xl.alert("用户名不能为空!");
        return false;
    }
    if (password == null || password == "") {
        xl.alert("密码不能为空!");
        return false;
    }
    if (code == null || code == "") {
        xl.alert("验证码不能为空!");
        return false;
    }
    $.ajax({
        type: 'POST',//方法类型
        dataType: 'json',//预期服务器返回的数据类型
        url: '/auth',//url
        data: {'username': username, 'password': password, 'code': code},
        success: function (data) {
            if (data.success) {
                    layer.msg("登录成功", {
                        icon: 6,
                        time: 1000
                    }, function () {
                        location.href = '/index';
                    })
            } else {
                xl.alert(data.msg);
                reloadCaptcha();
            }
        }
    })
}

//注册
function registered() {
    var pwdmin = 6;
    if ($('#username').val() == "" || $('#username').val() == null) {
        $('#username').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font color='red'><b>*用户名不能为空</b></font>");
        return false;
    }
    if (!(/^[0-9a-zA-Z]+$/.test($('#username').val()))) {
        $('#username').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font color='red'><b>*用户名只能是字母或者数字</b></font>");
        return false;
    }

    if ($('#username').val().length < 4 || $('#username').val().length > 16) {
        $('#user').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font color='red'><b>*用户名位4-16字符</b></font>");
        return false;
    }
    if ($('#passwd').val().length < pwdmin) {
        $('#passwd').focus();
        $('#userCue').html("<font color='red'><b>*密码不能小于" + pwdmin + "位</b></font>");
        return false;
    }
    if ($('#passwd2').val() != $('#passwd').val()) {
        $('#passwd2').focus();
        $('#userCue').html("<font color='red'><b>*两次密码不一致！</b></font>");
        return false;
    }
    if ($('#nickname').val() == null || $('#nickname').val() == "") {
        $('#nickname').focus();
        $('#userCue').html("<font color='red'><b>*昵称不能为空！</b></font>");
        return false;
    }
    if (!(/^1[34578]\d{9}$/.test($('#tel').val())) || $('#tel').val().length < 11 || $('#tel').val().length > 11) {
        $('#tel').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font color='red'><b>*手机号码格式不正确</b></font>");
        return false;
    } else {
        $('#tel').css({
            border: "1px solid #D7D7D7",
            boxShadow: "none"
        });
    }
    if ($('#address').val() == null || $('#address').val() == "") {
        $('#address').focus();
        $('#userCue').html("<font color='red'><b>*地址不能为空！</b></font>");
        return false;
    }
    if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($('#email').val())) || $('#email').val() == null || $('#email').val() == "") {
        $('#email').focus();
        $('#userCue').html("<font color='red'><b>*邮箱格式不正确！</b></font>");
        return false;
    }
    if ($('#Captcha').val() == null || $('#Captcha').val() == "") {
        $('#Captcha').focus();
        $('#userCue').html("<font color='red'><b>*验证码不能为空！</b></font>");
        return false;
    }
   $.ajax({
        type: 'POST',//方法类型
        dataType: 'json',//预期服务器返回的数据类型
        url: '/user/registered',//url
        data: $("#regUser").serialize(),
        success: function (data) {
            if (data.success) {
                layer.msg("请等待管理员审批!", {icon: 6, time: 2000}, function () {
                    location.reload();
                })
            } else {
                xl.alert(data.msg);
            }
        }
    });
}




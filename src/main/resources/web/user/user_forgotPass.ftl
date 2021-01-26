<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<#include "${ctx!}/common/common_css.ftl" />
<link rel="stylesheet" href="${ctx!}/css/container.css">
<style>
    .pp_main_subnav {
        margin-top: 10px;
        border: 1px solid #e6e5e5;
    }

    .pp_main_mb {
        margin-bottom: 40px;
    }

    .pp_main {
        width: 100%;
        border: 0px;
        padding-bottom: 20px;
        position: relative;
    }

    body, div, h1, h2, h3, h4, h5, h6, p, dl, dt, dd, ul, ol, li, form, th, td, table, label, article, aside, dialog, footer, header, section, footer, nav, figure, hgroup {
        margin: 0;
        padding: 0;
        border: 0;
        outline: 0;
        font-size: 100%;
        vertical-align: baseline;
    }

    body {
        color: #333;
        font: 12px/18px "\5FAE\8F6F\96C5\9ED1", Arial, sans-serif, "\5B8B\4F53";
    }

    .pp_pw_retakeStep {
        padding-top: 60px;
        font-size: 14px;
    }

    .retakeStep_wrap {
        width: 681px;
        margin: 0 auto;
    }

    .stepOne {
        background-position: left top;
    }

    .step_bg {
        width: 681px;
        height: 33px;
    }

    .pp_icon {
        background: transparent url(../../images/passport_icon.png) no-repeat scroll;
        overflow: hidden;
        display: inline-block;
        vertical-align: middle;
    }

    .step_list .step_pass {
        color: #fff;
    }

    .step_list ul li {
        width: 227px;
        height: 33px;
        line-height: 33px;
        font-size: 14px;
        text-align: center;
        float: left;
    }

    ul, li {
        list-style: none;
    }

    .step_con {
        margin: 40px 0 0 143px;
    }

    .pp_account_form_item {
        height: 40px;
        line-height: 34px;
        font-size: 14px;
    }

    .tr {
        text-align: right;
    }

    label {
        width: 84px;
    }

    .input-common {
        display: inline-block;
        width: 228px;
        height: 28px;
        border: 1px solid #e4e4e4;
        padding: 0 10px;
        line-height: 28px \9;
        vertical-align: middle;
    }

    .send-captcha {
        font-size: 12px;
        color: #2e82ff;
        border: 0px;
        background-color: #ffffff;
    }

    .btn-sure {
        margin-left: 30%;
        margin-top: 6%;
        background-color: #80c200;
    }

    .pp_icon2 {
        background: transparent url(../../images/passport_icon.png) no-repeat scroll -0px -40px;
        overflow: hidden;
        display: inline-block;
        vertical-align: middle;
    }

    .step_con2 {
        margin: 40px 0 0 123px;
    }

    .step_con2 label {
        width: 100px !important;
    }
    .pp_icon3 {
        background: transparent url(../../images/passport_icon.png) no-repeat scroll -0px -80px;
        overflow: hidden;
        display: inline-block;
        vertical-align: middle;
    }
    .pp_icon4 {
        background: transparent url(../../images/passport_icon.png) no-repeat scroll -480px -117px;
        overflow: hidden;
        display: inline-block;
        vertical-align: middle;
        width: 40px !important;
        height: 40px !important;
    }
    .pp_account_form_item3 {
        height: 40px;
        line-height: 34px;
        font-size: 16px;
        margin-top: 100px;
        width: 280px;
        margin-left: 180px;
    }
</style
<body>
<div class="pp_main pp_main_mb pp_main_subnav clearfix">
    <!--外框架结束-->
    <div class="pp_pw_retakeStep">
        <div class="retakeStep_wrap verifyInfo">
            <div class="pp_icon step_bg stepOne">
                <div class="step_list">
                    <ul>
                        <li class="step_pass">1.输入绑定邮箱</li>
                        <li>2.设置新密码</li>
                        <li>3.设置成功</li>
                    </ul>
                </div>
            </div>
            <div class="step_con" id="J_verify-email">
                <div class="pp_account_form_item">
                    <div>
                        <span class="vl-inline item_title_big tr">
                            <label for="">输入用户名：</label>
                        </span>
                        <span class="vl-inline item_input">
                            <input class="input-common" type="text" id="username" placeholder="请输入用户名"
                                   data-find-pw="true">
                        </span>
                    </div>
                    <div>
                        <span class="vl-inline item_title_big tr">
                            <label for="">绑定的邮箱：</label>
                        </span>
                        <span class="vl-inline item_input">
                            <input class="input-common" type="email" id="email" placeholder="请输入用户名绑定的邮箱地址"
                                   data-find-pw="true">
                            <input id="send-captcha" class="send-captcha" type="button" value="发送验证码"
                                   onclick="sendCaptcha();"/>
                        </span>
                    </div>
                    <div>
                        <span class="vl-inline item_title_big tr">
                                    <label for="">验证码：</label>
                        </span>
                        <span class="vl-inline item_input">
                                    <input class="input-common" id="captcha" type="text" placeholder="请输入验证码"
                                           data-find-pw="true">
                        </span>
                    </div>
                    <button class="btn btn-sure btn-sure-one ">确认</button>
                </div>
            </div>
        </div>
        <div class="retakeStep_wrap verifyInfo2" hidden>
            <div class="pp_icon2 step_bg stepOne">
                <div class="step_list">
                    <ul>
                        <li>1.输入绑定邮箱</li>
                        <li class="step_pass">2.设置新密码</li>
                        <li>3.设置成功</li>
                    </ul>
                </div>
            </div>
            <div class="step_con2" id="J_verify-email">
                <div class="pp_account_form_item">
                    <div>
                        <span class="vl-inline item_title_big tr">
                            <label for="">输入新密码：</label>
                        </span>
                        <span class="vl-inline item_input">
                            <input class="input-common" type="password" id="password" placeholder="请输入新密码"
                                   data-find-pw="true">
                        </span>
                    </div>
                    <div>
                        <span class="vl-inline item_title_big tr">
                            <label for="">再次输入密码：</label>
                        </span>
                        <span class="vl-inline item_input">
                            <input class="input-common" type="password" id="password2" placeholder="请再次输入新密码"
                                   data-find-pw="true">
                        </span>
                    </div>
                    <button class="btn btn-sure btn-sure-two">确认</button>
                </div>
            </div>
        </div>
        <div class="retakeStep_wrap verifyInfo3" hidden>
            <div class="pp_icon3 step_bg stepOne">
                <div class="step_list">
                    <ul>
                        <li>1.输入绑定邮箱</li>
                        <li>2.设置新密码</li>
                        <li class="step_pass">3.设置成功</li>
                    </ul>
                </div>
            </div>
            <div class="step_con3" id="J_verify-email">
                <div class="pp_account_form_item3">
                    <div class="pp_icon4"></div>
                    <div style="float: right;margin-top: 3px;">修改成功，请返回登录界面登录！</div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "${ctx!}/common/common_js.ftl" />
<script type="text/javascript">
    $(function () {
        $(".btn-sure-one").click(function () {
            var email = $("#email").val();
            var username = $("#username").val();
            var captcha = $("#captcha").val()
            if (null == username || "" == username) {
                xl.alert("用户名不能为空!");
                return false;
            }
            if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($('#email').val())) || $('#email').val() == null || $('#email').val() == "") {
                xl.alert("请输入正确格式的邮箱格式!");
                return false;
            }
            if (null == captcha || "" == captcha) {
                xl.alert("验证码不能为空!");
                return false;
            }
            $.ajax({
                url: '/user/verifyInfo',
                dataType: 'json',
                type: 'post',
                data: {"email": email, "username": username, "captcha": captcha},
                success: function (data) {
                    if (data.success) {
                        $(".verifyInfo").hide();
                        $(".verifyInfo2").show();
                    } else {
                        xl.alert(data.msg);
                    }
                }
            })
        })

        $(".btn-sure-two").click(function () {
            var password = $("#password").val();
            var password2 = $("#password2").val();
            if (null == password || "" == password) {
                xl.alert("新密码不能为空!");
                return false;
            }
            if (null == password2 || "" == password2) {
                xl.alert("再次输入密码不能为空!");
                return false;
            }
            if(password2 != password){
                xl.alert("两次输入的密码不一致，请重新填写！");
                return false;
            }
            $.ajax({
                url: '/user/updatePassword',
                dataType: 'json',
                type: 'post',
                data: {"password": password, "username": $("#username").val()},
                success: function (data) {
                    if (data.success) {
                        $(".verifyInfo2").hide();
                        $(".verifyInfo3").show();
                    } else {
                        xl.alert(data.msg);
                    }
                }
            })
        })
    })
    var countdown = 60;

    function settime() { //发送验证码倒计时
        var obj = $("#send-captcha");
        if (countdown == 0) {
            obj.attr('disabled', false);
            obj.val("发送验证码");
            countdown = 60;
            return true;
        } else {
            obj.attr('disabled', true);
            obj.val("重新发送(" + countdown + "S)");
            countdown--;
            setTimeout(function () {
                settime(obj)
            }, 1000)
        }
    }

    //发送验证码
    function sendCaptcha() {
        var email = $("#email").val();
        var username = $("#username").val();
        if (null == username || "" == username) {
            xl.alert("用户名不能为空!");
            return false;
        }
        if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($('#email').val())) || $('#email').val() == null || $('#email').val() == "") {
            xl.alert("请输入正确格式的邮箱格式!");
        } else {
            $.ajax({
                url: '/email/captcha2',
                dataType: 'json',
                data: {email: email, username: username},
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
</script>
</body>
</html>
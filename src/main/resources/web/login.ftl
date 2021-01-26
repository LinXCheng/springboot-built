<!DOCTYPE html>
<html lang="en">
<title>登录</title>
<#include "${ctx!}/common/common_css.ftl"/>
<link rel="stylesheet" href="${ctx!}/css/login.css">
<style>
</style>
<body style="background:#fff url(../images/login.jpg) 50% 0 no-repeat;">
<h1>登录</h1>
<div class="login" style="margin-top:50px;">
    <div class="header">
        <div class="switch" id="switch">
            <a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
            <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>
            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 285px;">
        <div class="web_login" id="web_login">
            <div class="login-box">
                <div class="login_form">
                    <form action="javascript:void(0)" name="loginform" accept-charset="utf-8" id="form" class="loginForm" method="post">
                        <input name="did" value="0" type="hidden">
                        <input name="to" value="log" type="hidden">
                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>
                            <div class="inputOuter" id="uArea">
                                <input id="u" name="username" class="inputstyle username" type="text">
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>
                            <div class="inputOuter" id="pArea">
                                <input id="p" name="password" class="inputstyle password" type="password">
                            </div>
                        </div>
						<div class="auth-div">
							<label class="auth-tip" for="p">验证码：</label>
							<input id="auth-code"type="text" value=""maxlength="4" class="inputstyle auth-input">
                            <img alt="点击刷新验证码" src="${ctx!}/images/captcha" onclick="reloadCaptcha();" id="captcha"/>
                        </div>
                        <div style="padding-left:50px;margin-top:20px;">
                            <input value="登 录" type="submit" style="width:150px;" class="button_blue" onclick="submitAuth();">
                            <a class="pass-fgtpwd" href="#" onclick="ForgotPass();">忘记密码？</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="qlogin" id="qlogin" style="display: none; ">
        <div class="web_login">
            <form name="form2" id="regUser" accept-charset="utf-8" action="javascript:void(0)" method="post">
                <ul class="reg_form" id="reg-ul">
                    <div id="userCue" class="cue">快速注册请注意格式</div>
                    <li>
                        <label for="username" class="input-tips2">用户名：</label>
                        <div class="inputOuter2">
                            <input id="username" name="username" maxlength="16" class="inputstyle2" type="text" placeholder="请输入用户名">
                        </div>
                    </li>
                    <li>
                        <label for="passwd" class="input-tips2">密码：</label>
                        <div class="inputOuter2">
                            <input id="passwd" name="password" maxlength="16" class="inputstyle2" type="password" placeholder="请输入密码">
                        </div>
                    </li>
                    <li>
                        <label for="passwd2" class="input-tips2">确认密码：</label>
                        <div class="inputOuter2">
                            <input id="passwd2" name="password2" maxlength="16" class="inputstyle2" type="password" placeholder="请确认密码">
                        </div>
                    </li>
                    <li>
                        <label for="nickname" class="input-tips2">昵称：</label>
                        <div class="inputOuter2">
                            <input id="nickname" name="nickname"  class="inputstyle3" type="text" maxlength="16"placeholder="请输入昵称">
                        </div>
                    </li>
                    <li>
                        <label for="tel" class="input-tips2">手机号码：</label>
                        <div class="inputOuter2">
                            <input id="tel" name="tel" maxlength="11" class="inputstyle2" type="text"placeholder="请输入手机号">
                        </div>
                    </li>
                    <li>
                        <label for="tel" class="input-tips2">地址：</label>
                        <div class="inputOuter2">
                            <input id="address" name="address" class="inputstyle3" type="text"placeholder="请输入地址">
                        </div>
                    </li>
                    <li>
                        <label for="tel" class="input-tips2">邮箱：</label>
                        <div class="inputOuter2">
                            <input id="email" name="email"  class="inputstyle2 email-input" type="email" required placeholder="请输入邮箱">
                            <input id="send-captcha" class="send-captcha" type="button" value="发送验证码" onclick="sendCaptcha();"/>
                        </div>
                    </li>
                    <li>
                        <label for="tel" class="input-tips2">验证码：</label>
                        <div class="inputOuter2">
                            <input id="Captcha" name="Captcha" maxlength="6" class="inputstyle2" type="text" placeholder="请输入验证码">
                        </div>
                    </li>
                        <div class="inputArea">
                            <div style="padding-left:80px;margin-top:5px;"><input  value="注 册" style="width:150px;" class="button_blue" onclick="registered();" ></div>
                        </div>
                    <div class="cl"></div>
                </ul>
            </form>
        </div>
    </div>

</div>
<#include "${ctx!}/common/common_js.ftl"/>
<script src="${ctx!}/js/jquery-3.3.1.min.js"></script>
<script src="${ctx!}/layui/modules/layer.js"></script>
<script src="${ctx!}/js/xl.js"></script>
<script src="${ctx!}/js/web/login.js"></script>
</body>
</html>
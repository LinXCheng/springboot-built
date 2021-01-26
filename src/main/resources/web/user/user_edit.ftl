<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<#include "${ctx!}/common/common_css.ftl" />
<link rel="stylesheet" href="${ctx!}/css/container.css">
<style>
._container {
	width: 50%;
}
.error {
	color: #f00;
}
.send-Captcha {
    font-size: 12px;
    color: #2e82ff;
    border: 0px;
    background-color: #ffffff;
    margin-top: 8px;
}
#email {
	width: 172px !important;
	float: left;
}
</style>
<body>
	<div class="_container">
		<form class="cmxform" id="formId" method="post" action="javascript:void(0);">
			<input id="id" type="hidden" name="id" value="${(userList.id)!}" />
			<div class="form-group row">
				<label for="username" class="col-sm-2 ">用户名:</label>
				<div class="col-sm-8">
					<input class="form-control" id="username" name="username"
						value="${(userList.username)!}" placeholder="请输入用户名">
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group row">
				<label for="password" class="col-sm-2 ">密码:</label>
				<div class="col-sm-8">
					<input class="form-control" id="password" type="password"
						name="password" <#if userList?exists>value="********"<#else>value=""</#if> placeholder="请输入密码">
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group row">
				<label for="password" class="col-sm-2 ">确认密码:</label>
				<div class="col-sm-8">
					<input class="form-control" id="pass2" type="password" name="pass2"
						<#if userList?exists>value="********"<#else>value=""</#if> placeholder="请再次输入密码">
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group row">
				<label for="nickname" class="col-sm-2 ">昵称:</label>
				<div class="col-sm-8">
					<input class="form-control" id="nickname" name="nickname"
						value="${(userList.nickname)!}" placeholder="请输入昵称">
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group row">
				<label for="tel" class="col-sm-2 ">手机号码:</label>
				<div class="col-sm-8">
					<input class="form-control" id="tel" name="tel"
						value="${(userList.tel)!}" placeholder="请输入联系方式" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group row">
				<label for="address" class="col-sm-2 ">地址:</label>
				<div class="col-sm-8">
					<input class="form-control" id="address" name="address"
						value="${(userList.address)!}" placeholder="请输入用户地址" />
				</div>
				<div class="col-sm-2"></div>
			</div>
            <div class="form-group row">
                <label for="email" class="col-sm-2 ">邮箱:</label>
                <div class="col-sm-8">
					<#if userList?exists>
                        <input class="form-control" name="email" type="email"
                               value="${(userList.email)!}" placeholder="请输入邮箱" readonly/>
					<#else>
						 <input class="form-control" id="email" name="email" type="email"
                                value="${(userList.email)!}" placeholder="请输入邮箱"/>
                        <input id="send-Captcha" class="send-Captcha" type="button" value="发送验证码" onclick="sendCaptcha();"/>
					</#if>
				</div>
                <div class="col-sm-2"></div>
            </div>
			<#if userList?exists>
			<#else>
				<div class="form-group row">
					<label for="Captcha" class="col-sm-2 ">验证码:</label>
					<div class="col-sm-8">
						<input class="form-control" id="Captcha" name="Captcha"
							   placeholder="请输入验证码" maxlength="6"/>
					</div>
					<div class="col-sm-2"></div>
				</div>
			</#if>
			<div class="form-group row">
				<label for="roleId" class="col-sm-2">角色:</label>
				<div class="col-sm-8">
					<select id="roleId" name="roleId" class="form-control">
						<option value="">---请选择角色---</option>
						<#if rolelist?exists> 
							<#list rolelist as rolelist> 
								<#if userList?exists && userList.roleId?exists>
									<#if userList.roleId == rolelist.id>
										<option value="${(rolelist.id)!}" selected>${(rolelist.roleName)!}</option>
									<#else>
										<option value="${(rolelist.id)!}">${(rolelist.roleName)!}</option>
									</#if> 
								<#else>
										<option value="${(rolelist.id)!}">${(rolelist.roleName)!}</option>
								</#if>
							</#list> 
						</#if>
					</select>
				</div>
				<div class="col-sm-2"></div>
			</div>
            <div class="form-group row">
                <label for="username" class="col-sm-2 ">工资:</label>
                <div class="col-sm-8">
                    <input oninput="checkMoney(this);" onchange="ValFloat(this);" class="form-control" id="wage" name="wage"
                           value="${(userList.wage?string('0.00'))!}" placeholder="请输入工资">
                </div>
                <div class="col-sm-2"></div>
            </div>
			<div class="form-group row">
				<label for="state" class="col-sm-2 ">有效标志:</label>
				<div class="col-sm-8">
					<span style="float: left; margin: 0 40px;"> <input
						type="radio" name="deleteStatus"<#if userList?exists><#if
						userList.deleteStatus==0>checked</#if><#else>checked</#if>
						value="0"> 启用
					</span> <span> <input type="radio" name="deleteStatus"<#if
						userList?exists><#if userList.deleteStatus==1>checked</#if></#if>
						value="1"> 禁用
					</span>
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group btn-center">
				<span class="my-btn"> <input
					class="btn btn-primary submit-btn" type="submit" value="保存"/> <input class="btn btn-danger"
					type="reset" value="清空">
				</span>
			</div>
		</form>
	</div>
	<#include "${ctx!}/common/common_js.ftl" />
	<script>
$(function() {
	<#if userList?exists>
		<#if userList.type==1>
			$("input").attr("readonly","readonly");
			$("input[type='radio']").attr("readonly",false);
			$("#wage").attr("readonly",false);
		</#if>
	</#if>
	//表单验证
	var validate = $("#formId").validate({
		debug : true, //调试模式取消submit的默认提交功能
		focusInvalid : false, //当为false时，验证无效时，没有焦点响应
		onkeyup : false,
		submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
			submitForm();
		},
		rules : {
			username : {
				required : true,
				checkUsername:true
			},
			password : {
				required : true
			},
			pass2 : {
				required : true,
				equalTo : "#password"
			},
			nickname : {
				required : true
			},
			tel : {
				required : true,
				isphoneNum : true,
			},
			address : {
				required : true
			},
            email :{
                required: true,
                isEmail: true
			}
		},
		messages : {
			username : {
				required : "用户名必填项",
			},
			password : {
				required : "密码必填项"
			},
			pass2 : {
				required : "确认密码必填项",
				equalTo : "两次密码输入不一致"
			},
			nickname : {
				required : "昵称必须输入"
			},
			tel : {
				required : "手机号码是必填项",
			},
			address : {
				required : "地址必须输入"
			},
            email :{
                required: "邮箱必须绑定",
            }
		}
	});
});
	jQuery.validator.addMethod("isphoneNum", function(value, element) {
		var length = value.length;
		var mobile = /^1[3|5|8]{1}[0-9]{9}$/;
		return this.optional(element)
				|| (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");
	jQuery.validator.addMethod("isEmail", function(value, element) {
	    var flag = true;
        if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)) || null == value || "" == value) {
            flag=false;
		}
		return flag;
	}, "请输入有效的邮箱号");
	jQuery.validator.addMethod("checkUsername", function(value, element) {
		var flag=false;
		$.ajax({
			url : "/user/queryExist", //后台处理程序
			type : "post", //数据发送方式
			dataType : "json", //接受数据格式
			async:false,
			data : { //要传递的数据
				username : function() {
					return $("#username").val();
				},
				id:function() {
					return $("#id").val();
				}
			},
			success: function(data) {
		     	if(data.success){
		     		flag=true;
		         } else {
		        	 flag=false;
		         }
		     }
		})
		return flag;
	}, "该用户名已存在");

//提交修改
function submitForm() {
	<#if userList?exists>
	<#else>
	var Captcha = $("#Captcha").val();
	if(null == Captcha || "" == Captcha){
		xl.alert("请输入验证码!")
		return false;
	}
	if($("#Captcha").length<6){
		xl.alert("请输入正确的6位数验证码!");
		return false;
	}
	</#if>
	$.ajax({
		type : "POST",//方法类型
		dataType : "json",//预期服务器返回的数据类型
		url : "/user/editUser",//url
		data : $('#formId').serialize(),
		anync:false,
		success : function(data) {
			if (data.success) {
				layer.msg("提交成功!", {
					icon : 6,
					time : 1000
				}, function() {
					parent.onreload();
				})
			} else {
				xl.alert(data.msg);
			}
		},
		error : function() {
			xl.alert("提交失败！");
		}
	});
}
var countdown = 60;
function settime() { //发送验证码倒计时
    var obj = $("#send-Captcha");
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
</script>
</body>
</html>
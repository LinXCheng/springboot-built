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
</style>
<body>
	<div class="_container">
		<form class="cmxform" id="formId" method="post" action="javascript:void(0);">
			<div class="form-group row">
				<label for="password" class="col-sm-2 ">原密码:</label>
				<div class="col-sm-8">
					<input class="form-control" id="oldpass" name="oldpass" type="password" placeholder="请输入原密码">
				</div>
				<div class="col-sm-2"></div>
			</div>			
			<div class="form-group row">
				<label for="password" class="col-sm-2 ">新密码:</label>
				<div class="col-sm-8">
					<input class="form-control" id="password" type="password"
						name="password" placeholder="请输入密码">
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group row">
				<label for="password" class="col-sm-2 ">确认新密码:</label>
				<div class="col-sm-8">
					<input class="form-control" id="pass2" type="password" name="pass2" placeholder="请再次输入密码">
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group btn-center">
				<span class="my-btn"> <input
					class="btn btn-primary submit-btn" type="submit" value="保存"/>
					<input class="btn btn-danger" type="reset" value="清空">
				</span>
			</div>			
		</form>
	</div>
	<#include "${ctx!}/common/common_js.ftl" />
	<script>
$(function() {
	//表单验证
	var validate = $("#formId").validate({
		debug : true, //调试模式取消submit的默认提交功能
		focusInvalid : false, //当为false时，验证无效时，没有焦点响应
		onkeyup : false,
		submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
			submitForm();
		},

		rules : {
			oldpass : {
				required : true,
			},
			password : {
				required : true
			},
			pass2 : {
				required : true,
				equalTo : "#password"
			},
		},
		messages : {
			oldpass : {
				required : "原密码必填项",
			},
			password : {
				required : "密码必填项"
			},
			pass2 : {
				required : "确认密码必填项",
				equalTo : "两次密码输入不一致"
			}
		}
	});
});
//提交修改
function submitForm() {
	$.ajax({
		type : "POST",//方法类型
		dataType : "json",//预期服务器返回的数据类型
		url : "/user/savePassword",//url
		data :{"oldpass":$("#oldpass").val(),"password":$("#password").val()},
		success : function(data) {
			if (data.success) {
				layer.msg("保存成功,请重新登录!", {
					icon : 6,
					time : 1000
				}, function() {
					window.top.location.reload();
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
</script>
</body>
</html>
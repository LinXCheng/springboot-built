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
#previewPicture {
	float: left;
	width:100%;
}
#reUpload {
	margin-top: 6%;
	margin-left: 10%;
}
#imgdiv {
	float: left;
}
</style>
<div class="_container">
	<form class="cmxform" id="formId" method="post"
		action="javascript:void(0);">
		<input id="id" type="hidden" name="id" value="${(userList.id)!}" />
		<div class="form-group row">
			<label for="username" class="col-sm-2 ">用户名:</label>
			<div class="col-sm-8">
				<input class="form-control" id="username" name="username"
					value="${(userList.username)!}" placeholder="请输入用户名" readonly="readonly">
			</div>
			<div class="col-sm-2"></div>
		</div>
        <div class="form-group row">
            <label for="email" class="col-sm-2 ">邮箱:</label>
            <div class="col-sm-8">
                <input class="form-control" id="email" name="email"
                       value="${(userList.email)!}" readonly="readonly">
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
					value="${(userList.tel)!}" placeholder="请输入联系方式" maxlength="11"
					onkeyup="value=value.replace(/[^\d]/g,'')" />
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
			<label class="col-sm-2">用户头像</label>
			<div class="col-sm-8">
			<input type="hidden" name="photo" id="photo" value="${(userList.photo)!}"> 
				<div id="previewPicture">
					<#if (userList.photo)?exists> 
						<img id="imgdiv" src="${ctx!}/${(userList.photo)!}" style="width: 80px; height: 80px; margin-bottom: 5px"> 
						<button type="button" class="imgUpload" id="imgUpload"
							style="display: none;">
							<i class="fa fa-plus"></i>上传图片
						</button>
						<button type="button" class="imgUpload" id="reUpload">
							<i class="fa fa-plus"></i>重新上传
						</button>						
					<#else>
					<button type="button" class="imgUpload" id="imgUpload">
						<i class="fa fa-plus"></i>上传图片
					</button>
					<button type="button" class="imgUpload" id="reUpload"
						style="display: none;">
						<i class="fa fa-plus"></i>重新上传
					</button>
					</#if>
				</div>
			</div>
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
	function uploadErro(){
		$('#imgdiv').remove();
        $('#reUpload').hide();
        $('#imgUpload').show();
        $('#photo').val("");
	}
	$(document).on("click","#reUpload",function () {
        $('#imgdiv').remove();
        $('#reUpload').hide();
        $('#imgUpload').show();
        $('#imgUpload').trigger('upload');
    });
	//上传图片
	layui.use('upload', function () {
	    var upload = layui.upload;
	    var uploadInst = upload.render({
	        elem: '#imgUpload', //绑定元素
	        url: '/upload/setFileUpload', //上传接口
	        multiple: false,
	        before: function (obj) {
	            //回显
	            obj.preview(function (index, file, result) {
	                $('#imgUpload').hide();
	                $('#reUpload').before('<img id="imgdiv" src="' + result + '" alt="' + file.name + '" class="layui-upload-img" height="80" width="80" style="margin-bottom: 5px;">');
	                $('#reUpload').show();
	            });
	        }
	        , done: function (res) {
	            //上传完毕回调
	            if (res.success) {
	            	$('#photo').val(res.result.path);
	                return layer.msg('上传成功!', {time: 1000});
	            } else {
	            	uploadErro()
	                return layer.msg(res.msg, {time: 1000});
	            }
	            delete files[index];
	        }
	        , error: function () {
	        	uploadErro();
	            return layer.msg('上传失败', {time: 1000});
	        }
	    });
	});
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
				required : true
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
			}
		},
		messages : {
			username : {
				required : "用户名必填项",
			},
			nickname : {
				required : "昵称必须输入"
			},
			tel : {
				required : "手机号码是必填项",
			},
			address : {
				required : "地址必须输入"
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

//提交修改
function submitForm() {
	$.ajax({
		type : "POST",//方法类型
		dataType : "json",//预期服务器返回的数据类型
		url : "/user/saveUserInfo",//url
		data : $('#formId').serialize(),
		success : function(data) {
			if (data.success) {
				layer.msg("提交成功!", {
					icon : 6,
					time : 1000
				}, function() {
					window.top.location.reload();
				})
			} else {
				xl.alert("修改失败，请重试！");
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
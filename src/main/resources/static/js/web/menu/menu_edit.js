var check=false;
$(function () {
	$("#pid").change(function(){
		var pid=$("#pid").val();
		if(pid!=0){
			$(".menuType").hide();
		}else{
			$(".menuType").show();
		}
	})
    $("#code").change(function () {
        $.ajax({
            url:'/menu/checkCode',
            type:'post',
            datetype:'json',
            data:{"id":$("#id").val(),"code":$("#code").val()},
            success:function (data) {
                if(data.success){
                    check=true;
                    xl.alert("菜单编码已存在,请重新输入!");
                }else{
                    check=false;
                }
            }
        })
    })
});
//保存
function  submintBtn() {
    var name = $("#name").val();
    var code = $("#code").val();
    var level = $("#level").val();
    var pid = $("#pid").val();
    var path = $("#path").val();
    if (name == null || name == "") {
        xl.alert("菜单名称不能为空,请输入菜单名称!");
        return false;
    }
    if (pid !=0 && (code == null || code == "")) {
        xl.alert("菜单编码不能为空,请输入菜单编码!");
        return false;
    }else if(pid == 0 && (code != null && code != "")){
        xl.alert("请选择上级菜单!");
        return false;
    }
    if (pid !=0 && (path == null || path == "")) {
        xl.alert("资源值不能为空,请输入资源值!");
        return false;
    }else if(pid == 0 && (path != null && path != "")){
        xl.alert("请选择上级菜单!");
        return false;
    }
    if (level == null || level == "") {
        xl.alert("排序值不能为空,请输入排序值!");
        return false;
    }
    if(check){
        xl.alert("菜单编码已存在,请重新输入!");
        return false;
    }
    $.ajax({
        url:'/menu/save',
        type:'post',
        dataType:'json',
        data:$("#formId").serialize(),
        success: function (data){
            if(data.success){
                layer.msg("保存成功",{icon:6,time:1000},function () {
                    xl.close();
                    parent.onreload();
                })
            }else{
                xl.msg("保存失败!");
            }
        }
    })
}
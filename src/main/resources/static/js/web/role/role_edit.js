
//登录
function submitForm() {
    var roleCode = $("#roleCode").val();
    var roleName = $("#roleName").val();
    if (roleName == null || roleName == '') {
        xl.alert("角色名称不能为空,请输入!")
        return false;
    }
    if (roleCode == null || roleCode == '') {
        xl.alert("角色编码不能为空,请输入!")
        return false;
    }
    $.ajax({
        type: 'POST',//方法类型
        dataType: 'json',//预期服务器返回的数据类型
        url: '/role/save',//url
        data: $("#formId").serialize(),
        success: function (data) {
            if (data.success) {
                layer.msg("保存成功", {icon: 6, time: 1000}, function () {
                    parent.onreload();
                })
            } else {
                xl.alert(data.msg);
            }
        }
    })
}
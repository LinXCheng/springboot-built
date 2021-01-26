var check = false;
$(function () {
    $(document).on("change", "#type", function () {
        if ($(this).val() == '11') {
            $("#codediv").show();
        } else {
            $("#codediv").hide();
        }
    });

    //检测所属菜单和所属类型是否存在
    $("#type,#menuId").change(function () {
        var menuId = $("#menuId").val();
        var typeId = $("#type").val();
        if (menuId != null && menuId != "" && typeId != null && typeId != "" && menuId != 0 && typeId != 0) {
            $.ajax({
                url: '/resourcesPath/checkRepeat',
                type: 'post',
                datatype: 'json',
                data: {"code": $("#code").val(), "id": $("#id").val(), "typeId": typeId, "menuId": menuId},
                success: function (data) {
                    if (data.success) {
                        check = true;
                        xl.alert("已存在相同所属菜单和所属类型，请重新选择!");
                    } else {
                        check = false;
                    }
                }
            })
        }
    });
    //检测资源编码是否重复
    $(document).on("change", "#code", function () {
    	var typeId = $("#type").val();
        $.ajax({
            url: '/resourcesPath/checkRepeat',
            type: 'post',
            datatype: 'json',
            data: {"code": $("#code").val(), "id": $("#id").val(), "typeId": typeId, "menuId": ""},
            success: function (data) {
                if (data.success) {
                    check = true;
                    xl.alert("资源编码重复，请重新填写!");
                } else {
                    check = false;
                }
            }
        })
    })
});
//保存
function submintBtn() {
    var typeval = $("#type").val();
    var description = $("#description").val();
    var path = $("#path").val();
    var menuId = $("#menuId").val();
    var code = $("#code").val();
    if (description == null || description == '') {
        xl.alert("资源名称不能为空!");
        return false;
    }
    if (path == null || path == '') {
        xl.alert("资源路径不能为空!");
        return false;
    }
    if (menuId == null || menuId == '' || menuId == 0 || menuId == -1) {
        if (menuId == -1) {
            xl.alert("所属菜单不能为一级菜单，请选择资源对应的二级菜单!");
        } else {
            xl.alert("所属菜单不能为空!");
        }
        return false;
    }
    if (typeval == null || typeval == '' || typeval == 0) {
        xl.alert("所属类型不能为空!");
        return false;
    }
    if (typeval == '11' && check == true) {
        xl.alert("资源编码重复，请重新填写!");
        return false;
    }
    if (typeval != '11' && check == true) {
        xl.alert("已存在相同所属菜单和所属类型，请重新选择!");
        return false;
    }
    if (typeval == '11' && (code == null || code == "")) {
        xl.alert("资源编码不能为空!");
        return false;
    }

    $.ajax({
        url: '/resourcesPath/save',
        type: 'post',
        dataType: 'json',
        data: $("#formId").serialize(),
        success: function (data) {
            if (data.success) {
                layer.msg("保存成功", {icon: 6, time: 1000}, function () {
                    xl.close();
                    parent.onreload();
                })
            } else {
                xl.alert(data.msg);
            }
        }
    })
}
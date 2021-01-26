$(document).ready(function() {
    $('#example tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected') ) {
            $(this).removeClass('selected');
            var spinner = xl.spinOpen(foo);
            $(".rr").attr("checked",false);
            $(".ss").attr("checked",false);
            xl.spinClose(spinner);
        }else{
            $('#example').find('tr').removeClass('selected');
            $(this).addClass('selected');
            var roleId="";
            $("#example").find("tbody tr.selected").each(function(){
                roleId = $(this).attr("id");
            })
            var spinner = xl.spinOpen(foo);
            $.ajax({
                url:'/permission/showPermission',
                data:{'roleId':roleId},
                type:'post',
                dataType:'Json',
                async:false,
                success:function(json){
                    $(".rr").attr("checked", false);
                    $(".ss").attr("checked", false);
                    if (json.success) {
                        $.each(json.result, function (index, array) {
                            $('.rr').each(function (index, ele) {
                                if ($(this).attr("data-resources") == array) {
                                    $(this).attr("checked", true);
                                    $(this).prop("checked", true);
                                }
                            });
                            $('.ss').each(function (index, ele) {
                                if ($(this).attr("data-resources") == array) {
                                    $(this).attr("checked", true);
                                    $(this).prop("checked", true);
                                }
                            });
                        })
                    } else {
                        xl.alert(json.msg);
                    }
                }
            });
            xl.spinClose(spinner);
        }
    });

    $('.editstate').on('click', function (event) {
        event.stopPropagation();
        var id = $(this).data("id");
        var obthis = $(this);
        $.ajax({
            url:'/role/roleDisable',
            data:{'id':id},
            type:'post',
            dataType:'json',
            async:false,
            success:function(json){
                if(json.success){
                    if(json.result==0){//启用
                        obthis.text("禁用");
                        obthis.parents("td").prev().find("div").text("启用");
                        obthis.toggleClass("btn-info btn-danger");
                    }else if(json.result==1){//禁用
                        obthis.text("启用");
                        obthis.parents("td").prev().find("div").text("禁用");
                        obthis.toggleClass("btn-info btn-danger");
                    }
                }else{
                    xl.alert(json.msg)
                }
            }
        })
    });

    $(".partname").click(function(){
        if($(this).hasClass("chose")){
            $(this).removeClass("chose");
            $(this).parent().find("input").attr("checked",false);
            $(this).parent().find("input").prop("checked",false);
        }else{
            $(this).addClass("chose");
            $(this).parent().find("input").attr("checked",true);
            $(this).parent().find("input").prop("checked",true);
        }
    });

});

function savePermission(){
    var roleId = "";
/*    if (($('.rr:checked').length < 1) && ($('.ss:checked').length < 1)) {
        xl.alert('请勾选需要的权限');
        return;
    } else*/ if($("#example").find("tbody tr.selected").length<=0){
        xl.alert('请选择需要授权的角色');
        return;
    } else {
        $("#example").find("tbody tr.selected").each(function(){
            roleId = $(this).attr("id");
        })
        xl.confirm('是否确认提交？', function() {
            var resources = [];
            $('.rr:checked').each(function(index,ele){
                resources.push($(this).attr("data-resources"));
            });
            $('.ss:checked').each(function(index,ele){
                resources.push($(this).attr("data-resources"));
            });
            $.post("/permission/save",{"roleId":roleId,"resourcesId":resources.join(",")},function(resp,state){
                if(resp.success){
                    layer.msg("保存成功!", {icon: 6, time: 1000}, function () {
                        location.reload();
                    })
                    $(".rr").attr("checked",false);
                    $(".rr").prop("checked",false);
                    $(".ss").attr("checked",false);
                    $(".ss").prop("checked",false);
                }else{
                    xl.alert(resp.msg);
                }
            });
        });
    }
}

function onreload() {
    location.reload();
}

function addBtn() {
    var ob={
        title:'<label>新增角色</label>',
        width:800,
        height:400,
        url:'/role/roleEdit'
    }
    xl.open(ob);
}

function updateBtn() {
    var $this = $("#example").find("tbody tr.selected");
    if($this.length<=0){
        xl.alert('请选中其中一个角色!');
        return;
    }else{
        var ob={
            title:'<label>编辑角色</label>',
            width:800,
            height:400,
            url:'/role/roleEdit?id='+$this.attr("id")
        }
        xl.open(ob);
    }
}
//删除按钮
function delRole() {
    if ($("#example").find("tbody tr.selected").length <= 0) {
        xl.alert('请选中一项');
        return;
    } else {
        $("#example").find("tbody tr.selected").each(function () {
            var id = $(this).attr("id");
            xl.confirm('是否确认删除？', function () {
                $.post("/role/delete", {id: id}, function (resp, state) {
                    if (resp.success) {
                        xl.alert('角色删除成功!');
                        location.reload();
                    } else {
                        xl.alert(resp.msg);
                    }
                });
            });
        })
    }
}
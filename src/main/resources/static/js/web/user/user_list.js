$(function () {
    var $this;
    //用户是否有效开关
    $('tr').mouseenter(function () {
        $this = $(this);
    });
    $('.checkbox').bootstrapSwitch({
        onText: "有效",
        offText: "无效",
        onColor: "success",
        offColor: "info",
        size: "small",
        onSwitchChange: function (event, state) {
            var id = $this.find(".onlyCheck").val();
            layer.confirm('是否确认修改用户状态?', {
                btn: ['确认', '取消'] //按钮
            }, function () {
                $.get("/user/updateStatus", {"id": id, "state": state}, function (data) {
                    if (data.success) {
                        layer.msg("修改成功!", {icon: 6, time: 1000})
                    } else {
                        if (state) {
                            $this.find(".checkbox").bootstrapSwitch('toggleState', true);
                            $this.find(".checkbox").bootstrapSwitch('state', false);
                        } else {
                            $this.find(".checkbox").bootstrapSwitch('toggleState', true);
                            $this.find(".checkbox").bootstrapSwitch('state', true);
                        }
                        xl.alert(data.msg);
                    }
                });
            }, function () {
                if (state) {
                    $this.find(".checkbox").bootstrapSwitch('toggleState', true);
                    $this.find(".checkbox").bootstrapSwitch('state', false);
                } else {
                    $this.find(".checkbox").bootstrapSwitch('toggleState', true);
                    $this.find(".checkbox").bootstrapSwitch('state', true);
                }
            });
        }
    });

    //选择用户进行修改
    $(".update-btn").click(function () {
        //获取选中的复选框
        var length = $('.onlyCheck:checked').length;
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else if (length > 1) {
            xl.alert("请选择其中一项编辑!");
        } else {
            edit($('.onlyCheck:checked').val());
        }
    });

    //删除操作
    $(".del-btn").click(function () {
        var length = $('.onlyCheck:checked').length
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            xl.confirm('是否确认删除?', function () {
                $.get("/user/delete", {id: $('.onlyCheck:checked').val()}, function (data) {
                    if (data.success) {
                        layer.msg("删除成功!",
                            {icon: 6, time: 1000},
                            function () {
                                onreload();
                            })
                    } else {
                        xl.alert(data.msg);
                    }
                })
            })
        }
    })
});

//修改用户
function edit(id) {
    var ob = {
        title: "修改用户",
        width: "800",
        height: "480",
        url: "/user/editUserView?id=" + id
    };
    xl.open(ob);
}

//修改用户
function insert(id) {
    var ob = {
        title: "新增用户",
        width: "800",
        height: "480",
        url: "/user/editUserView"
    };
    xl.open(ob);
}

function onreload() {
    location.reload();
}

//根据用户名搜索用户
function searchBtn() {
    location.href = "/user/userList?keyword=" + $("#keyword").val();
}
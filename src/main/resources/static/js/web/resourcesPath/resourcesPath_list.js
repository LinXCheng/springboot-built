$(function () {
    //新增
    $(".insert-btn").click(function () {
        var ob = {
            title: "<label>新增资源</label>",
            width: 1000,
            height: 400,
            url: "/resourcesPath/editResources"
        };
        xl.open(ob);
    });
    //选中编辑
    $(".update-btn").click(function () {
        var length = $('.cb:checked').length
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            updataBtn($('.cb:checked').val());
        }
    })

    //删除
    $(".del-btn").click(function () {
        var length = $('.cb:checked').length
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        }  else {
            deleteBtn($('.cb:checked').val());
        }
    })
});
function onreload() {
    location.reload();
}
//删除
function deleteBtn(id){
    xl.confirm('是否确认删除?', function() {
        $.get("/resourcesPath/delete", {id: id}, function(data) {
            if (data.success) {
                layer.msg("删除成功!",
                    {icon:6,time:1000},
                    function(){
                        onreload();
                    })
            } else {
                xl.alert(data.msg);
            }
        });
    })
}
//修改
function updataBtn(id){
    var ob = {
        title: "<label>修改资源</label>",
        width: 1000,
        height: 400,
        url: "/resourcesPath/editResources?id="+id
    };
    xl.open(ob);
}
//搜索
function searchBtn() {
    location.href="/resourcesPath/resourcesList?keyword="+$("#keyword").val();
}
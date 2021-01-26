<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假记录</title>
</head>
<#include "${ctx!}/common/common_css.ftl" />
<style>
    input[readonly]{
        background-color:#ffffff !important;
    }
    .btn-set {
        float: left;
        margin: 10px 0px;
        width: 46%;
        height: 34px;
    }
    .dateInterval {
        width: 37%;
        float: left;
        height: 34px !important;
        margin-right: 16px;
        position: relative;
        left: 12%;
    }
    .name-search {
        width: 40% !important;
        float: left !important;
        margin-left: 10.6% !important;
    }
    .laydate-inp {
        width: 45%;
        float: left;
        height: 100%;
        margin-left: 45.6%;
    }
</style>
<body>
<div>
    <div class="ibox-content" style="margin-top: 0%;width: 94%;margin-left: 3%;">
        <div class="btn-div">
        	<div class="btn-set ">
                <@shiro.hasRole name="employees">
                    <a class="btn insert-btn" onclick="insert();"><i class="fa fa-plus"></i> 新增</a>
                    <a class="btn update-btn" onclick="update();"><i class="fa fa-pencil"></i> 修改</a>
                    <a class="btn del-btn" onclick="deleteBtn();"><i class="fa fa-trash-o"></i> 删除</a>
                </@shiro.hasRole>
                <@shiro.hasAnyRoles name="admin,administrator">
                    <a class="btn audit-btn" onclick="audit();"><i class="fa fa-calendar-check-o"></i> 通过</a>
                    <a class="btn yellow-btn undo-btn" onclick="undo();"><i class="fa fa-calendar-times-o"></i> 撤销</a>
                    <a class="btn red-btn rejected-btn" onclick="rejected();"><i class="fa fa-ban"></i> 驳回</a>
                </@shiro.hasAnyRoles>
            </div>
            <div class="search-div" style="width: 50%;">
                <@shiro.hasAnyRoles name="admin,administrator">
                    <input type="text" id="dateInterval" name="dateInterval" class="input-sm form-control laydate dateInterval" readonly  data-format="yyyy-MM-dd" range="~" placeholder="请选择查询时间" value="${(dateInterval)!}" >
                    <input type="text" id="keyword" name="keyword" placeholder="请输入姓名" value="${(keyword)!}" class="input-sm form-control name-search">
                </@shiro.hasAnyRoles>
                <@shiro.hasRole name="employees">
                    <input type="text" id="dateInterval" name="dateInterval" class="input-sm form-control laydate laydate-inp" readonly  data-format="yyyy-MM-dd" range="~" placeholder="请选择查询时间" value="${(dateInterval)!}" >
                </@shiro.hasRole>
                <span class="input-group-btn" id="search-btn">
                	<button type="button" class="btn btn-sm btn-primary " onclick="searchBtn();"> 搜索</button>
                </span>
            </div>
        </div>
        <@shiro.hasRole name="employees">
            <div id="left_dict" style="overflow-y: auto;">
                <table id="example" class="table table-striped table-bordered table-hover dataTables-example dataTable"
                       cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="tabtd" width="4%"></th>
                        <th class="tabtd">出差时间</th>
                        <th class="tabtd">出差天数</th>
                        <th class="tabtd">目的地</th>
                        <th class="tabtd">申请日期</th>
                        <th class="tabtd">审批日期</th>
                        <th class="tabtd">审核状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if businessTripDOS?exists>
                    <#if businessTripDOS.list?exists>
                        <#list businessTripDOS.list as businessTripDOS>
                        <tr>
                            <td class="tabtd"><input class="onlyCheck"type="checkbox" value="${(businessTripDOS.id)!}"></td>
                            <td class="tabtd">${(businessTripDOS.startTime?string('yyyy-MM-dd'))!} ~ ${(businessTripDOS.endTime?string('yyyy-MM-dd'))!}</td>
                            <td class="tabtd">${(businessTripDOS.numberDay)!}</td>
                            <td class="tabtd">${(businessTripDOS.province)!}-${(businessTripDOS.city)!}-${(businessTripDOS.district)!}</td>
                            <td class="tabtd">${(businessTripDOS.applyTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                            <td class="tabtd">${(businessTripDOS.auditTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                            <td class="tabtd state" date-state="${(businessTripDOS.state)!}"><#if businessTripDOS.state == 0>审核中<#elseif businessTripDOS.state == 1>审核通过<#else>驳回申请</#if></td>
                        </tr>
                        </#list>
                    </#if>
                    </#if>
                    </tbody>
                </table>
            </div>
        </@shiro.hasRole>
        <@shiro.hasAnyRoles name="admin,administrator">
            <div id="left_dict" style="overflow-y: auto;">
                <table id="example" class="table table-striped table-bordered table-hover dataTables-example dataTable"
                       cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="tabtd" width="4%"></th>
                        <th class="tabtd">出差员工</th>
                        <th class="tabtd">出差时间</th>
                        <th class="tabtd">目的地</th>
                        <th class="tabtd">出差天数</th>
                        <th class="tabtd">出差原因</th>
                        <th class="tabtd">申请日期</th>
                        <th class="tabtd">审批日期</th>
                        <th class="tabtd">审核状态</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#if businessTripDOS?exists>
                            <#list businessTripDOS.list as businessTripDOS>
                                <tr>
                                    <td class="tabtd"><input class="onlyCheck"type="checkbox" value="${(businessTripDOS.id)!}"></td>
                                    <td class="tabtd">${(businessTripDOS.nickName)!}</td>
                                    <td class="tabtd time">${(businessTripDOS.startTime?string('yyyy-MM-dd'))!} ~ ${(businessTripDOS.endTime?string('yyyy-MM-dd'))!}</td>
                                    <td class="tabtd">${(businessTripDOS.province)!}-${(businessTripDOS.city)!}-${(businessTripDOS.district)!}</td>
                                    <td class="tabtd">${(businessTripDOS.numberDay)!}</td>
                                    <td>${(businessTripDOS.applyReason)!}</td>
                                    <td class="tabtd">${(businessTripDOS.applyTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                                    <td class="tabtd">${(businessTripDOS.auditTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                                    <td class="tabtd state" date-state="${(businessTripDOS.state)!}"><#if businessTripDOS.state == 0>审核中<#elseif businessTripDOS.state == 1>审核通过<#else>驳回申请</#if></td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </div>
        </@shiro.hasAnyRoles>
        <#if businessTripDOS?exists>
            <#if businessTripDOS??>
                <@macro.paging pagingList=businessTripDOS url="/attendance/businessTrip?dateInterval=${(dateInterval)!}&&keyword = ${(keyword)!}"/>
            </#if>
        </#if>
    </div>
</div>
<#include "${ctx!}/common/common_js.ftl"/>
<script type="text/javascript">
    function insert() {
        var ob = {
            title:'出差事由',
            width:900,
            height:500,
            url:'/attendance/businessTripEdit'
    }
        xl.open(ob);
    }
    function update() {
        var length = $('.onlyCheck:checked').length;
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            var ob = {
                title:'出差事由',
                width:900,
                height:500,
                url:'/attendance/businessTripEdit?id='+$('.onlyCheck:checked').val()
            }
            xl.open(ob);
        }
    }

    //删除为通过审核的请假记录
    function deleteBtn() {
        var length = $('.onlyCheck:checked').length;
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            xl.confirm('是否确认删除?', function () {
                var state = $('.onlyCheck:checked').parents("tr").find("td.state").attr('date-state');
                if (1 != state) {
                    var id = $('.onlyCheck:checked').val();
                    $.ajax({
                        url:'/attendance/deleteBusinessTrip',
                        type:'post',
                        dateType:'json',
                        data: {"id": id},
                        success:function (data) {
                            if(data.success){
                                layer.msg("修改成功!",{icon:6,time:800},function () {
                                    onreload();
                                })
                            }else {
                                xl.alert("删除失败!");
                            }
                        }
                    })
                } else {
                    xl.alert("该条记录已经通过审批，无法删除!");
                }
            })
        }
    }
    function onreload() {
        location.reload();
    }
    function searchBtn() {
        if(null != $("#keyword").val() && "" != $("#keyword").val() && undefined != $("#keyword").val()){
            location.href = "/attendance/businessTrip?dateInterval="+$("#dateInterval").val()+"&&keyword="+$("#keyword").val()+"";
        } else {
            location.href = "/attendance/businessTrip?dateInterval=" + $("#dateInterval").val();
        }
    }
    // 审批通过通过
    function audit() {
        var length = $('.onlyCheck:checked').length;
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            var state = $('.onlyCheck:checked').parents("tr").find("td.state").attr('date-state');
            if (1 != state && 2 != state) {
                var id = $('.onlyCheck:checked').val();
                updateState(id, 1);
            } else {
                xl.alert("该条记录已经审批，无需重复审批!");
            }
        }
    }
    // 撤销状态恢复成审核中
    function undo() {
        var length = $('.onlyCheck:checked').length;
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            var state = $('.onlyCheck:checked').parents("tr").find("td.state").attr('date-state');
            if( 0 != state){
                var id = $('.onlyCheck:checked').val();
                updateState(id, 2);
            } else {
                xl.alert("该条记录无需撤销!");
            }
        }
    }
    // 驳回申请
    function rejected() {
        var length = $('.onlyCheck:checked').length;
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            var state = $('.onlyCheck:checked').parents("tr").find("td.state").attr('date-state');
            if (0 == state) {
                var id = $('.onlyCheck:checked').val();
                updateState(id, 3);
            } else {
                xl.alert("该条记录已经驳回或者审核通过，无法驳回!");
            }
        }
    }

    function updateState(id, type) {
        var systemDate = '${(systemYM)!}';
        var thisDate = $('.onlyCheck:checked').parents("tr").find("td.time").text().substring(0,7);
        if(systemDate != thisDate){
            xl.alert("只能审核当前月份记录!");
            return false;
        }
        $.ajax({
            url:'/attendance/updateBusinessState',
            type:'post',
            dateType:'json',
            data: {"type": type, "id": id},
            success:function (data) {
                if(data.success){
                    layer.msg("修改成功!",{icon:6,time:800},function () {
                        onreload();
                    })
                }else {
                    xl.alert("修改失败!");
                }
            }
        })
    }
</script>
</body>
</html>
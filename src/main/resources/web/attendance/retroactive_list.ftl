<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>补卡记录</title>
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
                    <a class="btn" href="${(ctx)!}/attendance/retroactive"><i class="fa fa-mail-reply"></i> 返回</a>
                    <a class="btn del-btn" onclick="deleteBtn();"><i class="fa fa-trash-o"></i> 删除</a>
                </@shiro.hasRole>
                <@shiro.hasAnyRoles name="admin,administrator">
                    <a class="btn audit-btn" onclick="audit();"><i class="fa fa-calendar-check-o"></i> 通过</a>
                    <a class="btn red-btn rejected-btn" onclick="rejected();"><i class="fa fa-ban"></i> 驳回</a>
                </@shiro.hasAnyRoles>
            </div>
            <div class="search-div" style="width: 50%;">
                <@shiro.hasAnyRoles name="admin,administrator">
                    <input type="text" id="YearMonth" name="YearMonth" class="input-sm form-control laydate dateInterval" readonly  data-format="yyyy-MM" data-type="month" placeholder="请选择查询时间" value="${(YearMonth)!}" >
                    <input type="text" id="keyword" name="keyword" placeholder="请输入姓名" value="${(keyword)!}" class="input-sm form-control name-search">
                </@shiro.hasAnyRoles>
                <@shiro.hasRole name="employees">
                    <input type="text" id="YearMonth" name="YearMonth" class="input-sm form-control laydate laydate-inp" readonly  data-format="yyyy-MM" data-type="month" placeholder="请选择查询时间" value="${(YearMonth)!}" >
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
                        <th class="tabtd">补卡时间</th>
                        <th class="tabtd">申请日期</th>
                        <th class="tabtd">审批日期</th>
                        <th class="tabtd">审核状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if retroactiveDOS?exists>
                    <#if retroactiveDOS.list?exists>
                        <#list retroactiveDOS.list as retroactiveDOS>
                        <tr>
                            <td class="tabtd"><input class="onlyCheck"type="checkbox" value="${(retroactiveDOS.id)!}"></td>
                            <td class="tabtd">${(retroactiveDOS.time?string('yyyy-MM-dd'))!}</td>
                            <td class="tabtd">${(retroactiveDOS.applyTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                            <td class="tabtd">${(retroactiveDOS.auditTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                            <td class="tabtd"><#if retroactiveDOS.state == 0>审核中<#elseif retroactiveDOS.state == 1>审核通过<#else>驳回申请</#if></td>
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
                        <th class="tabtd" width="4%"><input class="allCb"type="checkbox"/></th>
                        <th class="tabtd">员工</th>
                        <th class="tabtd">补卡时间</th>
                        <th class="tabtd">申请日期</th>
                        <th class="tabtd">审批日期</th>
                        <th class="tabtd">审核状态</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#if retroactiveDOS?exists>
                            <#list retroactiveDOS.list as retroactiveDOS>
                                <tr>
                                    <td class="tabtd"><input class="cb"type="checkbox" value="${(retroactiveDOS.id)!}"></td>
                                    <td class="tabtd">${(retroactiveDOS.nickName)!}</td>
                                    <td class="tabtd time">${(retroactiveDOS.time?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                                    <td class="tabtd">${(retroactiveDOS.applyTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                                    <td class="tabtd">${(retroactiveDOS.auditTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                                    <td class="tabtd state" date-state="${(retroactiveDOS.state)!}"><#if retroactiveDOS.state == 0>审核中<#elseif retroactiveDOS.state == 1>审核通过<#else>驳回申请</#if></td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </div>
        </@shiro.hasAnyRoles>
        <#if retroactiveDOS?exists>
            <#if retroactiveDOS??>
                <@macro.paging pagingList=retroactiveDOS url="/attendance/retroactiveList?YearMonth=${(YearMonth)!}&&keyword = ${(keyword)!}"/>
            </#if>
        </#if>
    </div>
</div>
<#include "${ctx!}/common/common_js.ftl"/>
<script type="text/javascript">
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
                        url: '/attendance/deleteRetroactive',
                        type: 'post',
                        dateType: 'json',
                        data: {"id": id},
                        success: function (data) {
                            if (data.success) {
                                layer.msg("修改成功!", {icon: 6, time: 800}, function () {
                                    onreload();
                                })
                            } else {
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
            location.href = "/attendance/retroactiveList?YearMonth="+$("#YearMonth").val()+"&&keyword="+$("#keyword").val()+"";
        } else {
            location.href = "/attendance/retroactiveList?YearMonth=" + $("#YearMonth").val();
        }
    }
    // 审批通过通过
    function audit() {
        var length = $('.cb:checked').length;
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            var systemDate = '${(systemYM)!}';
            var thisDate = $(".cb:checked").eq(0).parents("tr").find("td.time").text().substring(0,7);
            if(systemDate != thisDate){
                xl.alert("只能选择本月的记录进行审核！");
                return false;
            } else {
                var idStr = [];
                var fg = true;
                $(".cb:checked").each(function () {
                    var $this = $(this);
                    var state = $this.parents("tr").find("td.state").attr('date-state');
                    if (1 != state && 2 != state) {
                        idStr.push($this.val())
                    } else {
                        xl.alert("您选择的记录存在已审批记录，请核对后再次审批！");
                        fg = false;
                        return false;
                    }
                })
                if (fg) {
                    updateState(idStr, 1);
                }
            }
        }
    }
    // 驳回申请
    function rejected() {
        var length = $('.cb:checked').length;
        if (length < 1) {
            xl.alert('未选中任何项!');
            return false;
        } else {
            var idStr = [];
            var fg = true;
            $(".cb:checked").each(function () {
                var $this = $(this);
                var state = $this.parents("tr").find("td.state").attr('date-state');
                if (1 != state && 2 != state) {
                    idStr.push($this.val())
                } else {
                    xl.alert("您选择的记录存在已审批记录，请核对后再次审批！");
                    fg = false;
                    return false;
                }
            })
            if (fg) {
                updateState(idStr, 2);
            }
        }
    }

    function updateState(idStr, type) {
        $.ajax({
            url:'/attendance/updateRetroactiveState',
            type:'post',
            dateType:'json',
            data: {"type": type, "idStr": idStr.join(",")},
            success:function (data) {
                if(data.success){
                    layer.msg("审核成功!",{icon:6,time:800},function () {
                        onreload();
                    })
                }else {
                    xl.alert("审核失败!");
                }
            }
        })
    }
</script>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>用户列表</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap-switch.min.css">
</head>
    <#include "${ctx!}/common/common_css.ftl" />
<style>
.bootstrap-switch.bootstrap-switch-small {
	min-width: 60px;
}
.ibox-content {
	margin-top: 0%;
	width: 94%;
	margin-left: 3%;
}
</style>
<body>
<div>
    <div class="ibox-content">
        <div class="btn-div">
        	<div class="btn-set ">
	            <@shiro.hasPermission name="user:update">
                    <a class="btn insert-btn" onclick="insert();"><i class="fa fa-plus"></i> 新增</a>
	                <a class="btn update-btn"><i class="fa fa-pencil"></i> 修改</a>
	            </@shiro.hasPermission>
                <@shiro.hasRole name="admin">
                    <a class="btn del-btn"><i class="fa fa-trash-o"></i> 删除</a>
                </@shiro.hasRole>
            </div>
            <div class="search-div">
				<input type="text" id="keyword" name="keyword" placeholder="请输入用户名称" value="${(keyword)!}" class="input-sm form-control">
                <span class="input-group-btn" id="search-btn">
                	<button type="button" class="btn btn-sm btn-primary" onclick="searchBtn();"> 搜索</button>
                </span>
            </div>
        </div>
        <div id="left_dict" style="overflow-y: auto;">
            <table id="example" class="table table-striped table-bordered table-hover dataTables-example dataTable" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th class="tabtd" width="4%">
                        <input class="all" name="" type="checkbox" hidden>
                    </th>
                    <th class="tabtd">用户名</th>
                    <th class="tabtd">用户昵称</th>
                    <th class="tabtd">联系方式</th>
                    <th class="tabtd">地址</th>
                    <th class="tabtd">用户角色</th>
                    <th class="tabtd">用户创建类型</th>
                    <th class="tabtd">创建时间</th>
                    <th class="tabtd">最近修改时间</th>
                    <@shiro.hasPermission name="user:other">
                        <th class="tabtd">是否有效</th>
                    </@shiro.hasPermission>
                <tbody>
            <#list userList.list as userList>
            <tr>
                <td class="tabtd">
                	<input class="onlyCheck" type="checkbox" onclick="onlyCheck(this);" value="${(userList.id)!}">
                </td>
                <td class="tabtd">${(userList.username)!}</td>
                <td class="tabtd">${(userList.nickname)!}</td>
                <td class="tabtd">${(userList.tel)!}</td>
                <td class="tabtd">${(userList.address)!}</td>
                <td class="tabtd">${(userList.roleName)!}</td>
                <td class="tabtd"><#if userList?exists><#if userList.type==0>管理员创建<#else>用户注册</#if></#if></td>
                <td class="tabtd">${(userList.createTime?datetime)!}</td>
                <td class="tabtd">${(userList.updateTime?datetime)!}</td>
                <@shiro.hasPermission name="user:other">
                    <td class="tabtd">
                        <div class="bootstrap-switch bootstrap-switch-small">
                            <#if userList.deleteStatus == 0>
                            <input type="checkbox" class="checkbox" checked/>
                            <#else >
                                <input type="checkbox" class="checkbox"/>
                            </#if>
                        </div>
                    </td>
                </@shiro.hasPermission>
            </tr>
            </#list>
                </tbody>
            </table>
        </div>
        <#if userList?exists>
			<#if userList??>
        		<@macro.paging pagingList=userList url="/user/userList?keyword=${(keyword)!}"/>
        	</#if>
    	</#if>
    </div>
</div>
<#include "${ctx!}/common/common_js.ftl" />
<script src="${ctx!}/js/bootstrap-switch.min.js"></script>
<script src="${ctx!}/js/web/user/user_list.js"></script>
</body>
</html>
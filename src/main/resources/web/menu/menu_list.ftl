<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
</head>
<#include "${ctx!}/common/common_css.ftl" />
<body>
<div>
    <div class="ibox-content" style="margin-top: 0%;width: 94%;margin-left: 3%;">
        <div class="btn-div">
        	<div class="btn-set ">
                <@shiro.hasRole name="admin">
	            <a class="btn insert-btn"><i class="fa fa-plus"></i> 新增</a>
	            <a class="btn update-btn"><i class="fa fa-pencil"></i> 修改</a>
                <a class="btn del-btn"><i class="fa fa-trash-o"></i> 删除</a>
                </@shiro.hasRole>
            </div>
            <div class="search-div">
				<input type="text" id="keyword" name="keyword" placeholder="请输入菜单名称" value="${(keyword)!}" class="input-sm form-control">
                <span class="input-group-btn" id="search-btn">
                	<button type="button" class="btn btn-sm btn-primary" onclick="searchBtn();"> 搜索</button>
                </span>
            </div>
        </div>
        <!-- search end -->
        <div id="left_dict" style="overflow-y: auto;">
            <table id="example" class="table table-striped table-bordered table-hover dataTables-example dataTable"
                   cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th class="tabtd" width="4%"></th>
                    <th class="tabtd">菜单名称</th>
                    <th class="tabtd">资源值</th>
                    <th class="tabtd">上级菜单</th>
                    <th class="tabtd">排序值</th>
                    <@shiro.hasRole name="admin">
                        <th class="tabtd" width="120px">操作</th>
                    </@shiro.hasRole>
                </tr>
                </thead>
                <tbody>
                <#list menulist.list as menulist>
                <tr>
                    <td class="tabtd"><input class="cb onlyCheck" onclick="onlyCheck(this);"name="" type="checkbox" value="${(menulist.id)!}"></td>
                    <td>${(menulist.name)!}</td>
                    <td>${(menulist.path)!}</td>
                    <#if menulist.pid!=0>
                        <td class="tabtd">${(menulist.parentName)!}</td>
                    <#else>
                        <td></td>
                    </#if>
                    <td class="tabtd">${(menulist.level)!}</td>
                    <@shiro.hasRole name="admin">
                        <td class="tabtd">
                            <button type="button" class="btn btn-xs yellow-btn"  onclick="updataBtn(${(menulist.id)!});">编辑</button>
                            <button type="button" class="btn btn-xs red-btn"  onclick="deleteBtn(${(menulist.id)!});">删除</button>
                        </td>
                    </@shiro.hasRole>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
     	<#if menulist?exists>
			<#if menulist??>
        		<@macro.paging pagingList=menulist url="/menu/menuList?keyword=${(keyword)!}"/>
        	</#if>
    	</#if>
    </div>
</div>
<#include "${ctx!}/common/common_js.ftl"/>
<script src="${ctx!}/js/web/menu/menu_list.js"></script>
</body>
</html>
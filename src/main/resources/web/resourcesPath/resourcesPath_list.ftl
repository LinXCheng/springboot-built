<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统资源</title>
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
				<input type="text" id="keyword" name="keyword" placeholder="请输入资源名称" value="${(keyword)!}" class="input-sm form-control">
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
                    <th class="tabtd">资源名称</th>
                    <th class="tabtd" style="width:40%;">资源值</th>
                    <th class="tabtd">所属菜单</th>
                    <th class="tabtd">所属类型</th>
                    <@shiro.hasRole name="admin">
                        <th class="tabtd" width="120px">操作</th>
                    </@shiro.hasRole>
                </tr>
                </thead>
                <tbody>
                <#if resources.list?exists>
                    <#if resources.list??>
                        <#list resources.list as resources>
                            <tr>
                                <td class="tabtd">
                                    <input class="cb onlyCheck" onclick="onlyCheck(this);" type="checkbox" value="${(resources.id)!}">
                                </td>
                                <td>${(resources.description)!}</td>
                                <td>${(resources.path)!}</td>
                                <td class="tabtd">${(resources.menuName)!}</td>
                                <td class="tabtd">${(resources.typeName)!}</td>
                                <@shiro.hasRole name="admin">
                                    <td  style="text-align: center;">
                                        <button type="button" class="btn btn-xs yellow-btn" data-relId="" onclick="updataBtn(${(resources.id)!});">编辑</button>
                                        <button type="button" class="btn btn-xs red-btn" data-relId="" onclick="deleteBtn(${(resources.id)!});">删除</button>
                                    </td>
                                </@shiro.hasRole>
                            </tr>
                        </#list>
                    </#if>
                </#if>
                </tbody>
            </table>
        </div>
        <#if resources?exists>
			<#if resources.list??>
        		<@macro.paging pagingList=resources url="/resourcesPath/resourcesList?keyword=${(keyword)!}"/>
        	</#if>
    	</#if>
    </div>
</div>
<#include "${ctx!}/common/common_js.ftl" />
<script src="${ctx!}/js/web/resourcesPath/resourcesPath_list.js"></script>
</body>
</html>
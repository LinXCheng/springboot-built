<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
</head>
<#include "${ctx!}/common/common_css.ftl" />
<link rel="stylesheet" href="${ctx!}/css/container.css">
<body>
<div class="_container">
    <form class="cmxform" id="formId" method="POST" action="javascript:void(0)">
        <input type="hidden" id="id" name ="id" value="${(menuRecord.id)!}">
        <div class="form-group row">
            <label for="name"  class="col-sm-2">菜单名称</label>
            <div class="col-sm-8">
                <input class="form-control" id="name" name="name" value="${(menuRecord.name)!}" size="25" placeholder="请输入菜单名称" />
            </div>
            <div class="col-sm-2">

            </div>
        </div>

        <div class="form-group row">
            <label for="pid" class="col-sm-2 ">上级菜单</label>
            <div class="col-sm-8">
                <select id="pid" name="pid" class="form-control">
                    <option value="0">------请选择上级菜单------</option>
                    <#list parentsMenu as parentsMenu>
                        <#if menuRecord?exists>
                            <#if menuRecord.pid==parentsMenu.id>
                                <option value="${(parentsMenu.id)!}" selected>${(parentsMenu.name)!}</option>
                            <#else>
                                <option value="${(parentsMenu.id)!}">${(parentsMenu.name)!}</option>
                            </#if>
                        <#else>
                            <option value="${(parentsMenu.id)!}">${(parentsMenu.name)!}</option>
                        </#if>
                    </#list>
                </select>
            </div>
            <div class="col-sm-2">

            </div>
        </div>
        <div class="form-group row">
            <label for="level" class="col-sm-2 ">菜单编码</label>
            <div class="col-sm-8">
                <input class="form-control" id="code" name="code" value="${(menuRecord.code)!}" placeholder="请输入菜单编码">
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="form-group row">
            <label for="level" class="col-sm-2 ">资源值</label>
            <div class="col-sm-8">
                <input class="form-control" id="path" name="path" value="${(menuRecord.path)!}" placeholder="请输入资源值">
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="form-group row">
            <label for="level" class="col-sm-2 ">排序值</label>
            <div class="col-sm-8">
                <input class="form-control" id="level" name="level" value="${(menuRecord.level)!}" placeholder="请输入排序值">
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="form-group row menuType" <#if menuRecord?exists><#if menuRecord.pid!=0>style="display:none;"</#if></#if>>
            <label for="level" class="col-sm-2 ">菜单类型</label>
            <div class="col-sm-8">
				<span style="float:left;margin:0 10px;">
					<input type="radio" name="menuType" <#if menuRecord?exists><#if menuRecord.menuType==0>checked</#if><#else>checked</#if> value="0"> 后台管理
				</span> 
		         <span> 
		            <input type="radio" name="menuType" <#if menuRecord?exists><#if menuRecord.menuType==1>checked</#if></#if> value="1"> 前台管理
		         </span>
		         <span style="margin-left:10px;"> 
		            <input type="radio" name="menuType" <#if menuRecord?exists><#if menuRecord.menuType==2>checked</#if></#if> value="2"> 其他管理
		         </span> 		            
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="form-group btn-center">
            <span class="my-btn">
                <input class="btn btn-primary submit-btn" type="button" value="保存" onclick="submintBtn();"/>
                <input class="btn btn-danger" type="reset" value="清空">
            </span>
        </div>
    </form>
</div>
<#include "${ctx!}/common/common_js.ftl" />
<script src="${ctx!}/js/web/menu/menu_edit.js"></script>
</body>
</html>
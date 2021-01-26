<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<#include "${ctx!}/common/common_css.ftl" />
<link rel="stylesheet" href="${ctx!}/css/container.css">
<body>
<div class="_container">
    <form class="cmxform" id="formId" method="get" action="javascript:void(0)">
        <input id="id" type="hidden" name="id" value="${(resources.id)!}">
        <div class="form-group row">
            <label for="name" class="col-sm-2 ">资源名称</label>
            <div class="col-sm-8">
                <input class="form-control" id="description" name="description" value="${(resources.description)!}"
                       size="25" placeholder="请输入资源名称"/>
            </div>
            <div class="col-sm-2">

            </div>
        </div>

        <div class="form-group row special">
            <label for="path" class="col-sm-2 ">资源路径</label>
            <div class="col-sm-8">
                <input id="path" name="path" value="${(resources.path)!}" class="form-control" placeholder="请输入资源路径">
            </div>
            <div class="col-sm-2">
            </div>
        </div>

        <div class="form-group row">
            <label for="menuId" class="col-sm-2 ">所属菜单</label>
            <div class="col-sm-8">
                <select  id="menuId" name="menuId" class="form-control">
                    <option value="0">------请选择所属菜单------</option>
                    <#if menuList?exists>
                        <#list menuList as menuList>
                            <#if resources?exists>
                                <option value="-1">${(menuList.name)!}</option>
                                <#if (menuList.childmenu)?exists>
                                    <#if (menuList.childmenu)??>
                                        <#list menuList.childmenu as ChildMenu>
                                            <#if  resources.menuId==ChildMenu.id>
                                                <option value="${(ChildMenu.id)!}" selected>&nbsp;&nbsp;${(ChildMenu.name)!}</option>
                                            <#else>
                                                <option value="${(ChildMenu.id)!}">&nbsp;&nbsp;${(ChildMenu.name)!}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                </#if>
                            <#else>
                                    <option value="-1">${(menuList.name)!}</option>
                                    <#if (menuList.childmenu)?exists>
                                        <#if (menuList.childmenu)??>
                                            <#list menuList.childmenu as ChildMenu>
                                            <option value="${(ChildMenu.id)!}">&nbsp;&nbsp;${(ChildMenu.name)!}</option>
                                            </#list>
                                        </#if>
                                    </#if>
                            </#if>
                        </#list>
                    </#if>
                </select>
            </div>
            <div class="col-sm-2">

            </div>
        </div>

        <div class="form-group row">
            <label for="type" class="col-sm-2 ">所属类型</label>
            <div class="col-sm-8">
                <select id="type" name="type" class="form-control">
                    <option value="0">----请选择所属类型----</option>
                    <#if typeList?exists>
                        <#if typeList??>
                            <#list typeList as typeList>
                                <#if resources?exists>
                                    <#if resources.type==typeList.id>
                                        <option value="${(typeList.id)!}" selected>${(typeList.name)!}</option>
                                    <#else>
                                        <option value="${(typeList.id)!}">${(typeList.name)!}</option>
                                    </#if>
                                <#else>
                                    <option value="${(typeList.id)!}">${(typeList.name)!}</option>
                                </#if>
                            </#list>
                        </#if>
                    </#if>
                </select>
            </div>
            <div class="col-sm-2">
            </div>
        </div>

        <div id="codediv" class="form-group row special"  <#if resources?exists><#if resources.type!=11>hidden</#if><#else>hidden</#if>>
            <label for="name" class="col-sm-2 ">资源编码</label>
            <div class="col-sm-8">
                <input id="code" name="code" class="form-control" placeholder="请输入其它资源编码" <#if resources?exists><#if resources.type!=11>value=""<#else>value="${(resources.code)!}"</#if><#else>value=""</#if>>
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
<script src="${ctx!}/js/web/resourcesPath/resourcesPath_edit.js"></script>
</body>
</html>
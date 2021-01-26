<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="UTF-8">
<#include "${ctx!}/common/common_css.ftl"/>
<link rel="stylesheet" href="${ctx!}/css/container.css">
<body>
    <div class="_container">
        <form class="cmxform" id="formId" method="post" action="javascript:void(0);">
            <input type="hidden" name="id" value="${(roleModel.id)!}">
            <div class="form-group row">
                <label for="roleName" class="col-sm-2 ">角色名称</label>
                <div class="col-sm-8">
                    <input class="form-control" id="roleName" name="roleName" value="${(roleModel.roleName)!}" size="25"
                           placeholder="请输入角色名称"/>
                </div>
                <div class="col-sm-2">
                </div>
            </div>

            <div class="form-group row">
                <label for="roleCode" class="col-sm-2 ">角色编码</label>
                <div class="col-sm-8">
                    <input class="form-control" id="roleCode" name="roleCode" value="${(roleModel.roleCode)!}" size="25"
                           placeholder="请输入角色名称"/>
                </div>
                <div class="col-sm-2">
                </div>
            </div>
            <div class="form-group row">
                <label for="state" class="col-sm-2 ">状态</label>
                <div class="col-sm-8">
				<span style="float:left;margin:0 40px;">
					<input type="radio" name="state" <#if roleModel?exists><#if roleModel.state==0>checked</#if><#else>checked</#if> value="0"> 启用
				</span> 
		         <span> 
		            <input type="radio" name="state" <#if roleModel?exists><#if roleModel.state==1>checked</#if></#if> value="1"> 禁用
		         </span>                 
                </div>
                <div class="col-sm-2">
                </div>
            </div>

            <div class="form-group btn-center">
                 <span class="my-btn">
                     <input class="btn btn-primary submit-btn" type="button" value="保存" onclick="submitForm();"/>
                     <input class="btn btn-danger" type="reset" value="清空">
                </span>
            </div>
        </form>
    </div>
<#include "${ctx!}/common/common_js.ftl"/>
<script src="${ctx!}/js/web/role/role_edit.js"></script>
</body>
</html>
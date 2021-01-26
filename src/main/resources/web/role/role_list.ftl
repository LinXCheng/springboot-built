<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <meta charset="utf-8">
<head>
    <#include "${ctx!}/common/common_css.ftl"/>
    <link rel="stylesheet" href="${ctx!}/css/web/role/role_list.css">
    <style type="text/css">
        #center_dict {
            float: left;
            width: 10px;
            margin: 0px ,10px;
        }
    </style>
</head>
<body  style="overflow: hidden;">
	<div class="row col-lg-12">
		<div class="wrapper wrapper-content animated fadeInUp">
            <div class="btn-div">
            	<div class="btn-set ">
					<@shiro.hasRole name="admin">
						<a class="btn insert-btn" onclick="addBtn();"><i class="fa fa-plus"></i> 新增角色</a>
						<a class="btn update-btn" onclick="updateBtn();"><i class="fa fa-pencil"></i> 编辑角色</a>
						<a class="btn save-btn" onclick="savePermission();"><i class="fa fa-save"></i> 保存</a>
						<a class="btn del-btn" onclick="delRole();"><i class="fa fa-trash-o"></i> 删除</a>
					</@shiro.hasRole>
            	</div>
            </div>

                <div class="ibox-content" style="margin: 2% 0% 0% 2%;width: 96%;">
                    <div id="wrap">
	           			<div id="left_dict">
		                	<table id="example" class="table table-striped table-bordered table-hover dataTables-example dataTable" cellspacing="0" width="100%">
		                		<thead>
									<tr>
										<th class="cn" style="width:190px;">角色名称</th>
										<th class="cn" style="width:50px;">状态</th>
										<@shiro.hasRole name="admin">
											<th class="cn" style="width:70px;">操作</th>
										</@shiro.hasRole>
									</tr>
								</thead>
								<tbody id="rolelist">
									<#list rolelist as role>
									<tr id="${role.id!}">
										<td  class="cn"><div>${role.roleName!}</div></td>
										<#if role.state==0>
											<td  class="cn"><div>启用</div></td>
										<#elseif role.state==1>
											<td  class="cn"><div>禁用</div></td>
										</#if>
										<@shiro.hasRole name="admin">
											<#if role.state==0>
												<td  class="cn"><div><button class="btn del-btn btn-xs p310 editstate"  data-id="${role.id!}">禁用</button></div></td>
											<#elseif role.state==1>
												<td  class="cn"><div><button class="btn btn-info btn-xs p310 editstate"  data-id="${role.id!}">启用</button></div></td>
											</#if>
										</@shiro.hasRole>
									</tr>
									</#list>
								  </tbody>
		                	  </table>
	                	  </div>
	                	  <div class="center_dict" id="foo"></div>
	               		  <div class="right_dict" style="height: 435px;">
	               		  	<table id="role_detail" class="table table-striped table-bordered table-hover dataTables-example dataTable" cellspacing="0" width="100%">
		                		<thead>
									<tr>
										<th class="tn seCode">菜单名称</th>
										<th class="tn seCode">子级菜单</th>
										<#list reourcesTypeList as type>
											<th class="tn">${(type.name)!}</th>
										</#list>
										<th class="tn seCode">其他功能</th>
									</tr>
								</thead>
                                <tbody id="role">
									<#list menuList as menu>
										<#list menu.childmenu as sub>
											<tr <#if !sub_has_next> style="border-bottom: 1px solid #ddd;"</#if>>
												<#if sub_index==0>
                                                    <td class="seCode" rowspan="${(menu.size)!}" style="border-bottom: 1px solid #ddd;" data-id="${(menu.id)!}">${(menu.name)!}</td>
                                                    <td class="seCode partname">${(sub.name)!}</td>
													<#list reourcesTypeList as rtype>
														<td>
														<#list sub.typeList as child>
															<#if rtype.id==child.id>
																<input class='rr' type='checkbox' data-menuId="${(sub.id)!}" data-resources="${(child.resourcesId)!}" title="${(child.description)!}" />
															</#if>
														</#list>
                                                        </td>
													</#list>
                                                    <td class="seCode">
														<#if (sub.otherList)?? && (sub.otherList?size>0)>
														<#list sub.otherList as other>
															<input class='ss' type='checkbox' data-menuId="${(sub.id)!}" data-resources="${(other.resourcesId)!}" title="${(other.description)!}" />
														</#list>
														</#if>
                                                    </td>
												<#else>
													<td class="seCode partname" data-id="${(sub.id)!}">${(sub.name)!}</td>
													<#list reourcesTypeList as rtype>
														<td>
														<#list sub.typeList as child>
															<#if rtype.id==child.id>
																<input class='rr' type='checkbox' data-menuId="${(sub.id)!}" data-resources="${(child.resourcesId)!}" title="${(child.description)!}" />
															</#if>
														</#list>
                                                        </td>
													</#list>
                                                    <td class="seCode">
														<#if (sub.otherList)?? && (sub.otherList?size>0)>
														<#list sub.otherList as other>
															<input class='ss' type='checkbox' data-menuId="${(sub.id)!}" data-resources="${(other.resourcesId)!}" title="${(other.description)!}" />
														</#list>
														</#if>
                                                    </td>
												</#if>
                                            </tr>
										</#list>
									</#list>
                                </tbody>
		                	  </table>
	               		  </div>
	               		  <div class="clear"></div>
	               	</div>
                </div>

			</div>
		</div>
	</div>
	<!-- table end -->
<#include "${ctx!}/common/common_js.ftl"/>
<script src="${ctx!}/js/web/role/role_list.js"></script>
<script type="text/javascript">
    $(function () {
        var height = $(window).height();
        $("#left_dict").css("max-height",(height-75)+"px");
        $(".right_dict").css("max-height",(height-75)+"px");
    })
</script>
</body>
</html>
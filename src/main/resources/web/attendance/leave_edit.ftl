<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假申请</title>
</head>
<#include "${ctx!}/common/common_css.ftl" />
<link rel="stylesheet" href="${ctx!}/css/container.css">
<style>
    textarea {
        resize: none;
    }
    .instructions {
        margin-left: 21%;
        position: relative;
        top: 20px;
        font-size: 12px;
    }
    li {
        margin-left: 33px;
    }
    ._container {
        width: 44%;
        margin-left: 32%;
        margin-top: 4%;
    }
    input[readonly]{
        background-color:#ffffff !important;
    }
</style>
<body>
<ul class="error instructions">*请假须知：
    <li>1、请假开始日期和结束日期必须同年同月，如需跨年或者跨月请分别分段申请。</li>
    <li>2、系统会自动剔除日期期间中所有的节假日和双休日，自动计算请假天数。</li>
    <li>3、关乎薪酬结算请注意：申请的请假区间请不要有包含关系。</li>
</ul>
<div class="_container">
    <form class="cmxform" id="formId" method="POST" action="javascript:void(0)">
        <input type="hidden" id="id" name ="id" value="${(leave.id)!}">
        <div class="form-group row">
            <label for="name"  class="col-sm-2">开始日期</label>
            <div class="col-sm-8">
                <input id="startTime" class="form-control laydate" data-format="yyyy-MM-dd" min="${(minDate?date)!}"    name="startTimeStr" value="${(leave.startTime?string('yyyy-MM-dd'))!}"  readonly="readonly" placeholder="请选择开始时间" />
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="form-group row">
            <label for="name"  class="col-sm-2">结束日期</label>
            <div class="col-sm-8">
                <input id="endTime" class="form-control laydate" data-format="yyyy-MM-dd" min="${(minDate?date)!}"     name="endTimeStr" value="${(leave.endTime?string('yyyy-MM-dd'))!}" readonly="readonly" placeholder="请选择结束时间" />
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="form-group row">
            <label for="type" class="col-sm-2 ">请假类型</label>
            <div class="col-sm-8">
                <select  name="typeId" class="form-control">
                    <#if leaveTypeList?exists>
                        <#list leaveTypeList as leaveTypeList>
                            <#if leave?exists>
                                <#if leave.typeId == leaveTypeList.id>
                                <option value='${(leaveTypeList.id)!}' selected>${(leaveTypeList.name)!}</option>
                                <#else>
                                <option value='${(leaveTypeList.id)!}'>${(leaveTypeList.name)!}</option>
                                </#if>
                            <#else>
                            <option value='${(leaveTypeList.id)!}'>${(leaveTypeList.name)!}</option>
                            </#if>
                        </#list>
                    </#if>
                </select>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="form-group row">
            <label for="level" class="col-sm-2 ">详细原因</label>
            <div class="col-sm-8">
                <textarea  style="width: 100%;"  name="applyReason" rows="3" cols="30" maxlength="100" placeholder="简述请假原因，最多输入100字。">${(leave.applyReason)!}</textarea>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="form-group btn-center" <#if leave?exists><#if leave.state==1>hidden</#if></#if>>
            <span class="my-btn">
                <input class="btn btn-primary submit-btn" type="submit" value="申请"/>
                <input class="btn btn-danger" type="reset" value="清空">
            </span>
        </div>
    </form>
</div>
<#include "${ctx!}/common/common_js.ftl" />
<script type="text/javascript">
    $(function () {
        <#if leave?exists>
            <#if leave.state == 1>
                $("input").attr("disabled",true);
                $("select").attr("disabled",true);
                $("textarea").attr("disabled",true);
            </#if>
        </#if>
        var validate = $("#formId").validate({
            debug : true, //调试模式取消submit的默认提交功能
            focusInvalid : false, //当为false时，验证无效时，没有焦点响应
            onkeyup : false,
            submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
                submitForm();
            },
            rules : {
                startTimeStr : {
                    required : true
                },
                endTimeStr : {
                    required : true
                },
                applyReason : {
                    required : true
                }
            },
            messages : {
                startTimeStr : {
                    required : "开始日期必须输入"
                },
                endTimeStr : {
                    required : "结束日期必须输入"
                },
                applyReason : {
                    required : "原因详细必须说明"
                }
            }
        });
    })
    //提交修改
    function submitForm() {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if(startTime.substring(0,7) != endTime.substring(0,7)){
            xl.alert("开始日期和结束日期必须同年同月,如果跨年或者跨月请分别分段申请!");
            return false;
        }
        $.ajax({
            type : "POST",//方法类型
            dataType : "json",//预期服务器返回的数据类型
            url : "/attendance/leaveSave",//url
            data : $('#formId').serialize(),
            success : function(data) {
                if (data.success) {
                    layer.msg("提交成功!", {
                        icon : 6,
                        time : 1000
                    }, function() {
                        parent.onreload();
                    })
                } else {
                    xl.alert(data.msg);
                }
            },
            error : function() {
                xl.alert("提交失败！");
            }
        });
    }
</script>
</body>
</html>
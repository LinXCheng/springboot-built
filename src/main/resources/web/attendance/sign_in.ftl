<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>签到</title>
</head>
<#include "${ctx!}/common/common_css.ftl" />
<link rel="stylesheet" type="text/css" href="${ctx!}/css/web/attendance/sign_in.css"/>
<link rel="stylesheet" href="${ctx!}/css/aliIcon/iconfont.css">
<style>
</style>
<body style="overflow: hidden;">
<div class="btn-calendar-div">
    <div class="index_frame_leftTop" style="float: left">
        <div id='schedule-boxS'></div>
        <div class="index_liTLeft">
            <div class="index_liTline"></div>
        </div>
        <div class="index_liTRight">
            <div class="index_liTline"></div>
        </div>
    </div>
    <div class="sign-in-div" style="float: right">
        <li class="dot-left"></li>
        <li class="dot-right"></li>
        <div class="div-bg">
            <#if type?exists>
                <#if type==0>
                    <button class="btn sign-in sign-in-btn" data-type="0"><i class="fa fa-pencil"></i> 上班签到</button>
                <#elseif type==1>
                    <button class="btn sign-in sign-in-btn" data-type="1"><i class="fa fa-pencil"></i> 下班签退</button>
                <#elseif type==3>
                    <button readonly="readonly" class="btn sign-in" data-type="3"><i class="fa fa-check-square-o"></i>
                        请假中
                    </button>
                <#elseif type==4>
                    <button readonly="readonly" class="btn sign-in" data-type="4"><i class="fa fa-check-square-o"></i>
                        出差中
                    </button>
                <#else>
                    <button readonly="readonly" class="btn sign-in" data-type="2"><i class="fa fa-check-square-o"></i>
                        签到完成
                    </button>
                </#if>
            </#if>
        </div>
        <div class="index_li_Left">
            <div class="index_li_line"></div>
        </div>
        <div class="index_li_Right">
            <div class="index_li_line"></div>
        </div>
    </div>
    <div class="instructions-div">
        <div class="index_li_Left_bottom"></div>
        <div class="index_li_Right_bottom"></div>
        <div>
            <ul style="padding-left: 20px;padding-right: 20px;font-size: 12px;color: #bb8d8d;">签到须知:
                <li style="padding: 15px 0px;">1、上班签到时间：06：00:00~09:00:00，下班签到时间：18:00:00~次日：06:00:00，20：00：00之后算加班时间！</li>
                <li style="padding: 0 0px 15px 0px;">2、请假与出差期间无需打卡！</li>
                <li style="padding: 0 0px 15px 0px;">3、调休补班时间无需打卡！</li>
                <li style="padding: 0 0px 15px 0px;">4、请按时打卡，补卡只能补有出勤记录的日期！</li>
                <li>5、迟到、早退、缺勤和请假都有扣工资，加班和出差都有补偿！</li>
            </ul>
        </div>
    </div>
</div>
<div class="index_frame_leftBottom">
    <div class="index_liBLeft"></div>
    <div class="index_liBRight"></div>
    <div class="index_frame_leftBottom_Top clearfix">
        <div class="index_frame_leftBottom_bottom clearfix matters">
            <#if leaveTypeDOS?exists>
                <#list leaveTypeDOS as leaveTypeDOS>
                    <div  <#if leaveTypeDOS_index=0>style="border-left: 1px solid #eee;"<#elseif !leaveTypeDOS_has_next>style="border-left: 1px solid #eee;"</#if>>
                        <span>${(leaveTypeDOS.numberDay)!}</span>
                        <span>${(leaveTypeDOS.name)!}</span>
                    </div>
                 </#list>
            </#if>
        </div>
    </div>
<#include "${ctx!}/common/common_js.ftl"/>
    <script src="${ctx!}/js/web/attendance/schedule.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx!}/js/web/attendance/echarts.simple.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        var zc = [];
        var yc = [];
    <#if zcDate?exists>
        <#if zcDate??>
        var recode = {};
            <#list zcDate as zcDate>
            var time = '${(zcDate.time?string('yyyy-MM-dd'))!}';
            var morning = '${(zcDate.morning?string('HH:mm:ss'))!}';
            var afternoon = '${(zcDate.afternoon?string('HH:mm:ss'))!}';
           recode = {
               time: time,
               morning: morning,
               afternoon: afternoon
           }
            zc.push(recode);
            </#list>
        </#if>
    </#if>
        <#if qqDate?exists>
            <#if qqDate??>
        var recode = {};
                <#list qqDate as qqDate>
            var time = '${(qqDate.time?string('yyyy-MM-dd'))!}';
            var morning = '${(qqDate.morning?string('HH:mm:ss'))!}';
            var afternoon = '${(qqDate.afternoon?string('HH:mm:ss'))!}';
           recode = {
               time: time,
               morning: morning,
               afternoon: afternoon
           }
            yc.push(recode);
                </#list>
            </#if>
        </#if>
        var mySchedule = new Schedule({
            el: '#schedule-boxS',
            qqDate: yc,
            zcDate: zc
        })

        $(function () {
            $(".sign-in-btn").click(function () {
                var type = $(this).attr("data-type");
                $.ajax({
                    url: '/attendance/saveSignIn',
                    type: 'post',
                    dataType: 'json',
                    data: {type: type},
                    success: function (data) {
                        if (data.success) {
                            if (type == 0) {
                                layer.msg("签到成功,祝您工作愉快!", {icon: 6, time: 800}, function () {
                                    location.reload();
                                })
                            } else {
                                layer.msg("签退成功,祝您生活愉快!", {icon: 6, time: 800}, function () {
                                    location.reload();
                                })
                            }
                        } else {
                            xl.alert(data.msg);
                        }
                    }
                })
            })
        })
    </script>
</body>
</html>
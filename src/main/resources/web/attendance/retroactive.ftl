<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>补签</title>
</head>
<#include "${ctx!}/common/common_css.ftl" />
<link rel="stylesheet" type="text/css" href="${ctx!}/css/web/attendance/sign_in.css" />
<link rel="stylesheet" href="${ctx!}/css/aliIcon/iconfont.css">
<style>
    .retroactive-div {
        border-radius: 10px;
        width: 450px;
        height: 330px;
        background: #fff;
        position: relative;
        margin: 30px 0 0 10%;
        float: left;
    }
    .retroactive-div-date {
        height: 280px;
        width: 100%;
        max-height: 280px;
        overflow: auto;
    }
    .retroactive-date-btn-div {
        border-radius: 5px;
        background: #e3a0a0;
        color: #fff;
        box-shadow: 0 5px 9px 0 #ffb3bb;
        margin-left: 25px;
        border: 1px solid #f6eded;
        width: 105px;
        height: 35px;
        display: block;
        line-height: 2.3;
        text-align: center;
        float: left;
        margin-top: 10px;
    }
    .close {
        position: relative;
        top: -7px;
        left: 6px;
    }
    .submit-btn {
        width: 167px;
        font-size: 20px;
        margin-left: 44%;
        margin-top: 4%;
    }
    .btn-div-holiday {
        position: relative;
        left: 27%;
    }
    .wait {
        position: relative;
        left: 11.5%;
    }
</style>
<body style="overflow: hidden;">
<div class="btn-calendar-div">
    <div class="index_frame_leftTop" style="float: left">
        <div id='schedule-boxS'></div>
    </div>
    <div class="retroactive-div">
        <div class="schedule-hd">
            <div class="today wait">待补卡</div>
            <div class="btn-div-holiday">
            <@shiro.hasAnyRoles name="admin,administrator">
                <button class="btn" onclick="lookHoliday();"><i class="fa fa-calendar"></i> 审批记录</button>
            </@shiro.hasAnyRoles>
            <@shiro.hasRole name="employees">
                <button class="btn" onclick="lookHoliday();"><i class="fa fa-calendar"></i> 历史记录</button>
            </@shiro.hasRole>
            </div>
        </div>
        <div class="retroactive-div-date">
        </div>
    </div>
</div>
<div class="submit-div">
    <button class="btn submit-btn">提交</button>
</div>
<#include "${ctx!}/common/common_js.ftl"/>
<script src="${ctx!}/js/web/attendance/sign_in.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx!}/js/web/attendance/schedule.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx!}/js/web/attendance/echarts.simple.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var zc = [];
    var yc = [];
    <#if zcDate?exists>
        <#if zcDate??>
        var recode={} ;
            <#list zcDate as zcDate>
            var time = '${(zcDate.time?string('yyyy-MM-dd'))!}';
            var morning = '${(zcDate.morning?string('HH:mm:ss'))!}';
            var afternoon = '${(zcDate.afternoon?string('HH:mm:ss'))!}';
           recode = {
               time: time,
                morning:morning,
                afternoon:afternoon
            }
            zc.push(recode);
            </#list>
        </#if>
    </#if>
        <#if qqDate?exists>
            <#if qqDate??>
        var recode={} ;
                <#list qqDate as qqDate>
            var time = '${(qqDate.time?string('yyyy-MM-dd'))!}';
            var morning = '${(qqDate.morning?string('HH:mm:ss'))!}';
            var afternoon = '${(qqDate.afternoon?string('HH:mm:ss'))!}';
           recode = {
               time: time,
               morning:morning,
               afternoon:afternoon
           }
            yc.push(recode);
                </#list>
            </#if>
        </#if>
    var mySchedule = new Schedule({
        el: '#schedule-boxS',
        qqDate :yc,
        zcDate:zc
    })

    $(function () {
        var YearMonth = '${(YearMonth)!}';
        $(document).on("click", ".qq-style", function () {
            var lg = $(".today").eq(0).text().length;
            var year, month, day;
            year = $(".today").eq(0).text().substr(0,4);
            day = $(this).text();
            if(lg == 7){
                month = "0"+$(".today").eq(0).text().substring(5,6);
            } else {
                month = $(".today").eq(0).text().substring(5,7);
            }
            var date =year+"-"+month+"-"+day;
            var flag = true;
            $(".retroactive-date-btn-div").each(function () {
                if($(this).attr("data-date") == date){
                    flag = false;
                }
            })
            if(flag){
                    if(YearMonth == year+"-"+month){
                        $(".retroactive-div-date").append("<div class='retroactive-date-btn-div' data-date='" + date + "'><span>" + date + "</span><i class='fa fa-times-circle close close-btn'></i></div>");
                    } else {
                        xl.alert("只能为本月进行补卡!")
                    }
                }else {
                xl.alert("已经存在待补卡区域了！");
            }
        })
        
        $(document).on("click",".close-btn",function () {
            $(this).parents(".retroactive-date-btn-div").remove();
        })

        $(".submit-btn").click(function () {
            var dateArr = [];
            $(".retroactive-date-btn-div").each(function () {
                dateArr.push($(this).attr("data-date"));
            })
            if(dateArr.length != 0){
                $.ajax({
                    url:'/attendance/retroactiveSave',
                    type:'post',
                    dataType:'json',
                    data:{"dateStr":dateArr.join(",")},
                    success:function (data) {
                        if(data.success){
                            layer.msg("请等待领导审核！",{icon:6,time:800},function () {
                                location.reload();
                            })
                        } else {
                            xl.alert("提交失败！")
                        }
                    }
                })
            } else {
                xl.alert("您没有添加需要待补卡的记录！");
            }
        })
    })
    
    function lookHoliday() {
        location.href='${ctx!}/attendance/retroactiveList'
    }
</script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>补卡记录</title>
</head>
<#include "${ctx!}/common/common_css.ftl" />
<style>
    input[readonly]{
        background-color:#ffffff !important;
    }
    .btn-set {
        float: left;
        margin: 10px 0px;
        width: 46%;
        height: 34px;
    }
    .dateInterval {
        width: 37%;
        float: left;
        height: 34px !important;
        margin-right: 16px;
        position: relative;
        left: 12%;
    }
    .name-search {
        width: 40% !important;
        float: left !important;
        margin-left: 10.6% !important;
    }
    .laydate-inp {
        width: 45%;
        float: left;
        height: 100%;
        margin-left: 45.6%;
    }
</style>
<body>
<div>
    <div class="ibox-content" style="margin-top: 0%;width: 94%;margin-left: 3%;">
        <div class="btn-div">
        	<div class="btn-set ">
            </div>
            <div class="search-div" style="width: 50%;">
                <@shiro.hasAnyRoles name="admin,administrator">
                    <input type="text" id="YearMonth" name="YearMonth" class="input-sm form-control laydate dateInterval" readonly  data-format="yyyy-MM" data-type="month" placeholder="请选择查询时间" value="${(YearMonth)!}" >
                    <input type="text" id="keyword" name="keyword" placeholder="请输入姓名" value="${(keyword)!}" class="input-sm form-control name-search">
                </@shiro.hasAnyRoles>
                <@shiro.hasRole name="employees">
                    <input type="text" id="YearMonth" name="YearMonth" class="input-sm form-control laydate laydate-inp" readonly  data-format="yyyy-MM" data-type="month" placeholder="请选择查询时间" value="${(YearMonth)!}" >
                </@shiro.hasRole>
                <span class="input-group-btn" id="search-btn">
                	<button type="button" class="btn btn-sm btn-primary " onclick="searchBtn();"> 搜索</button>
                </span>
            </div>
        </div>
        <@shiro.hasRole name="employees">
            <div id="left_dict" style="overflow-y: auto;">
                <table id="example" class="table table-striped table-bordered table-hover dataTables-example dataTable"
                       cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="tabtd">工资所属月</th>
                        <th class="tabtd">实际工资</th>
                        <th class="tabtd">出勤天数</th>
                        <th class="tabtd">日薪</th>
                        <th class="tabtd">请假天数</th>
                        <th class="tabtd">请假扣款合计</th>
                        <th class="tabtd">差旅天数</th>
                        <th class="tabtd">差旅补贴</th>
                        <th class="tabtd">加班天数</th>
                        <th class="tabtd">加班合计</th>
                        <th class="tabtd">出勤异常扣款</th>
                        <th class="tabtd">应发合计</th>
                        <th class="tabtd">养老保险</th>
                        <th class="tabtd">失业保险</th>
                        <th class="tabtd">医疗保险</th>
                        <th class="tabtd">住房公积金</th>
                        <th class="tabtd">五险一金总扣款</th>
                        <th class="tabtd">税前工资</th>
                        <th class="tabtd">税率</th>
                        <th class="tabtd">个人所得税</th>
                        <th class="tabtd">实际工资</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if wage?exists>
                        <tr>
                            <td class="tabtd">${(wage.yearMonth)!}</td>
                            <td class="tabtd">${(wage.wage?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.attendance)!}</td>
                            <td class="tabtd">${(wage.dailyWage?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.leaveDay)!}</td>
                            <td class="tabtd">${(wage.leaveAmount?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.businessDay)!}</td>
                            <td class="tabtd">${(wage.businessAmount?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.overtimeDay)!}</td>
                            <td class="tabtd">${(wage.overtimeAmount?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.otherDeduct?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.totalAmount?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.pension?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.unemploymentBenefits?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.medical?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.housingFund?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.fiveInsurances?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.grossSalary?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.rate)!}</td>
                            <td class="tabtd">${(wage.personalIncomeTax?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.realWages?string('0.00'))!}</td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
        </@shiro.hasRole>
        <@shiro.hasAnyRoles name="admin,administrator">
            <div id="left_dict" style="overflow-y: auto;">
                <table id="example" class="table table-striped table-bordered table-hover dataTables-example dataTable"
                       cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="tabtd">员工</th>
                        <th class="tabtd">工资所属月</th>
                        <th class="tabtd">实际工资</th>
                        <th class="tabtd">出勤天数</th>
                        <th class="tabtd">日薪</th>
                        <th class="tabtd">请假天数</th>
                        <th class="tabtd">请假扣款合计</th>
                        <th class="tabtd">差旅天数</th>
                        <th class="tabtd">差旅补贴</th>
                        <th class="tabtd">加班天数</th>
                        <th class="tabtd">加班合计</th>
                        <th class="tabtd">出勤异常扣款</th>
                        <th class="tabtd">应发合计</th>
                        <th class="tabtd">养老保险</th>
                        <th class="tabtd">失业保险</th>
                        <th class="tabtd">医疗保险</th>
                        <th class="tabtd">住房公积金</th>
                        <th class="tabtd">五险一金总扣款</th>
                        <th class="tabtd">税前工资</th>
                        <th class="tabtd">税率</th>
                        <th class="tabtd">个人所得税</th>
                        <th class="tabtd">实际工资</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if wageList?exists>
                    <#if wageList??>
                    <#list wageList as wage>
                        <tr>
                            <td class="tabtd">${(wage.nickName)!}</td>
                            <td class="tabtd">${(wage.yearMonth)!}</td>
                            <td class="tabtd">${(wage.wage?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.attendance)!}</td>
                            <td class="tabtd">${(wage.dailyWage?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.leaveDay)!}</td>
                            <td class="tabtd">${(wage.leaveAmount?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.businessDay)!}</td>
                            <td class="tabtd">${(wage.businessAmount?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.overtimeDay)!}</td>
                            <td class="tabtd">${(wage.overtimeAmount?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.otherDeduct?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.totalAmount?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.pension?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.unemploymentBenefits?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.medical?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.housingFund?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.fiveInsurances?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.grossSalary?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.rate)!}</td>
                            <td class="tabtd">${(wage.personalIncomeTax?string('0.00'))!}</td>
                            <td class="tabtd">${(wage.realWages?string('0.00'))!}</td>
                        </tr>
                    </#list>
                    </#if>
                    </#if>
                    </tbody>
                </table>
            </div>
        </@shiro.hasAnyRoles>
    </div>
</div>
<#include "${ctx!}/common/common_js.ftl"/>
<script type="text/javascript">
    function searchBtn() {
        if(null != $("#keyword").val() && "" != $("#keyword").val() && undefined != $("#keyword").val()){
            location.href = "/wage?YearMonth="+$("#YearMonth").val()+"&&keyword="+$("#keyword").val()+"";
        } else {
            location.href = "/wage?YearMonth=" + $("#YearMonth").val();
        }
    }
</script>
</body>
</html>
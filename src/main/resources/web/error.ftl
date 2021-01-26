<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>403 无访问该资源权限</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx!}/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="${ctx!}/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx!}/layui/modules/layer/default/layer.css" media="all">
    <style>
        .layui-text h1 {
            font-size: 100px !important;
        }
        .layadmin-tips {
            margin-top: 0px;
            text-align: center;
        }
    </style>
    <style id="LAY_layadmin_theme">
        .layui-side-menu,.layadmin-pagetabs .layui-tab-title li:after,.layadmin-pagetabs .layui-tab-title li.layui-this:after,.layui-layer-admin .layui-layer-title,.layadmin-side-shrink .layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child{background-color:#20222A !important;}
        .layui-nav-tree .layui-this,.layui-nav-tree .layui-this>a,.layui-nav-tree .layui-nav-child dd.layui-this,.layui-nav-tree .layui-nav-child dd.layui-this a{background-color:#009688 !important;}
        .layui-layout-admin .layui-logo{background-color:#20222A !important;}
    </style>
</head>
<body layadmin-themealias="default">
<div class="layui-fluid">
    <div class="layadmin-tips">
        <i class="layui-icon" face=""></i>
        <div class="layui-text">
            <h1>
                <span class="layui-anim layui-anim-loop layui-anim-">4</span>
                <span class="layui-anim layui-anim-loop layui-anim-rotate">0</span>
                <span class="layui-anim layui-anim-loop layui-anim-">3</span>
            </h1>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx!}/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx!}/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index']);
</script>
</body>
</html>
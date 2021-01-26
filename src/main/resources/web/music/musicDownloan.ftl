<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title></title>
</head>
<#include "${ctx!}/common/common_css.ftl"/>
<body>
<button onclick="downloan();">下载</button>
<#include "${ctx!}/common/common_js.ftl"/>
<script type="text/javascript">
    function downloan() {
        $.ajax({
            url: 'http://music.onlychen.cn/',
            type: 'post',
            dataType: 'JSON',
            crossDomain: true,
            data: {input: "周杰伦", filter: "name", type: "qq", page: "1"},
            success: function (json) {
                alert("1");
            }
        })
    }
</script>
</body>
</html>
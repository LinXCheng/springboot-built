var xl={};
$(function () {
    //代替alert
    xl.alert = function(msg, title){
        if(msg == undefined){
            msg = "";
        }
        if(title == undefined){
            title = "提示";
        }
        layer.alert(msg,{
            skin: 'layui-layer-molv' //样式类名
            ,closeBtn: 1,
            shade: false, //点击遮罩关闭层
            title : [title , true]
        });
    };
//代替confirm
    xl.confirm = function(msg, conYes){
        layer.confirm(msg,{shade: false},conYes);
    };
    //弹出窗口,等待封装...
    xl.open = function(options){
        if(options.success == undefined){
            options.success = function(layero){
                layer.setTop(layero);//置顶
            };
        }
        var index = layer.open({
            type: 2,//iframe层
            title: options.title,
            closeBtn: options.closeBtn, //不显示关闭按钮
            shadeClose: true,
            shade: false,
            zIndex: layer.zIndex,
            maxmin: options.maxmin, //开启最大化最小化按钮
            area: [options.width+"px", options.height+"px"],
            content: options.url|| options.html,
            btn: options.btn,
            yes:options.yes,//【按钮一】的回调
            cancel:options.cancel,//按钮【按钮二】的回调
            btn3:options.btn3,
            btn4:options.btn4,
            success: options.success//成功后的回调
        });
        return index;
    };
    //在弹出页关闭自身的方法
    xl.close =  function(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    }; 
//开启加载中
    xl.spinOpen = function(target){
        var spinnerOpts = {
            lines: 13, // 共有几条线组成
            length: 7, // 每条线的长度
            width: 4, // 每条线的粗细
            radius: 10, // 内圈的大小
            scale: 1, // 尺度旋转器的总体规模
            corners: 1, // 圆角的程度
            color: '#000', // 颜色 #rgb 或 #rrggbb
            opacity: 0.25, // Opacity of the lines
            rotate: 0, // 整体的角度（因为是个环形的，所以角度变不变其实都差不多）
            direction: 1, // 1:顺时针,-1:逆时针方向
            speed: 1, // 速度：每秒的圈数
            trail: 60, // 高亮尾巴的长度
            fps: 20, // Frames per second when using setTimeout() as a fallback for CSS
            zIndex: 2e9, // 高度 (默认2000000000)
            className: 'spinner', // class的名字
            top: '50%', // 样式中的top的值（以像素为单位，写法同css）
            left: '50%', // 样式中的left的值（以像素为单位，写法同css）
            shadow: false, // 是否要阴影
            hwaccel: false, // 是否用硬件加速
            position: 'absolute' // 元素位置
        };
        var spinner = new Spinner(spinnerOpts).spin(target);
        return  spinner;
    };
//关闭加载中
    xl.spinClose = function(spinner){
        spinner.spin();
    };


    $('.laydate').each(function(i){
        var dateFormat = $('.laydate').eq(i).attr("data-format");
        var min = $('.laydate').eq(i).attr("min");
        var max = $('.laydate').eq(i).attr("max");
        var type = $('.laydate').eq(i).attr("data-type");
        var range = $('.laydate').eq(i).attr("range");
        if(dateFormat == undefined || dateFormat == "" || dateFormat == 'yyyy-MM-dd HH:mm:ss'){
            dateFormat = 'yyyy-MM-dd HH:mm:ss';
        }else if(dateFormat == 'yyyy-MM-dd HH'){
            dateFormat = 'yyyy-MM-dd HH';
        }else if(dateFormat == 'yyyy-MM-dd'){
            dateFormat = 'yyyy-MM-dd';
        }else if(dateFormat == 'yyyy-MM'){
            dateFormat = 'yyyy-MM';
        }

        if(type == undefined || type == '' || type == 'date'){
            type = 'date';
        }else if(type == 'time'){
            type = 'time';
        }else if(type == 'datetime'){
            type = 'datetime';
        }else if(type == 'year'){
            type = 'year';
        }else if(type == 'month'){
            type = 'month';
        }

        if(range=='' || range == undefined){
            range = null;
        }

        if(min=='' || min == undefined){
            min = '1970-1-1';
        }
        if(max=='' || max == undefined){
            max = '2099-12-31';
        }
        /*        if(range=='' || range == undefined || range == null){
                    laydate.render({
                      elem: this,
                      format: dateFormat,
                      type:type,
                      min:min,
                      max:max,
                      theme: '#FF6666',
                      range: range,
                      btns: ['clear','now']
                    });
                }else{*/
        laydate.render({
            elem: this,
            format: dateFormat,
            type:type,
            min:min,
            max:max,
            theme: '#FF6666',
            range: range,
        });
        //}
    });
    $(".onlyCheck").click(function () {
        var $this = $(this);
        var flag = $this.prop("checked");
        var Onlycheck = $(".onlyCheck");
        Onlycheck.each(function(i, v) {
            $(this).prop("checked", false);
        })
        if (!flag) {
            $this.prop("checked", false);
        } else {
            $this.prop("checked", true);
        }
    })

    $(".allCb").click(function () {
        if($(this).prop("checked")) {
            $(".cb").each(function () {
                var $this = $(this);
                var flag = $this.prop("checked");
                if (!flag) {
                    $this.prop("checked", true);
                }
            })
        } else {
            $(".cb").each(function () {
                var $this = $(this);
                var flag = $this.prop("checked");
                if (flag) {
                    $this.prop("checked", false);
                }
            })
        }
    })
});
// 复选框 互斥事件
function onlyCheck(obj) {
	var $this = $(obj);
	var flag = $this.prop("checked");
	var Onlycheck = $(".onlyCheck");
	Onlycheck.each(function(i, v) {
		$(this).prop("checked", false);
	})
	if (!flag) {
		$this.prop("checked", false);
	} else {
		$this.prop("checked", true);
	}
}
//调用此方法则验证输入值是保留两位并且只有一个小数点的价格型数字
function checkMoney(obj){
	  var val = obj.value;
    //先把非数字的都替换掉，除了数字和.
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/^\./g,"");
    var len1 = obj.value.substr(0,1);
    var len2 = obj.value.substr(1,1);
    //如果第一位是0，第二位不是.,就全部替换''
    if(obj.value.length > 1 && len1==0 && len2 != '.'){
  	  obj.value = '';
    }
    //保证只有出现一个.而没有多个.
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    //只能输入两个小数
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}
// input调用此方法则保留两位小数
function ValFloat(obj) {
	obj.value = parseFloat(obj.value).toFixed(2);
}

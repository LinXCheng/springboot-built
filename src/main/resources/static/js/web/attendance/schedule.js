;(function (undefined) {
    var _global;

    function extend(def, opt, override) {
        for (var k in opt) {
            if (opt.hasOwnProperty(k) && (!def.hasOwnProperty(k) || override)) {
                def[k] = opt[k]
            }
        }
        return def;
    }

    function formartDate(y, m, d, symbol) {
        symbol = symbol || '-';
        m = (m.toString())[1] ? m : '0' + m;
        d = (d.toString())[1] ? d : '0' + d;
        return y + symbol + m + symbol + d
    }

    function Schedule(opt) {
        var def = {}, opt = extend(def, opt, true), curDate = opt.date ? new Date(opt.date) : new Date(),
            year = opt.selectYear || curDate.getFullYear(), month = opt.selectMonth || curDate.getMonth(),
            day = curDate.getDate(), currentYear = curDate.getFullYear(), currentMonth = curDate.getMonth(),
            currentDay = curDate.getDate(), selectedDate = '', qqDate = opt.qqDate || "", zcDate = opt.zcDate || "",
            el = document.querySelector(opt.el) || document.querySelector('body'), _this = this;
        var bindEvent = function () {
            el.addEventListener('click', function (e) {
                switch (e.target.id) {
                    case 'nextMonth':
                        _this.nextMonthFun();
                        break;
                    case 'nextYear':
                        _this.nextYearFun();
                        break;
                    case 'prevMonth':
                        _this.prevMonthFun();
                        break;
                    case 'prevYear':
                        _this.prevYearFun();
                        break;
                    default:
                        break;
                }
                ;
                if (e.target.className.indexOf('currentDate') > -1) {
                    opt.clickCb && opt.clickCb(year, month + 1, e.target.innerHTML, e.target);
                    selectedDate = e.target.title;
                    day = e.target.innerHTML;
                    render();
                }
            }, false)
        }
        var init = function () {
            var scheduleHd = '<div class="schedule-hd">' +
                '<div>' +
                '<span class="arrow icon iconfont icon-jiantou-copy" id="prevMonth"></span>' +
                '</div>' +
                '<div class="today">' + year + "年" + (month + 1) + "月" + '</div>' +
                '<div>' +
                '<span class="arrow icon iconfont icon-arrow-right" id="nextMonth"></span>' +
                '</div>' +
                '</div>'
            var scheduleWeek = '<ul class="week-ul ul-box clearfix">' +
                '<li>日</li>' +
                '<li>一</li>' +
                '<li>二</li>' +
                '<li>三</li>' +
                '<li>四</li>' +
                '<li>五</li>' +
                '<li>六</li>' +
                '</ul>'
            var scheduleBd = '<ul class="schedule-bd ul-box clearfix" ></ul>';
            el.innerHTML = scheduleHd + scheduleWeek + scheduleBd;
            bindEvent();
            render();
        }
        var render = function () {
            var fullDay = new Date(year, month + 1, 0).getDate(), startWeek = new Date(year, month, 1).getDay(),
                total = (fullDay + startWeek) % 7 == 0 ? (fullDay + startWeek) : fullDay + startWeek + (7 - (fullDay + startWeek) % 7),
                lastMonthDay = new Date(year, month, 0).getDate(), eleTemp = [];
            for (var i = 0; i < total; i++) {
                if (i < startWeek) {
                    var nowDate = formartDate(year, month, ((lastMonthDay - startWeek) + 1 + i), '-');
                    var addClass = '';
                    eleTemp.push('<li class="other-month"><span class="dayStyle ' + addClass + '">' + (lastMonthDay - startWeek + 1 + i) + '</span></li>')
                } else if (i < (startWeek + fullDay)) {
                    var nowDate = formartDate(year, month + 1, (i + 1 - startWeek), '-');
                    var addClass = '';
                    var morning = '';
                    var afternoon = '';
                    for (var j = 0; j < zcDate.length; j++) {
                        zcDate[j].time == nowDate && (addClass = 'zc_day', morning = zcDate[j].morning, afternoon = zcDate[j].afternoon);
                    }
                    for (var z = 0; z < qqDate.length; z++) {
                        qqDate[z].time == nowDate && (addClass = 'qq-style', morning = qqDate[z].morning, afternoon = qqDate[z].afternoon);
                    }
                    eleTemp.push('<li class="current-month" ><span  class="currentDate dayStyle ' + addClass + '">' + (i + 1 - startWeek) + '</span><div class="day_time"><div>上班：' + morning + '</div><div>下班：' + afternoon + '</div></li>')
                } else {
                    eleTemp.push('<li class="other-month"><span class="dayStyle">' + (i + 1 - (startWeek + fullDay)) + '</span></li>')
                }
            }
            el.querySelector('.schedule-bd').innerHTML = eleTemp.join('');
            el.querySelector('.today').innerHTML = year + "年" + (month + 1) + "月";
        };
        this.nextMonthFun = function () {
            if (month + 1 > 11) {
                year += 1;
                month = 0;
            } else {
                month += 1;
            }
            render();
            opt.nextMonthCb && opt.nextMonthCb(year, month + 1, day);
        }, this.nextYearFun = function () {
            year += 1;
            render();
            opt.nextYeayCb && opt.nextYeayCb(year, month + 1, day);
        }, this.prevMonthFun = function () {
            if (month - 1 < 0) {
                year -= 1;
                month = 11;
            } else {
                month -= 1;
            }
            render();
            opt.prevMonthCb && opt.prevMonthCb(year, month + 1, day);
        }, this.prevYearFun = function () {
            year -= 1;
            render();
            opt.prevYearCb && opt.prevYearCb(year, month + 1, day);
        }
        init();
    }

    _global = (function () {
        return this || (0, eval)('this')
    }());
    if (typeof module !== 'undefined' && module.exports) {
        module.exports = Schedule;
    } else if (typeof define === "function" && define.amd) {
        define(function () {
            return Schedule;
        })
    } else {
        !('Schedule' in _global) && (_global.Schedule = Schedule);
    }
}());
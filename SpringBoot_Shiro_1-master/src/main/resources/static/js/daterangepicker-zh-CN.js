/**
 * Simplified Chinese translation for bootstrap-datetimepicker
 * Yuan Cheung <advanimal@gmail.com>
 */
;(function($){
    $('#time-range').daterangepicker({
        locale : {
            daysOfWeek:["日","一","二","三","四","五","六"],
            monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
            customRangeLabel : '自定义',
            fromLabel : '起始时间',
            toLabel : '结束时间',
            applyLabel: '确认',
            cancelLabel: '取消',
            firstDay : 1
        },
        ranges: {
            '今日': [moment().startOf('Day'), moment().endOf('Day')],
            '昨日': [moment().subtract(1, 'days').startOf('Day'), moment().subtract(1, 'days').endOf('Day')],
            '前七日': [moment().subtract(6, 'days').startOf('Day'), moment().endOf('Day')],
            '前三十日': [moment().subtract(29, 'days').startOf('Day'), moment().endOf('Day')],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    });
    $('#time-range1').daterangepicker({
        locale : {
            daysOfWeek:["日","一","二","三","四","五","六"],
            monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
            customRangeLabel : '自定义',
            fromLabel : '起始时间',
            toLabel : '结束时间',
            applyLabel: '确认',
            cancelLabel: '取消',
            firstDay : 1
        },
        ranges: {
            '今日': [moment().startOf('Day'), moment().endOf('Day')],
            '昨日': [moment().subtract(1, 'days').startOf('Day'), moment().subtract(1, 'days').endOf('Day')],
            '前七日': [moment().subtract(6, 'days').startOf('Day'), moment().endOf('Day')],
            '前三十日': [moment().subtract(29, 'days').startOf('Day'), moment().endOf('Day')],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    });
    $('#time-range2').daterangepicker({
        locale : {
            daysOfWeek:["日","一","二","三","四","五","六"],
            monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
            customRangeLabel : '自定义',
            fromLabel : '起始时间',
            toLabel : '结束时间',
            applyLabel: '确认',
            cancelLabel: '取消',
            firstDay : 1
        },
        ranges: {
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    });
}(jQuery));
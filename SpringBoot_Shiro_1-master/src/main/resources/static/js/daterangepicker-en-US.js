/**
 * Simplified Chinese translation for bootstrap-datetimepicker
 * Yuan Cheung <advanimal@gmail.com>
 */
;(function($){
    $('#time-range').daterangepicker({
        locale : {
            daysOfWeek:["Su","Mo","Tu","We","Th","Fr","Sa"],
            monthNames: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
            customRangeLabel : 'Custom',
            fromLabel : 'FROM',
            toLabel : 'TO',
            applyLabel: 'Apply',
            cancelLabel: 'Cancel',
            firstDay : 1
        },
        ranges: {
            'Today': [moment().startOf('Day'), moment().endOf('Day')],
            'Yesterday': [moment().subtract(1, 'days').startOf('Day'), moment().subtract(1, 'days').endOf('Day')],
            'Last 7 Days': [moment().subtract(6, 'days').startOf('Day'), moment().endOf('Day')],
            'Last 30 Days': [moment().subtract(29, 'days').startOf('Day'), moment().endOf('Day')],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    });
    $('#time-range1').daterangepicker({
        locale : {
            daysOfWeek:["Su","Mo","Tu","We","Th","Fr","Sa"],
            monthNames: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
            customRangeLabel : 'Custom',
            fromLabel : 'FROM',
            toLabel : 'TO',
            applyLabel: 'Apply',
            cancelLabel: 'Cancel',
            firstDay : 1
        },
        ranges: {
            'Today': [moment().startOf('Day'), moment().endOf('Day')],
            'Yesterday': [moment().subtract(1, 'days').startOf('Day'), moment().subtract(1, 'days').endOf('Day')],
            'Last 7 Days': [moment().subtract(6, 'days').startOf('Day'), moment().endOf('Day')],
            'Last 30 Days': [moment().subtract(29, 'days').startOf('Day'), moment().endOf('Day')],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    });
    $('#time-range2').daterangepicker({
        locale : {
            daysOfWeek:["Su","Mo","Tu","We","Th","Fr","Sa"],
            monthNames: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
            customRangeLabel : 'Custom',
            fromLabel : 'FROM',
            toLabel : 'TO',
            applyLabel: 'Apply',
            cancelLabel: 'Cancel',
            firstDay : 1
        },
        ranges: {
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    });
}(jQuery));
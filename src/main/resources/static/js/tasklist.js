$(document).ready(function () {
    /** init **/
    function init() {
      document.onkeydown = function (e) {
          if (!e) {
              e = windows.event;
          }
          if ((e.keyCode || e.which) == 13) {
              search();
          }
      }
    }

    let search = function() {
        document.getElementById("form1").action = "/tasklist";
        document.getElementById("form1").submit();
    }

    let setstate = function() {
        document.getElementById("form1").action = "/updateTaskStatus";
        document.getElementById("form1").submit();
    }

    let selectAll = function() {
        var checkboxObjs = document.getElementsByName("checkboxid");
        for (var i = 0; i < checkboxObjs.length; i++) {
            checkboxObjs[i].checked = true;
        }
    }

    let unselectAll = function() {
        var checkboxObjs = document.getElementsByName("checkboxid");
        for (var i = 0; i < checkboxObjs.length; i++) {
            checkboxObjs[i].checked = false;
        }
    }

    // 点击日期切换事件
    $(".changeStatDt").bind("click", function () {
        $(".changeStatDt").parent().removeClass("active");
        $(this).parent().addClass("active");
        let num = $(this).attr("data-miner-num");
        let unit = $(this).attr("data-miner-unit");

        let statDtBeginObj = $("input[name='statDtBegin'");

        //current Data
        var MyDate = DateUtil.MyDate;
        var date = new MyDate();
        var dateStr = "";
        if (unit == "D") {
            dateStr = date.minusDate(num).toString("YYYY-MM-DD");
        } else if (unit == "M") {
            dateStr = date.minusMonth(num).toString("YYYY-MM-DD");
        } else if (unit == "Y") {
            dateStr = date.minusYear(num).toString("YYYY-MM-DD");
        }
        statDtBeginObj.val(dateStr);

        document.getElementById("form1").action = "/tasklist";
        document.getElementById("form1").submit();
    });

    //搜索
    $("#search").bind("click",search);

    //设置任务状态
    $("#setstate").bind("click",setstate);

    $("#selectAll").bind("click",selectAll);
    $("#unselectAll").bind("click",unselectAll);
    init();
});

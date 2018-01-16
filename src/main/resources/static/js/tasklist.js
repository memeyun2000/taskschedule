$(document).ready(function () {

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
});
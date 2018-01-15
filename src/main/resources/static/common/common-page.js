$(document).ready(function(){
    
    $(".page-link").bind("click",function(){
        $("input[name='pageNum'").val($(this).attr('pageindex'));

        document.getElementById("form1").action = "/tasklist";
        document.getElementById("form1").submit();
    });
});
$(document).ready(function(){
    $('.salesContent').click(function(){
        // 현재 선택된 차트에 따라 페이지를 결정
        if ($('#myChart').is(':visible')) {
            window.location.href = 'sales/dayPage';
        } else if ($('#myChart1').is(':visible')) {
            window.location.href = 'sales/weekPage';
        }else if($('#myChart2').is(':visible')){
            window.location.href='sales/monPage'
        }

    });
    $('#myChart').show();
    $('#myChart1, #myChart2').hide();

    $('.day').click(function(){
        $('#myChart').show();
        $('#myChart1, #myChart2').hide();
    });

    $('.week').click(function(){
        $('#myChart1').show();
        $('#myChart, #myChart2').hide();
    });

    $('.mon').click(function(){
        $('#myChart2').show();
        $('#myChart, #myChart1').hide();
    });
});
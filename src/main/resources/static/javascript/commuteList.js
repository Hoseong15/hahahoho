$(function (){

    var next = $(".com_nt");
    var prev = $(".com_pt");


    prev.on("click", function () {
        // Get the current month
        var currentMonth = parseInt($(".now-date").text());

        // Increment the month by 1 (if it's December, go to January of the next year)
        currentMonth = currentMonth === 12 ? 1 : currentMonth + 1;

        // Update the now-date span with the new month
        $(".now-date").text(currentMonth < 10 ? '0' + currentMonth : currentMonth);
    });

    next.on("click", function () {
        // Get the current month
        var currentMonth = parseInt($(".now-date").text());

        // Decrement the month by 1 (if it's January, go to December of the previous year)
        currentMonth = currentMonth === 1 ? 12 : currentMonth - 1;

        // Update the now-date span with the new month
        $(".now-date").text(currentMonth < 10 ? '0' + currentMonth : currentMonth);
    });

    $(".now-date").on("click", function () {
        // Get the current date
        var currentDate = new Date();
        var currentMonth = currentDate.getMonth() + 1; // JavaScript months are 0-indexed

        // Update the now-date span with the current month
        $(".now-date").text(currentMonth < 10 ? '0' + currentMonth : currentMonth);
    });

});
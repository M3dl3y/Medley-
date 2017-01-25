/**
 * Created by Nick on 1/22/17.
 */
$('#heartDiv').hover(
    function() {
        $('#moreInfo').css('display', 'block');
        $('#moreInfoDetail').append('Your health is very important to us');
    },
    function() {
        $('#moreInfo').css('display', 'none');
        $('#moreInfoDetail').empty();
    }
);
$('#calendarDiv').hover(
    function() {
        $('#moreInfo').css('display', 'block');
        $('#moreInfoDetail').append('Save appointments and we will remind you');
    },
    function() {
        $('#moreInfo').css('display', 'none');
        $('#moreInfoDetail').empty();
    }
);
$('#reminderDiv').hover(
    function() {
        $('#moreInfo').css('display', 'block');
        $('#moreInfoDetail').append('Remember to take your pills');
    },
    function() {
        $('#moreInfo').css('display', 'none');
        $('#moreInfoDetail').empty();
    }
);
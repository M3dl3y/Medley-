/**
 *
 * Created by jessedavila on 1/23/17.
 */


var prescriptionsDaySupply = $(".prescriptionDaySupply");
var daySupply;

var counter = 0;
prescriptionsDaySupply.each(function( index ) {
    daySupply = $(this).text();
    if (daySupply <= 3) {
        counter++;
    }
});

document.getElementById("medicationAlertNumber").innerHTML = counter;


/**
 *
 * Created by jessedavila on 1/23/17.
 */



// if prescription supply is under 3, add to alert counter
var prescriptionsDaySupply = $(".prescriptionDaySupply");
var daySupply;
var alertNum = 3;

var counter = 0;
prescriptionsDaySupply.each(function( index ) {
    daySupply = $(this).text();
    if (daySupply <= alertNum) {
        counter++;
    }
});

document.getElementById("medicationAlertNumber").innerHTML = counter;



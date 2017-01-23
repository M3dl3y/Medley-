/**
 *
 * Created by jessedavila on 1/23/17.
 */


var prescriptionsDaySupply = $(".prescriptionDaySupply");
var daySupply;
document.getElementById("medicationAlertNumber").innerHTML = prescriptionsDaySupply[0].innerHTML;

var counter = 0;
prescriptionsDaySupply.each(function( index ) {
    daySupply = $(this).text();
    console.log( index + ": " + daySupply );
});


// if i can pull the day_supply from prescriptions that are read into the dashboard page from the controller,
// can i use a loop or something to tally up meds that are below a certain int num?
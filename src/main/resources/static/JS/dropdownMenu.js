/**
 * Created by Nick on 1/23/17.
 */
$(".dropdown-button").dropdown();

$(document).ready(function(){
    $(".button-collapse").sideNav();
});

$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
});

$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year
    format: "yyyy-mm-dd",
    formatSubmit: "yyyy-mm-dd"
});


$(document).ready(function(){
    $('.parallax').parallax();
});


/**
 * Created by Nick on 1/24/17.
 */

$(document).ready(function(){

$('#addMedicationButton').click(function(){
    document.addPrescriptionForm.submit();
    return false;
});

$('#addAppointmentSubmit').click(function(){
    document.addAppointmentForm.submit();
    return false;
});
$('#editLoginCredSubmit').click(function(){
    document.editLoginCredForm.submit();
    return false;
});

$('#editUserDetailSubmit').click(function(){
    document.editUserDetailForm.submit();
    return false;
});
//
// $('.editPrescriptionSubmit').click(function(){
//     //document.editPrescriptionForm.submit();
//     console.log("editPrescriptionSubmit");
//     $(this).closest("form").submit();
//     return false;
// });


});
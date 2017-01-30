/**
 * Created by Nick on 1/24/17.
 */

$(document).ready(function(){

$('#medTaken').click(function(){
    document.medTakenForm.submit();
    return false;
});

$('#editReminderSubmit').click(function () {
    document.editReminderForm.submit();
    return false;
});

$('#medTaken').click(function(){
    document.medTakenForm.submit();
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
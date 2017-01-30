/**
 * Created by Nick on 1/29/17.
 */
$().ready(function(){

    $('#addPrescriptionForm').validate({
        rules: {
            rxName: {
                required: true
            },
            prescribedDate: {
                required: true
            },
            strength: {
                required: true
            },
            sig: {
                required: true
            },
            daySupply: {
                required: true
            },
            prescribedQuantity: {
                required: true
            }
        },
        messages:{
            rxName:{
                required: "What is the name of this medication?"
            },
            prescribedDate:{
                required: "What is the date you were given this medication?"
            },
            strength:{
                required: "What is the dosage on this medication? Ex: 5mg"
            },
            sig:{
                required: "How are you suppose to take this medication?"
            },
            daySupply:{
                required: "How many days will this medication last you?"
            },
            prescribedQuantity:{
                required: "How much of the medication were your prescribed? "
            }
        },
        errorElement: 'div',
        errorPlacement: function (error, element) {
            var placement = $(element).data('error');
            if(placement){
                $(placement).append(error)
            }else{
                error.insertAfter(element);
            }
        }
    });



    $('#editMedicationForm').validate({
        rules: {
            rxName: {
                required: true
            },
            prescribedDate: {
                required: true
            },
            strength: {
                required: true
            },
            sig: {
                required: true
            },
            daySupply: {
                required: true
            },
            prescribedQuantity: {
                required: true
            }
        },
        messages:{
            rxName:{
                required: "What is the name of this medication?"
            },
            prescribedDate:{
                required: "What is the date you were given this medication?"
            },
            strength:{
                required: "What is the dosage on this medication? Ex: 5mg"
            },
            sig:{
                required: "How are you suppose to take this medication?"
            },
            daySupply:{
                required: "How many days will this medication last you?"
            },
            prescribedQuantity:{
                required: "How much of the medication were your prescribed? "
            }
        },
        errorElement: 'div',
        errorPlacement: function (error, element) {
            var placement = $(element).data('error');
            if(placement){
                $(placement).append(error)
            }else{
                error.insertAfter(element);
            }
        }
    });

});
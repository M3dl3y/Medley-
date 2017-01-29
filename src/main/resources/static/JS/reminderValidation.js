/**
 * Created by Nick on 1/29/17.
 */
$().ready(function(){

    $('#addReminderForm').validate({
        rules: {
            nameReminder: {
                required: true
            }
        },
        messages:{
            nameReminder:{
                required: "A reminder must have a name."
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
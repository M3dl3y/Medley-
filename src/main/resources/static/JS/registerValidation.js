/**
 * Created by Nick on 1/28/17.
 */
$().ready(function(){

    $('#registerForm').validate({
        rules: {
            firstName: {
                required: true
            },
            lastName: {
                required: true
            },
            phoneNumber:{
                required: true
            },
            email: {
                required: false,
                email: true
            },
            username: {
                required: true,
                minlength: 5
            },
            password: {
                required: true,
                minlength: 5
            }
        },
        messages:{
            firstName:{
                required: "Please enter your first name"
            },
            lastName:{
              required: "Please enter your last name"
            },
            phoneNumber:{
                required: "Please enter your phone number"
            },
            username:{
                required: "Enter your username",
                minlength: "A username is at least 5 characters"
            },
            password:{
                required: "Please enter your password",
                minlength: "A password is at least 5 characters"
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
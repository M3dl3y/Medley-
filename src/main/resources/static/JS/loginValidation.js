/**
 * Created by Nick on 1/22/17.
 */
var loginButton = $('#loginButton');

//values/data
var username = $('#username');
var password = $('#password');

//Inputs
var usernameInput = $('#usernameInput');
var passwordInput = $('#passwordInput');

//Error message Div
var alertMessage = $('#alertMessage');

//Error Message
var usernameErrorMessage = $('#usernameErrorMessage');
var passwordErrorMessage = $('#passwordErrorMessage');

loginButton.click(function(event){
    if(username.val().length == 0){
        usernameInput.addClass('has-error');
        alertMessage.removeClass('hidden');
        usernameErrorMessage.removeClass('hidden');
        event.preventDefault();
    }
});

loginButton.click(function(event){
    if(password.val().length == 0){
        passwordInput.addClass('has-error');
        alertMessage.removeClass('hidden');
        passwordErrorMessage.removeClass('hidden');
        event.preventDefault();
    }
});

usernameInput.on('keyup',function(e){
    usernameErrorMessage.addClass('hidden');
    usernameInput.removeClass('has-error');
    alertMessage.addClass('hidden');
});

passwordInput.on('keyup',function (e) {
    passwordErrorMessage.addClass('hidden');
    passwordInput.removeClass('has-error');
    alertMessage.addClass('hidden');
});
/**
 * Created by Nick on 1/17/17.
 */
var today = new Date();
var hourNow = today.getHours();
var greeting;

if (hourNow > 18) {
    greeting = "Good evening!";
}else if (hourNow >12) {
    greeting = "Good Afternoon!";
}else if (hourNow > 0) {
    greeting = "Good Morning!";
} else {
    greeting = "Welcome!"
}

document.getElementById("greeting").innerHTML = greeting;

/**
 * Created by Nick on 1/18/17.
 */
var today = new Date();
var date =  today.getDate();
var year = today.getFullYear();
var month = today.getMonth() + 1;

if (month == 1){
    month = "January"
}

switch (month){
    case 1:
        month = "January";
        break;
    case 2:
        month = "February";
        break;
    case 3:
        month = "March";
        break;
    case 4:
        month = "April";
        break;
    case 5:
        month = "May";
        break;
    case 6:
        month = "June";
        break;
    case 7:
        month = "July";
        break;
    case 8:
        month = "August";
        break;
    case 9:
        month = "September";
        break;
    case 10:
        month = "October";
        break;
    case 11:
        month = "November";
        break;
    case 12:
        month = "December";

}

var todaysDate = month + " " + date + ", " + year;
document.getElementById("todaysDate").innerHTML = todaysDate;


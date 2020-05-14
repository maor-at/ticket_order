$(function(){
var $login = $('#login');
var $register = $('#register');

welcomeSign();


$("body").delegate("#register", "click", function(){
document.getElementById("register_login_btn").className = "buttonMain";
$(document.getElementById("login")).replaceWith('<input type="password" placeholder="סיסמא" id="password"/>');
$(this).replaceWith('<input type="text" placeholder="שם משתמש" id="userName"/>');
});


$("#logOut").delegate(".buttonMain", "click", function(){
releaseTickets();
localStorage.clear();
$(this).replaceWith('<button type = "button" id = "register">  הרשמה </button><button type = "button" id = "login" >  התחברות </button>');
 $('#shoppingCart').remove();
$(document.getElementById("form")).text( "שלום אורח,התחבר\\הירשם לאתר");
});



$("#shoppingCart").delegate("#img", "click", function(){
        window.location.href = "shoppingCart.html";

});
$("body").delegate(".homePage", "click", function(){

        window.location.href = "mainPage.html";

});


$("body").delegate("#login", "click", function(){
document.getElementById("register_login_btn").className = "buttonMain";
$(document.getElementById("register")).replaceWith('<input type="text" placeholder="שם משתמש" id="userName"/>');
$(this).replaceWith('<input type="password" placeholder="סיסמא" id="password"/>');
document.getElementById("register_login_btn").innerHTML = "התחברות";
});

$("body").delegate("#register_login_btn", "click", function(){
var $userName = $('#userName');
var $password = $('#password');

if(validateForm($userName.val(),$password.val()))
{

var usersDto = {
    "_userName": $userName.val(),
    "_password": $password.val(),
    "type": 1
};


//need to register new user - send a  Json
if(this.innerText =="הרשמה")
{
$.ajax({
contentType: "application/json",
type: 'POST',
url: 'http://localhost:8080/TicketStore/v1/a/registration',
data: JSON.stringify(usersDto),
success: function() {
login_register_success($userName.val());
},
error: function() {
alert('שם המשתמש כבר קיים במערכת');
}
});
}



else // it's a login
{
				$.getJSON('http://localhost:8080/TicketStore/v1/a/login', {
                  userName: $userName.val(),
                  password:$password.val(),
                  type : 1,
                }, function(result){
                login_register_success(result._userName);
                })
                .fail(function() { alert("שם המשתמש או הסיסמא אינם נכונים "); });
}

}
});




$('.show-container').on('click',function(event) {
    event.stopPropagation();
    event.stopImmediatePropagation();
window.location.href =  $(this)[0].children[0].children[1].innerText+".html";
});


$("body").delegate("#search", "click", function(){
var $searchByCity = $(document.getElementById("searchByCity"));//$('#searchByCity');
var $searchByDate = $(document.getElementById("searchByDate"));//$('#searchByDate');
var $searchByString = $(document.getElementById("searchByString"));

    if( (emptyForm($searchByCity.val()) ) && (emptyForm($searchByDate.val()) ) && ( emptyForm($searchByString.val()) ))
    {
     alert("can't search - your fields are empty");
    }
    //always gives priority to String search
    else if( !(emptyForm($searchByString.val())) )
    {
        window.location.href = "showSearch.html"+"?showName="+$searchByString.val();
    }
    else if(!(emptyForm($searchByCity.val())))
    {
        window.location.href = "showSearch.html"+"?location="+$searchByCity.val();
    }
    //search by Date
    else
    {
            var clearDate = $searchByDate.val().split(/[/.-]+/);
            if(parseInt(clearDate[0])>31)
            {
                //the format is YYYY/MM/DD
                $searchByDate = clearDate.join("-");
            }
            else if(parseInt(clearDate[2])>31)
            {
                //the format is dd/mm/yyyy
                $searchByDate = clearDate.reverse().join("-");
             }
             window.location.href = "showSearch.html"+"?showDate="+$searchByDate;
    }
});

function login_register_success(userName)
{
                  localStorage.clear();
                  localStorage.setItem('userName',userName);
                  $('#userName').remove();
                  $('#password').remove();
                  $('#register_login_btn').remove();
                  $(document.getElementById("form")).text(userName+" שלום ");
                  $("#shoppingCart").append('<div class="img-wrap"><img src="images/shopping_cart.jpg"  id="img" width="50" height="50"></div><div>');
                  $("#logOut").append('<button type = "button" class ="buttonMain"> התנתק </button>')

}

function releaseTickets()
{
var refreshTickets = JSON.parse(localStorage.getItem('refreshPageLtTickets'));

var cartTickets = JSON.parse(localStorage.getItem('lsTickets'));
if(refreshTickets != null && refreshTickets.length != 0)
    {
        $.each(refreshTickets,function(index,item){
        updateTicket(0,item.theaterName,item.date,item.row,item.column);
        });
    }
if(cartTickets != null && cartTickets.length != 0)
{
    $.each(cartTickets,function(index,item){
    updateTicket(0,item.theaterName,item.date,item.row,item.column);
    });
}

}

function updateTicket(status,nameOfTheater,showDate,row,column)
{

$.ajax({
contentType: "application/json",
dataType: 'jsonp',
type: 'POST',
url: 'http://localhost:8080/TicketStore/v1/s/updateSeat',
data: {"taken":status,"theaterName":nameOfTheater,"date":showDate,"row":row,"column":column},
});
}


});

function welcomeSign()
{
    var userName = localStorage.getItem("userName");
    if(userName != null)
    {
        $(document.getElementById("register")).remove();
        $(document.getElementById("login")).remove();
        $(document.getElementById("form")).text(userName+" שלום ");
        $("#shoppingCart").append('<div class="img-wrap"><img src="images/shopping_cart.jpg"  id="img" width="50" height="50"></div><div>');
        $("#logOut").append('<button type = "button" class ="buttonMain"> התנתק </button>')
    }
}


function validateForm(userName,password){
    if((isEmpty(userName,"UserName")) || (isEmpty(password,"Password")))
        return false;
    return true;
}

function isEmpty(elemValue, field){
    if((elemValue == "") || (elemValue == null)){
        alert("you can not have "+field+" field empty");
        return true;
    }else{
        return false;
    }
}

function emptyForm(elemValue){
    if((elemValue == "") || (elemValue == null))
        return true;
   return false;
}
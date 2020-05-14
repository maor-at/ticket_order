$(document).ready(function() {

$('.button').on('click',function() {
var $userName = $('#userName');
var $password = $('#password');

if(validateForm($userName.val(),$password.val()))
{

				$.getJSON('http://localhost:8080/TicketStore/v1/a/login', {
                  userName: $userName.val(),
                  password: $password.val(),
                  type : 2,
                }, function(result){
                window.location.href = "addNewShow.html";
                })
                .fail(function() { alert("שם המשתמש או הסיסמא אינם נכונים "); });

}

});



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
});
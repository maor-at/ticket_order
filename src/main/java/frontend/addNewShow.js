$(document).ready(function() {
var $showName = $('#showName');
var $theaterName = $('#theaterName');
var $location = $('#location');
var $description = $('#description');
var $showHour = $('#showHour');
var $showType = $('#showTypeInput');





function calcDate()
{
            var date = $('#date').val().split(/[/.-]+/);
            if(parseInt(date[0])>31)
            {
                //the format is YYYY/MM/DD
                return(date.join("-"));
            }
            else if(parseInt(date[2])>31)
            {
                //the format is dd/mm/yyyy
                return(date.reverse().join("-"));
             }
}

function showTypeFromStringToInt(showType)
{
switch(showType)
{
case "מופעי בידור וסטנדאפ":
                    showType = 1;
                    break;
        case "הצגות ילדים":
                         showType = 2;
                         break;
        case "מוזיקה והופעות חיות":
                         showType = 3;
                         break;
        case "פסטיבלים":
                         showType = 4;
                         break;

        case "הרצאות":
                         showType = 5;
                         break;

        case "תערוכות":
                         showType = 6;
                         break;
}
return showType;
}


$('#add-show').on('click',function() {
var seatMap = " ";

if(allAreFilled())
{
    if($('#seatType').val()== "בחירת מושבים" && document.getElementById("seatMap") != null)
        seatMap = document.getElementById("seatMap").value;
//($('#seatType').val()== "אוטומטי") ? seatMap = " ":seatMap = document.getElementById("seatMap").value

var newShow = {

    "showName": $showName.val(),
    "showType": showTypeFromStringToInt($showType.val()),
    "date": calcDate(),
    "theaterName": (document.getElementById("theaterName") != null)? document.getElementById("theaterName").value :$(theaterListInput)[0].value.split(",")[0],
    "location": (document.getElementById("location") != null)? document.getElementById("location").value :$(theaterListInput)[0].value.split(",")[1],//$location.val(),
    "description":$description.val(),
    "seatMap":seatMap,
    "showHour":$showHour.val(),

};

$.ajax({
contentType: "application/json",
type: '',
url: 'http://localhost:8080/TicketStore/v1/t/NewShow',
data: JSON.stringify(newShow),
success: function() {
alert('ההופעה נוספה בהצלחה!');
},
error: function() {
alert('אין אפשרות להכניס הופעה זו למערכת');
}
});
}

});

$('.theaterName').on('click',function() {
 //$('input[type="checkbox"]').not(this).prop('checked', false);
if($(this).val() == "theaterFromDb" && ($(this).is(':checked') == true))
{
				$.getJSON('http://localhost:8080/TicketStore/v1/r/theaterList',  function(result){
                var content = '<p>תיאטרון : <input list="theaterList" id=theaterListInput name ="theaterList" required><datalist id="theaterList">';
                 $.each(result,function(index,item){
                 content = content + '<option value="'+item.theaterName +','+item.location+'">';
               });
               content = content + '</datalist></p>';
               $('#location').parents()[0].remove();
                $('#seat').before(content);
                });
}
else if ( $(this).val() == "newTheater" && ($(this).is(':checked') == true))
{
    $($(this).parents()[0]).replaceWith('<p>שם התיאטרון : <input type="text" id ="theaterName" required></p>');
}

});


$("#seatType").on('input', function () {

        if(this.value == "בחירת מושבים")
        {
             $( "#add-show" ).before( '<p>מפת כיסאות : <input type="text" id ="seatMap" required></p> ' );
        }

    });



  function allAreFilled ()
  {
         let allAreFilled = true;
          document.getElementById("showRequest").querySelectorAll("[required]").forEach(function(i) {
            if (!allAreFilled) return;
            if (!i.value) allAreFilled = false;
            if (i.type === "radio") {
              let radioValueCheck = false;
              document.getElementById("myForm").querySelectorAll(`[name=${i.name}]`).forEach(function(r) {
                if (r.checked) radioValueCheck = true;
              })
              allAreFilled = radioValueCheck;
            }
          })
          if (!allAreFilled) {
            alert('Fill all the fields');
          }
  return allAreFilled;
  }


$("body").delegate("#theaterListInput", "input", function(){

    var location = $(this)[0].value.split(",")[1];
    var theaterName = $(this)[0].value.split(",")[0];

     				$.getJSON('http://localhost:8080/TicketStore/v1/r/getTheater', {
                       theaterName: theaterName,
                       location : location,
                     }, function(result){
                        if(result.seatMap != " ")
                        {
                            $("#seatType").val("בחירת מושבים");
                            $("#seatType [value='אוטומטי']").prop("disabled", true);
                        }
                        else
                        {
                            $("#seatType").val("אוטומטי");
                            $("#seatType [value='בחירת מושבים']").prop("disabled", true);
                        }
                     });

      });







});


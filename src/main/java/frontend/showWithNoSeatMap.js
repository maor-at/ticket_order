var tickets = [];
//document.getElementsByClassName("container")[0].childElementCount -2;//no included the summary(only rows child)
 $(document).ready(function() {
  //localStorage.removeItem("tickets");
var NumberOfRows =   $(".container")[0].childElementCount -2;
	$theaterName = $('#theaterName'); //name of theater
	var date = $('#date').text(); //date of the show
    $description = $('#description'); //description of the show
    $showName = $('#showName'); //the name of the show
    $location = $('#city'); //location of the show
    $showHour = $('#showTime');
    //initTicketsList();
    calcDate();
    showImage($showName.text());
    getShowDescription($showName.text());


function showImage(showName){
var img = document.createElement("img");
img.src = "images/"+showName+".jpg";
img.width = 350;
img.height = 300;
var src = document.getElementById("showImage");
src.appendChild(img);
}

//get request for show description
function getShowDescription(showName)
{
$.getJSON('http://localhost:8080/TicketStore/v1/t/showDescription', {
                  showName:showName,
                }, function(result){
                  $description.text(result.description);
                          });
}


$('.ticketInput').on('change keyup',function(){

$(this).parent().find("span").text(parseFloat($(this).parent().find("a").text()) * $(this)[0].value);
$(".countTickets").text("סה''כ: "+calcNumberOfTicketsSelected()+" כרטיסים");
$("#total").text(updateTotalPrice() + "₪");
});


function calcNumberOfTicketsSelected()
{
    var totalTickets = 0;
    for(var i = 0 ; i < NumberOfRows; i++)
    {
        totalTickets += parseInt($(".container")[0].children[i].children[1].value);
    }
    return totalTickets;
}

function updateTotalPrice()
{
    var total = 0 ;
    var rowsContainer = $(".container")[0];

    for ( var i = 0 ; i < NumberOfRows; i++)
    {
        rowTotalPrice = rowsContainer.children[i].children[0].innerText;
        if (rowTotalPrice == "--")
        {
            rowTotalPrice = 0;
        }
            total = total + parseFloat(rowTotalPrice);
    }
    return total;
}


$('#purchase').on('click',function()
{

for(var i = 0; i < NumberOfRows; i++)
{
    var NumberOfTickets = parseInt($(".container")[0].children[i].children[1].value);
    for( var j = 0 ; j < NumberOfTickets ; j++)
    {
            var ticketDto = {
                _userName: localStorage.getItem('userName'),
                showName: $showName.text(),
                row: 0,
                column: 0,
                date:date,
                theaterName: $theaterName.text(),
                price: parseInt($(".container")[0].children[i].children[2].innerText),
                showHour: $showHour.text(),
                location:$location.text(),
            };
            tickets.push(ticketDto);
    }
                    $(".container")[0].children[i].children[0].innerText= "--";
                    $(".container")[0].children[i].children[1].value = 0;

}
    updateTicketsList();
    localStorage.setItem("tickets",JSON.stringify(tickets));
    $(".countTickets").text("סה''כ: "+calcNumberOfTicketsSelected()+" כרטיסים")
    $("#total").text(updateTotalPrice() + "₪");
    alert("הכרטיסים נוספו בהצלחה");
    });



function updateTicketsList()
{
   var listOfTicketsInCart = JSON.parse(localStorage.getItem("tickets"));
    if(listOfTicketsInCart != null)
    {
        for ( var i = 0; i < tickets.length; i++)
            {
                var ticket = tickets[i];
                listOfTicketsInCart.push(ticket);
            }
        tickets = listOfTicketsInCart ;
    }
}



    function calcDate()
    {
                var cleanDate = date.split(/[/.-]+/);
                if(parseInt(cleanDate[0])>31)
                {
                    //the format is YYYY/MM/DD
                    date = cleanDate.join("-");
                }
                else if(parseInt(cleanDate[2])>31)
                {
                    //the format is dd/mm/yyyy
                    date = cleanDate.reverse().join("-");
                 }
    }

function initTicketsList()
{
 if(localStorage.getItem('tickets') != null)
    {
        tickets = localStorage.getItem('tickets');
        //if(itemIsExistsByShowName(JSON.parse(localStorage.getItem('lsTickets'))))
          //  lsTickets = localStorage.getItem('lsTickets');
         //else
           // lsTickets = [];
    }
 else if(tickets == "[]")
         tickets = [];
 else
   tickets = [];

}

});
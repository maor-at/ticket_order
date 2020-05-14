$(document).ready(function() {
var tickets = JSON.parse(localStorage.getItem('lsTickets'));
var ticketsWithNoSeat = JSON.parse(localStorage.getItem('tickets'));
var rowId = 0;
var ticketsDtoList = [];




if(tickets != null )
{
    shoppingCartInit(tickets);
}

if(ticketsWithNoSeat != null )
{
    shoppingCartInit(ticketsWithNoSeat);
}
$('.Table').append('<div class="RowCell"><span>סהכ:</span><button type = "button" id = "purchase" > סיים הזמנה </button></div>');
$('.RowCell')[0].children[0].innerText =("₪ "+calculateSum()+ " :סה''כ ");


function shoppingCartInit(tickets)
{
$.each(tickets,function(index,item)
{
var ticketDto = {
    "_userName": localStorage.getItem('userName'),
    "showName": item.showName,
    "row": item.row,
    "column":item.column,
    "date":item.date,
    "theaterName": item.theaterName,
    "price": item.price,
    "showHour":item.showHour,
};
ticketsDtoList.push(ticketDto);
  var content = '<div class="Row" id= "'+rowId+'"><div class = "buttonCell"><button class = "removeFromCart"> הסר מהעגלה </button></div>';
      content += '<div class="Cell"><p>'+item.price+'</p></div>';
      content +=  '<div class="Cell"><p>'+"R"+item.row +"_S"+ item.column+'</p></div>';
      content +=  '<div class="Cell"><p>'+item.showHour+'</p></div>';
      content +=  '<div class="Cell"><p>'+calcDate(item.date)+'</p></div>';
      content +=  '<div class="Cell"><p>'+item.location+'</p></div>';
      content +=  '<div class="Cell"><p>'+item.theaterName+'</p></div>';
      content += '<div class="Cell"><p>'+item.showName+'</p></div></div>';
  $('.Table').append(content);
  rowId++;
});

}


function calcDate(date)
{
            var z = date.split(/[/.-]+/);
            if(parseInt(z[0])>31)
            {
                //the format is YYYY/MM/DD
                date = z.reverse().join(".");
            }
            else if(parseInt(z[2])>31)
            {
                //the format is dd/mm/yyyy
                date = z.join(".");
             }
             return date;
}



function calculateSum()
{
var sum = 0;
var table = document.getElementsByClassName("Table")[0];
for( var i = 2 ;i<table.children.length-1; i++)//first two rows and the last row are not tickets
{
    sum +=parseInt(table.children[i].children[1].innerText);//child.price;
}
	return sum;
}


$("body").delegate(".removeFromCart", "click", function(event){
            var row = $(this).parents()[1];
            var list = tickets;
            var listName = "lsTickets";
            event.stopPropagation();
            event.stopImmediatePropagation();

            if(row.children[2].innerText == "R0_S0")
            {
                list = ticketsWithNoSeat;
                listName = "tickets";
            }
            var object = list[row.id];
            // Get index of object
            var index = list.indexOf(object);
            // Splice the array at the index of your object
            list.splice(index, 1);
            // Save back to localStorage
            localStorage.setItem(listName, JSON.stringify(list));
            //remove also from the general list that send to DB
                object = ticketsDtoList[row.id];
                index = ticketsDtoList.indexOf(object);
                ticketsDtoList.splice(index, 1);
            row.remove();
            var total = calculateSum();
            if( total == 0 )
                $('.RowCell').children()[1].remove();

           $('.RowCell').find("span").text("₪ "+total+ " :סה''כ ");

});




$("div.Table").delegate("#purchase", "click", function(event){
            event.stopPropagation();
            event.stopImmediatePropagation();
$.ajax({
contentType: "application/json",
type: 'POST',
url: 'http://localhost:8080/TicketStore/v1/b/purchase',
data:JSON.stringify(ticketsDtoList),
success: function() {
alert("תודה שרכשת ב - ticket4u");
localStorage.removeItem('tickets');
localStorage.removeItem('lsTickets');
document.location = 'mainPage.html'
},
error: function() {
alert('error ticket query');
}
});
});

});





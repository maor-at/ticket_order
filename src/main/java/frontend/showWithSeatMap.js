 var price = 100; //price
 var seatMap = [""];
 var ticketList = [];
 var sc;
 var unavailableSeats;
 var lsTickets;
 var refreshPageLtTickets;
 var indexTicket = {index:0};


$(document).ready(function() {
	var $cart = $('#selected-seats'), //Sitting Area
	$counter = $('#counter'), //Votes
	$total = $('#total'); //Total money
	$theaterName = $('#theaterName'); //name of theater
	var date = $('#date').text();
    $description = $('#description'); //description of the show
    $showName = $('#showName'); //the name of the show
    $location = $('#city');
    var $tickets = $('#ticekts');
    $showHour = $('#showTime');

    calcDate();
    getSeatMap($theaterName.text());
    showImage($showName.text());
    getShowDescription($showName.text());
        initLsUpdate();




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
                  retries     : 3,
                }, function(result){
                  $description.text(result.description);
                          });
}

//get request for each seat if is taken or not
function getUnavailableSeats()
{
    $.getJSON('http://localhost:8080/TicketStore/v1/s/unavailableSeat', {
                      theaterName:$theaterName.text(),
                      date:date,
                    }, function(result){
                    unavailableSeats = result;
                    $.each(unavailableSeats,function(index,item){
                        var exists;
                     if(refreshPageLtTickets!= null){

                         if(isJson(refreshPageLtTickets))
                             exists = itemIsExists(parseInt(item.substring(0,item.indexOf("_"))),parseInt(item.substring(item.indexOf("_")+1)),refreshPageLtTickets,indexTicket);
                         else
                             exists = itemIsExists(parseInt(item.substring(0,item.indexOf("_"))),parseInt(item.substring(item.indexOf("_")+1)),JSON.parse(refreshPageLtTickets),indexTicket);
                        }
                     if(lsTickets !=null)
                     {
                        if( isJson(lsTickets))
                             exists |= itemIsExists(parseInt(item.substring(0,item.indexOf("_"))),parseInt(item.substring(item.indexOf("_")+1)),lsTickets,indexTicket);
                        else
                            exists |= itemIsExists(parseInt(item.substring(0,item.indexOf("_"))),parseInt(item.substring(item.indexOf("_")+1)),JSON.parse(lsTickets),indexTicket);
                     }
                     if(!exists)
                        sc.get(item).status('unavailable');
                        });;
                    });
}

//get request for seat Map
function getSeatMap(theaterName)
{
$.getJSON('http://localhost:8080/TicketStore/v1/r/seatMap', {
                  theaterName:theaterName,
                  location:$location.text(),
                  retries     : 3,
                }, function(result){
                    seatMap = deSerializeSeatMap(result.seatMap);
                   $('.seatCharts-row').remove();
                      $('.seatCharts-legendItem').remove();
                      $('#seat-map,#seat-map *').unbind().removeData();
                      $('#seat-map').attr('aria-activedescendant', null);

                       sc = $('#seat-map').seatCharts({
                      		        map:seatMap,
                              		naming : {
                              			top : false,
                              			getLabel : function (character, row, column) {
                              				return column;
                              			}
                              		},
                              		legend : { //Definition legend
                              			node : $('#legend'),
                              			items : [
                              				[ 'a', 'available',   ' פנויים' ],
                              				[ 'a', 'unavailable', ' תפוסים']
                              			]
                              		},
                              		click: function () { //Click event
                              			if (this.status() == 'available') { //optional seat
                              				$('<li>R'+(this.settings.row+1)+' S'+this.settings.label+'</li>')
                              					.attr('id', 'cart-item-'+this.settings.id)
                              					.data('seatId', this.settings.id)
                              					.appendTo($cart);
                              				$counter.text(sc.find('selected').length+1);
                              				var seatNumber = this.settings.id;
                              				updateSeat(2,$theaterName.text(),date,parseInt(seatNumber.substring(0,seatNumber.indexOf("_"))),parseInt(seatNumber.substring(seatNumber.indexOf("_")+1)));
                              				if(!isJson(refreshPageLtTickets))
                              				    refreshPageLtTickets = JSON.parse(refreshPageLtTickets);
                              				$total.text(parseInt(recalculateTotal(sc)+price)+ "₪ ");
                                            updateTicketListLs(refreshPageLtTickets,"refreshPageLtTickets");
                                            localStorage.setItem("refreshPageLtTickets",JSON.stringify(refreshPageLtTickets));
                              				return 'selected';
                              			} else if (this.status() == 'selected') {
                              					var seatNumber = this.settings.id;
                                                var row = seatNumber.substring(0,seatNumber.indexOf("_"));
                                                var column = seatNumber.substring(seatNumber.indexOf("_")+1);
                                                updateSeat(0,$theaterName.text(),date,parseInt(seatNumber.substring(0,seatNumber.indexOf("_"))),parseInt(seatNumber.substring(seatNumber.indexOf("_")+1)));

                                                clearRefreshPageTickets(seatNumber);
                                                if(!isJson(lsTickets))
                                                    lsTickets = JSON.parse(lsTickets);
                              					if(refreshPageLtTickets == null)
                              					{
                              					  //want to update the lsTicket
                              					  if(!isJson(lsTickets))
                              					  {
                              					    lsTicket = JSON.parse(lsTickets);
                              					  }
                                                    itemIsExists(parseInt(row),parseInt(column),lsTickets,indexTicket);//return the index of the wanted seat in refresh list
                                                  removeTicketFromRefreshedTicketList(lsTickets,indexTicket,"lsTickets");
                              					}
                              					else
                              					{
                              					   if(!isJson(refreshPageLtTickets))
                              					        refreshPageLtTickets = JSON.parse(refreshPageLtTickets);

                              					    if(itemIsExists(parseInt(row),parseInt(column),refreshPageLtTickets,indexTicket))//return the index of the wanted seat in refresh list
                              					    {
                              					        //want to update the refresh list
                                                        removeTicketFromRefreshedTicketList(refreshPageLtTickets,indexTicket,"refreshPageLtTickets");
                              					    }
                              					    else
                              					    {
                              					        itemIsExists(parseInt(row),parseInt(column),lsTickets,indexTicket);//return the index of the wanted seat in refresh list
                                                        removeTicketFromRefreshedTicketList(lsTickets,indexTicket,"lsTickets");
                              					    }
                              					}
                              					return 'available';
                              			} else if (this.status() == 'unavailable') { //sold
                              				return 'unavailable';
                              			} else {
                              				return this.style();
                              			}
                              		}
                              	});
                                  //get the sold seats
                                    setSeatMapAfterRefresh();
                                    setInterval(function(){
                                    getUnavailableSeats()},1000);
                });
}

//sum total money
function recalculateTotal(sc) {
	var total = 0;
	sc.find('selected').each(function () {
		total += price;
	});
	return total;
}

function deSerializeSeatMap(map)
{
    var seatM;
    var seatMapRows=[];
    var j = 0;
    var index = 0;
    for( var i=0; i<map.length;i++)
    {
        if( map.charAt(i) =='/')
        {
           seatMapRows[j]=map.slice(index,i);
            i=i+2;
            index = i;
            j++;
        }
    }
return seatMapRows;
}


function strcmp(str1, str2)
{
return((str1 == str2) ? 0 :((str1 > str2) ? 1 : -1));
}



$('#addToCart').on('click',function()
{
    if(refreshPageLtTickets.length == 0)
    {
        alert("לא נבחרו כרטיסים להוסיף לעגלה");
    }
    else if(lsTickets.length != 0 || (lsTickets.length == 0 && refreshPageLtTickets.length != 0))
        {

            if(!isJson(lsTickets))
                lsTickets = JSON.parse(lsTickets);

            updateTicketListLs(lsTickets,"lsTickets");
            updateLsTicket();//in order to get the tickets from other shows as well
            localStorage.setItem("lsTickets",JSON.stringify(lsTickets));
            localStorage.removeItem("refreshPageLtTickets");
            refreshPageLtTickets = [];
            alert("הכרטיסים נוספו בהצלחה");
        }
});

function updateLsTicket()
{
   var listOfTicketsInCart = JSON.parse(localStorage.getItem("lsTickets"));
    if(listOfTicketsInCart != null)
    {
        for ( var i = 0; i < lsTickets.length; i++)
            {
                var ticket = lsTickets[i];
                if(!itemIsExists(parseInt(ticket.row),parseInt(ticket.column),listOfTicketsInCart,indexTicket))
                    listOfTicketsInCart.push(ticket);
            }
        lsTickets = listOfTicketsInCart ;
    }
}

function setSeatMapAfterRefresh()
{

    if(isJson(lsTickets))
    {
        updateMap(lsTickets);
    }
    else
    {
        updateMap(JSON.parse(lsTickets));
    }
    if(isJson(refreshPageLtTickets))
    {
        updateMap(refreshPageLtTickets);
    }
    else
    {
        updateMap(JSON.parse(refreshPageLtTickets));
    }


}

function updateTicketListLs(ticketlistLs,listName)
{
    for( var i = 0; i<$counter.text();i++)
    {
        var seatNumber = $cart[0].children[i].innerText.split(" ");
        var row = seatNumber[0].substring(1);
        var column = seatNumber[1].substring(1,seatNumber[0].length+1);
        var exists = false;
        if( listName == "refreshPageLtTickets")
        {
            if( lsTickets.length != 0 && ticketlistLs.length == 0)
            {
                if(isJson(lsTickets))//added
                    exists = !(itemIsExists(parseInt(row),parseInt(column),lsTickets,indexTicket))
                else
                    exists = !(itemIsExists(parseInt(row),parseInt(column),JSON.parse(lsTickets),indexTicket))
            }
            else
            {
                    if(isJson(lsTickets))
                         exists = ( !(itemIsExists(parseInt(row),parseInt(column),refreshPageLtTickets,indexTicket)) && !(itemIsExists(parseInt(row),parseInt(column),lsTickets,indexTicket)))
                    else
                    exists = ( !(itemIsExists(parseInt(row),parseInt(column),JSON.parse(refreshPageLtTickets),indexTicket)) && !(itemIsExists(parseInt(row),parseInt(column),JSON.parse(lsTickets),indexTicket)) )
            }
        }
        else
        {
            exists =  (!(itemIsExists(parseInt(row),parseInt(column),lsTickets,indexTicket)));
        }
        if(exists)
        {
        var ticketDto = {
            _userName: localStorage.getItem('userName'),
            showName: $showName.text(),
            row: row,
            column:column,
            date:date,
            theaterName: $theaterName.text(),
            price: price,
            location:$location.text(),
            showHour: $showHour.text(),
        };
        ticketlistLs.push(ticketDto);
         }
       }

}


function updateMap(list)
{
            $.each(list,function(index,item)
            {
            if(item.showName == $('#showName').text() )
               {
                $('<li>R'+(sc.seats[item.row+"_"+item.column].settings.row+1)+' S'+sc.seats[item.row+"_"+item.column].settings.label+'</li>')
                .attr('id', 'cart-item-'+sc.seats[item.row+"_"+item.column].settings.id)
                .data('seatId',sc.seats[item.row+"_"+item.column].settings.id).appendTo($cart);
                 sc.seats[item.row+"_"+item.column].status( 'selected');
                $counter.text(sc.find('selected').length);

            $total.text(recalculateTotal(sc) + " ₪");
        }
        });
}


function itemIsExists(row,column,list,ticketIndex)
{
    var exists = false;
    if( list != null)
    {
    var d1 = new Date(date);
         $.each(list,function(index,item)
                {
                    var d2 = new Date(item.date)
                    if(parseInt(item.row) == row  &&
                       parseInt(item.column) == column &&
                       strcmp(item.showName, $showName.text() ) == 0 &&
                       d1.getTime() == d2.getTime() &&
                       strcmp(item.location, $location.text() ) == 0 &&
                       strcmp(item.theaterName, $theaterName.text() ) == 0  )
                        {
                            exists = true;
                            if(index != null){
                            ticketIndex.index = index;}
                            return false;//break the $each loop
                        }
                });
    }
    return exists;
}
function removeTicketFromRefreshedTicketList(list,index,listName)
{
            var object = list[index.index];
            // Get index of object
            var index1 = list.indexOf(object);
            // Splice the array at the index of your object
            list.splice(index1, 1);
            // Save back to localStorage
            localStorage.setItem(listName,JSON.stringify(list));
            listName == "refreshPageLtTickets"? refreshPageLtTickets = list: lsTickets = list;
}

function clearRefreshPageTickets(seatNumber)
{
            //Update Number
            $counter.text(sc.find('selected').length-1);
            //update totalnum
            var total = recalculateTotal(sc)-price;
            if($counter.text() == 0)
                $total.text(" 0 ₪");
             else
                   $total.text(total+ "₪ ");
            //Delete reservation
            $('#cart-item-'+seatNumber).remove();

}



function calcDate()
{
            var z = date.split(/[/.-]+/);
            if(parseInt(z[0])>31)
            {
                //the format is YYYY/MM/DD
                date = z.join("-");
            }
            else if(parseInt(z[2])>31)
            {
                //the format is dd/mm/yyyy
                date = z.reverse().join("-");
             }
}

function itemIsExistsByShowName (list,listName)
{
     var exists = false;
             $.each(list,function(index,item)
                    {
                        if(strcmp(item.showName, $showName.text() ) == 0)
                            {
                                exists = true;
                                return false;//break the $each loop
                            }
                    });
        return exists;
}

function initLsUpdate()
{

    if(localStorage.getItem('lsTickets') != null)
    {
        if(itemIsExistsByShowName(JSON.parse(localStorage.getItem('lsTickets'))))
            lsTickets = localStorage.getItem('lsTickets');
         else
            lsTickets = [];
    }
    else if(lsTickets == "[]")
            lsTickets = [];
    else
        lsTickets = [];

     if(localStorage.getItem('refreshPageLtTickets') != null)
        {
           if(itemIsExistsByShowName(JSON.parse(localStorage.getItem('refreshPageLtTickets'))))
                refreshPageLtTickets = localStorage.getItem('refreshPageLtTickets');
            else
                refreshPageLtTickets = [];
        }
      else if (refreshPageLtTickets == "[]")
                refreshPageLtTickets = [];
    else
        refreshPageLtTickets = [];
}



function isJson(item) {

return (typeof item !== "string");
}


function updateSeat(status,nameOfTheater,showDate,row,column)
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


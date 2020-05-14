$(document).ready(function() {
var query = new URLSearchParams(document.location.search); //the parameter from the query.
var queryParameterValue;  //the value of the parameter in the query


if( query.has("showType"))
{
 queryParameterValue = query.get("showType");
    $.getJSON('http://localhost:8080/TicketStore/v1/t/SearchByType', {
                      showType:queryParameterValue,
                    }, function(result){
                        if(result.length == 0)
                            window.location.href = "showNotFound.html";
                      $.each(result,function(index,item){
                       $("#searchContainer").append('<div class="row"><div class="column"><div class = "showTitle">'
                       +'<header class ="headerShow">'+item.showName+'</header></div> <div class="img-wrapper">'
                       +'<img src="images/'+item.showName+'.jpg" width="200" height="150"></div><div>'
                       +'<button class="btn">לחץ להזמנה</button></div><div class="description"><p>'+item.description+'</p></div></div></div>');
                              });
                    });
}
else if (query.has( "showName"))
{
 queryParameterValue = query.get("showName");
    $.getJSON('http://localhost:8080/TicketStore/v1/t/SearchByName', {
                      showName:queryParameterValue,
                    }, function(result){
                        if(result.length == 0)
                            window.location.href = "showNotFound.html";
                      $.each(result,function(index,item){
                       $("#searchContainer").append('<div class="row"><div class="column"><div class = "showTitle">'
                       +'<header class ="headerShow">'+item.showName+'</header></div> <div class="img-wrapper">'
                       +'<img src="images/'+item.showName+'.jpg" width="200" height="150"></div><div>'
                       +'<button class="btn">לחץ להזמנה</button></div><div class="description"><p>'+item.description+'</p></div></div></div>');
                              });
                    });

}
else if( query.has( "showDate"))
{
 queryParameterValue = query.get("showDate");
  $.getJSON('http://localhost:8080/TicketStore/v1/t/SearchByDate', {
                      showDate: queryParameterValue,
                    }, function(result){
                        if(result.length == 0)
                            window.location.href = "showNotFound.html";
                      $.each(result,function(index,item){
                       $("#searchContainer").append('<div class="row"><div class="column"><div class = "showTitle">'
                       +'<header class ="headerShow">'+item.showName+'</header></div> <div class="img-wrapper">'
                       +'<img src="images/'+item.showName+'.jpg" width="200" height="150"></div><div>'
                       +'<button class="btn">לחץ להזמנה</button></div><div class="description"><p>'+item.description+'</p></div></div></div>');
                              });
                    });
}
else //query == "location"
{
 queryParameterValue = query.get("location");
    $.getJSON('http://localhost:8080/TicketStore/v1/t/SearchByLocation', {
                      location:queryParameterValue,
                    }, function(result){
                        if(result.length == 0)
                             window.location.href = "showNotFound.html";
                      $.each(result,function(index,item){
                       $("#searchContainer").append('<div class="row"><div class="column"><div class = "showTitle">'
                       +'<header class ="headerShow">'+item.showName+'</header></div> <div class="img-wrapper">'
                       +'<img src="images/'+item.showName+'.jpg" width="200" height="150"></div><div>'
                       +'<button class="btn">לחץ להזמנה</button></div><div class="description"><p>'+item.description+'</p></div></div></div>');
                              });
                    });

}

//used the delegate because of not existing button element when page load
$("#searchContainer").delegate(".btn", "click", function(event){
    event.stopPropagation();
    event.stopImmediatePropagation();
        var showName =$(this).parents()[1].children[0].innerText;//to get the showName
        window.location.href = showName+".html";

});
});

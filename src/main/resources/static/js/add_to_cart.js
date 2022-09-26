$(document).ready(function(){
    $(".button1").on("click", function(e){
      e.preventDefault();
      var productId = $(this).val();
      addToCart(productId)
    });
});


function addToCart(id){
    quantity = $("#quantity" + id).val();

    url= contextPath + "cart/add/" + id + "/" + quantity;

    $.ajax({
        type: "POST",
        url: url,
        beforeSend : function(xhr){
            xhr.setRequestHeader(csrfHeaderName, csrfValue);
        }
    }).done(function(response){
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text(response);
        $("#myModal").modal('show');
    }).fail(function(){
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text("Error while adding.");
        $("#myModal").modal();
    });
}

$("#insertError").hide();
$("#insertSuccess").hide();
$(document).ready(function () {
    serverContext = "http://localhost:8080";

    getAllRenterVehicles();

    $("#addNewCar").on("click", function () {
        $("#modalInsertVehicle").modal("show");
    });

    $("#formAddNewCar").submit(function (event) {
        event.preventDefault();
        insertNewCar();
    })

    $("#addNewTruck").on("click",function(){
        $("#modalInsertTruck").modal("show");
    });

    $("#formAddNewTruck").on("submit",function(){
        event.preventDefault();
        insertNewTruck();
    });

    $("#selectTrailer").change(function () {
        var v = $( "#selectTrailer option:selected" ).text();
        if(v==="Yes"){
            $("#trailerInfo").removeClass("d-none");
        }else{
            $("#trailerInfo").addClass("d-none");
        }
    });

    $("#addNewBus").on("click",function(){
        $("#modalInsertBus").modal("show");
    });

    $("#formAddNewBus").on("submit", function(){
       event.preventDefault();
       insertNewBus();
    });
});


function insertNewBus(){
    endPoint = window.location + "/bus/new";
    formData = $("#formAddNewBus").serialize();
    $.post(endPoint, formData, function (data) {
        if(data=="true"){
            $("#insertError").hide();
            $("#insertSuccess").show();
            getAllRenterVehicles();
        }else{
            $("#insertError").show();
            $("#insertSuccess").hide();
        }

    });
    $('#modalInsertBus').modal('hide');
    $(".modal-body input").val("")
}


function insertNewCar(){
    endPoint = window.location + "/car/new";
    formData = $("#formAddNewCar").serialize();
    $.post(endPoint, formData, function (data) {
        if(data=="true"){
            $("#insertError").hide();
            $("#insertSuccess").show();
            getAllRenterVehicles();
        }else{
            $("#insertError").show();
            $("#insertSuccess").hide();
        }

    });
    $('#modalInsertVehicle').modal('hide');
    $(".modal-body input").val("")
}

function insertNewTruck(){
    endPoint = window.location + "/truck/new";
    formData = $("#formAddNewTruck").serialize();
    $.post(endPoint, formData, function (data) {
        if(data=="true"){
            $("#insertError").hide();
            $("#insertSuccess").show();
            getAllRenterVehicles();
        }else{
            $("#insertError").show();
            $("#insertSuccess").hide();
        }

    });
    $('#modalInsertTruck').modal('hide');
    $(".modal-body input").val("")
}


function getAllRenterVehicles() {
    $.ajax({
        async: false,
        type: "GET",
        url: serverContext + "/renter/vehicle/all",
        success: function (data) {
            updateTable(data);
        }
    });
}

function updateTable(data) {
    $("#vehicleTable tbody>tr").remove();
    $("#vehicleTable thead>tr").remove();
    $tr = $("<tr>").append(
        $("<th>").text("#"),
        $("<th>").text("Manufacturer"),
        $("<th>").text("Year"),
        $("<th>").text("Registration Plate"),
        $("<th>").text("Type"),
        $("<th>").text("Subtype"),
        $("<th>").text("Available")
    )
        .appendTo("#vehicleTable thead");
    $.each(data, function (i, item) {
        $tr = $("<tr>").append(
            $("<th>").text(i + 1),
            $("<td id='tableManufacturer'>").text(item.manufacturer),
            $("<td id='tableYear'>").text(item.year),
            $("<td id='tableRegistrationPlate'>").text(item.registrationPlate),
            $("<td id='tableType'>").text(item.vehicleType),
            $("<td id='tableSubtype'>").text(item.vehicleSubtype),
            $("<td id='tableAvailable'>").text(item.available)
        )
            .appendTo("#vehicleTable");
    });
}
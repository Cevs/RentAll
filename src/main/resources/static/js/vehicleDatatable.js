$("#insertError").hide();
$("#insertSuccess").hide();
$(document).ready(function () {
    getAllRenterVehicles();

    $("#addNewCar").on("click", function () {
        populateModalCar(null);
        $("#modalInsertCar").modal("show");
    });

    //Insert/Update car
    $("#formAddNewCar").submit(function (event) {
        event.preventDefault();
        btnId = $(document.activeElement).attr('id')
        if(btnId === "btnCarInsert"){
            insertNewCar();
        }else if(btnId === "btnCarUpdate"){
            updateCar();
        }//TODO delete option

    });

    $("#addNewTruck").on("click",function(){
        populateModalTruck(null);
        $("#modalInsertTruck").modal("show");
    });

    $("#formAddNewTruck").on("submit",function(){
        event.preventDefault();
        btnId = $(document.activeElement).attr('id')
        if(btnId === "btnTruckInsert"){
            insertNewTruck();
        }else if(btnId === "btnTruckUpdate"){
            updateTruck();
        }//TODO delete option
    });

    $("#addNewBus").on("click",function(){
        populateModalBus(null);
        $("#modalInsertBus").modal("show");
    });

    $("#formAddNewBus").on("submit", function(){
        event.preventDefault();
        btnId = $(document.activeElement).attr('id');
        if(btnId === "btnBusInsert"){
            insertNewBus();
        }else if (btnId === "btnBusUpdate"){
            updateBus();
        }//TODO delete option
    });

    $("#selectTrailer").change(function () {
        var v = $( "#selectTrailer option:selected" ).text();
        if(v==="Yes"){
            $("#trailerInfo").removeClass("d-none");
        }else{
            $("#trailerInfo").addClass("d-none");
        }
    });

    //Handle the event of changing a type of vehicles that will be shown in the table
    $("#vehicleTypeMenu .dropdown-menu a").click(function(){
        typeSelected = $(this).text();
        if(typeSelected ==="All"){
            getAllRenterVehicles();
        }else{
            getAllRenterVehiclesOfType(typeSelected);
        }
        $("#vehicleTypeMenu button").text(typeSelected);
    });


});


function updateTableView(){
    selectedType = $("#btnShowVehicleType").text();
    console.log("Selected type: "+selectedType);
    if(selectedType == "All"){
        getAllRenterVehicles();
    }else{
        getAllRenterVehiclesOfType(selectedType);
    }
}

function insertNewBus(){
    endPoint = window.location + "/bus/new";
    formData = $("#formAddNewBus").serialize();
    $.post(endPoint, formData, function (data) {
        if(data=="true"){
            $("#insertError").hide();
            $("#insertSuccess").show();
            updateTableView();
        }else{
            $("#insertError").show();
            $("#insertSuccess").hide();
        }

    });
    $('#modalInsertBus').modal('hide');
    $(".modal-body input").val("")
}

function updateBus(){
    endPoint = window.location + "/bus/update";
    formData = $("#formAddNewBus").serialize();
    $.ajax({
        url:endPoint,
        type:'PUT',
        data:formData,
        success:function (response) {
            if(response == "true") {
                updateTableView();
                $(".modal-body input").val("")
                $("#modalInsertBus").modal("hide");
            }else{
                alert("Failer");
                $(".modal-body input").val("")
                $("#modalInsertBus").modal("hide");
            }
        }
    });
}


function insertNewCar(){
    endPoint = window.location + "/car/new";
    formData = $("#formAddNewCar").serialize();
    $.post(endPoint, formData, function (data) {
        if(data=="true"){
            $("#insertError").hide();
            $("#insertSuccess").show();
            updateTableView();
        }else{
            $("#insertError").show();
            $("#insertSuccess").hide();
        }

    });
    $('#modalInsertCar').modal('hide');
    $(".modal-body input").val("")
}

function updateCar(){
    endPoint = window.location + "/car/update";
    formData = $("#formAddNewCar").serialize();
    $.ajax({
       url:endPoint,
       type:'PUT',
       data:formData,
       success:function (response) {
           if(response == "true") {
               updateTableView();
               $(".modal-body input").val("")
               $("#modalInsertCar").modal("hide");
           }else{
               alert("Failer");
               $(".modal-body input").val("")
               $("#modalInsertCar").modal("hide");
           }
       }
    });
}

function updateTruck(){
    endPoint = window.location + "/truck/update";
    formData = $("#formAddNewTruck").serialize();
    $.ajax({
        url:endPoint,
        type:'PUT',
        data:formData,
        success:function (response) {
            if(response == "true") {
                updateTableView();
                $(".modal-body input").val("")
                $("#modalInsertTruck").modal("hide");
            }else{
                alert("Failer");
                $(".modal-body input").val("")
                $("#modalInsertTruck").modal("hide");
            }
        }
    });
}

function insertNewTruck(){
    endPoint = window.location + "/truck/new";
    formData = $("#formAddNewTruck").serialize();
    $.post(endPoint, formData, function (data) {
        if(data=="true"){
            $("#insertError").hide();
            $("#insertSuccess").show();
            updateTableView();
        }else{
            $("#insertError").show();
            $("#insertSuccess").hide();
        }

    });
    $('#modalInsertTruck').modal('hide');
    $(".modal-body input").val("")
}


function getAllRenterVehicles() {
    endPoint = window.location + "/all";
    $.ajax({
        async: false,
        type: "GET",
        url: endPoint,
        success: function (data) {
            updateTableData(data);
        }
    });
}

function getAllRenterVehiclesOfType(typeSelected) {
    endPoint = window.location + "/"+typeSelected;
    $.ajax({
        async: false,
        type: "GET",
        url: endPoint,
        success: function (data) {
            updateTableData(data);
        }
    });
}

function updateTableData(data) {
    $("#vehicleTable tbody>tr").remove();
    $("#vehicleTable thead>tr").remove();
    $tr = $("<tr>").append(
        $("<th>").text("#"),
        $("<th >").text("Id"),
        $("<th>").text("Manufacturer"),
        $("<th>").text("Year"),
        $("<th>").text("Registration Plate"),
        $("<th>").text("Type"),
        $("<th>").text("Subtype"),
        $("<th>").text("Available"),
        $("<th>").text("")
    )
        .appendTo("#vehicleTable thead");
    $.each(data, function (i, item) {
        $tr = $("<tr>").append(
            $("<th>").text(i + 1),
            $("<td id='tableId'>").text(item.id),
            $("<td id='tableManufacturer'>").text(item.manufacturer),
            $("<td id='tableYear'>").text(item.year),
            $("<td id='tableRegistrationPlate'>").text(item.registrationPlate),
            $("<td id='tableType'>").text(item.vehicleType),
            $("<td id='tableSubtype'>").text(item.vehicleSubtype),
            $("<td id='tableAvailable'>").text(item.available),
            $("<td id='btnDeleteVehicle'>").html("<button class='btn btn-danger'><i class='far fa-trash-alt'></i></button>")
        )
            .appendTo("#vehicleTable");
    });

    $("#vehicleTable tbody>tr>td").on("click", function(){
        //vehicleType = $(this).closest('tr').find('td:eq(4)').text();
        //id = $(this).find("#tableId").text();
        parent = $(this).parent();
        if($(this).attr('id') === "btnDeleteVehicle"){
            id = parent.find("#tableId").text();
            deleteVehicle(id);
        }else{
            vehicleType = parent.closest('tr').find('td:eq(4)').text();
            id = parent.find("#tableId").text();

            if(vehicleType === "Car"){
                getCarData(id);
            }else if(vehicleType === "Bus"){
                getBusData(id);
            }else if(vehicleType === "Truck"){
                getTruckData(id);
            }else{
                console.log("Unknown vehicle type");
            }
        }
    });
}


function deleteVehicle(id){
    endPoint = window.location +"/"+id;
    $.ajax({
        async:false,
        type:"DELETE",
        url:endPoint,
        success:function (response) {
            if(response == "true"){
                updateTableView();
            }
            else{
                alert("Delete failed");
            }
        }
    });
}

function getCarData(id) {
    endPoint = window.location+/car/+id;
    $.ajax({
        async: false,
        type: "GET",
        url: endPoint,
        success: function (data) {
            populateModalCar(data);
            $('#modalInsertCar').modal("show");
        }
    });
}

function getTruckData(id){
    endPoint = window.location+"/truck/"+id;
    $.ajax({
        async:false,
        type: "GET",
        url:endPoint,
        success:function(data){
            populateModalTruck(data);
            $("#modalInsertTruck").modal("show");
        }
    });
}

function getBusData(id){
    endPoint = window.location + "/bus/"+id;
    $.ajax({
        async:false,
        type: "GET",
        url: endPoint,
        success:function (data) {
            populateModalBus(data);
            $("#modalInsertBus").modal("show");
        }
    });
}


function populateModalCar(data){
    if(data != null){
        $("#modalInsertCar #btnCarUpdate").show();
        $("#modalInsertCar #btnCarInsert").hide();
        $("#modalInsertCar .modal-body #carId").val(data.id);
        $("#modalInsertCar .modal-body #carAvailable").val(data.available+""); //Needs to be string
        $("#modalInsertCar .modal-body #carSubtype").val(data.vehicleSubtype);
        $("#modalInsertCar .modal-body #carManufacturer").val(data.manufacturer);
        $("#modalInsertCar .modal-body #carYear").val(data.year);
        $("#modalInsertCar .modal-body #carEngine").val(data.engine);
        $("#modalInsertCar .modal-body #carFuelTank").val(data.fuelTank);
        $("#modalInsertCar .modal-body #carFuelConsumption").val(data.fuelConsumption);
        $("#modalInsertCar .modal-body #carRegistrationPlate").val(data.registrationPlate);
        $("#modalInsertCar .modal-body #carMileage").val(data.mileage);
        $("#modalInsertCar .modal-body #carSpareTires").val(data.spareTires);
        $("#modalInsertCar .modal-body #carWeight").val(data.weight);
        $("#modalInsertCar .modal-body #carPayloadCapacity").val(data.payloadCapacity);
        $("#modalInsertCar .modal-body #carAdditionalEquipment").val(data.additionalEquipment);

        $("#modalInsertCar .modal-body #carNuberOfDoors").val(data.doors);
        $("#modalInsertCar .modal-body #carColor").val(data.color);
        $("#modalInsertCar .modal-body #carTrunkCapacity").val(data.trunkCapacity);
    }else{
        $("#modalInsertCar #btnCarInsert").show();
        $("#modalInsertCar #btnCarUpdate").hide();
        $("#modalInsertCar .modal-body #carId").val(-1);
        $("#modalInsertCar .modal-body #carAvailable").val("true"); //Needs to be string
        $("#modalInsertCar .modal-body #carSubtype").val("Caravan");
        $("#modalInsertCar .modal-body #carManufacturer").val("");
        $("#modalInsertCar .modal-body #carYear").val(0);
        $("#modalInsertCar .modal-body #carEngine").val("");
        $("#modalInsertCar .modal-body #carFuelTank").val(0);
        $("#modalInsertCar .modal-body #carFuelConsumption").val(0);
        $("#modalInsertCar .modal-body #carRegistrationPlate").val("");
        $("#modalInsertCar .modal-body #carMileage").val(0);
        $("#modalInsertCar .modal-body #carSpareTires").val(0);
        $("#modalInsertCar .modal-body #carWeight").val(0);
        $("#modalInsertCar .modal-body #carPayloadCapacity").val(0);
        $("#modalInsertCar .modal-body #carAdditionalEquipment").val("");

        $("#modalInsertCar .modal-body #carNuberOfDoors").val(0);
        $("#modalInsertCar .modal-body #carColor").val("");
        $("#modalInsertCar .modal-body #carTrunkCapacity").val(0);
    }

}

function populateModalTruck(data){
    if(data !=null){
        $("#modalInsertTruck #btnTruckUpdate").show();
        $("#modalInsertTruck #btnTruckInsert").hide();
        $("#modalInsertTruck .modal-body #truckId").val(data.id);
        $("#modalInsertTruck .modal-body #truckAvailable").val(data.available+""); //Needs to be string
        $("#modalInsertTruck .modal-body #truckSubtype").val(data.vehicleSubtype);
        $("#modalInsertTruck .modal-body #truckManufacturer").val(data.manufacturer);
        $("#modalInsertTruck .modal-body #truckYear").val(data.year);
        $("#modalInsertTruck .modal-body #truckEngine").val(data.engine);
        $("#modalInsertTruck .modal-body #truckFuelTank").val(data.fuelTank);
        $("#modalInsertTruck .modal-body #truckFuelConsumption").val(data.fuelConsumption);
        $("#modalInsertTruck .modal-body #truckRegistrationPlate").val(data.registrationPlate);
        $("#modalInsertTruck .modal-body #truckMileage").val(data.mileage);
        $("#modalInsertTruck .modal-body #truckSpareTires").val(data.spareTires);
        $("#modalInsertTruck .modal-body #truckWeight").val(data.weight);
        $("#modalInsertTruck .modal-body #truckPayloadCapacity").val(data.payloadCapacity);
        $("#modalInsertTruck .modal-body #truckAdditionalEquipment").val(data.additionalEquipment);

        $("#modalInsertTruck .modal-body #truckHeight").val(data.truckHeight);
        $("#modalInsertTruck .modal-body #selectTrailer").val(data.trailer+"");
        if(data.trailer === true){
            $("#modalInsertTruck .modal-body #trailerInfo").removeClass("d-none");
        }else{
            $("#modalInsertTruck .modal-body #trailerInfo").addClass("d-none");
        }
        $("#modalInsertTruck .modal-body #trailerLength").val(data.trailerLength);
        $("#modalInsertTruck .modal-body #trailerWidth").val(data.trailerWidth);
        $("#modalInsertTruck .modal-body #trailerHeight").val(data.trailerHeight);
        $("#modalInsertTruck .modal-body #truckFreightSpace").val(data.freightSpace);
    }else{
        $("#modalInsertTruck #btnTruckInsert").show();
        $("#modalInsertTruck #btnTruckUpdate").hide();
        $("#modalInsertTruck .modal-body #truckId").val(-1);
        $("#modalInsertTruck .modal-body #truckAvailable").val("true"); //Needs to be string
        $("#modalInsertTruck .modal-body #truckSubtype").val("Delivery");
        $("#modalInsertTruck .modal-body #truckManufacturer").val("");
        $("#modalInsertTruck .modal-body #truckYear").val(0);
        $("#modalInsertTruck .modal-body #truckEngine").val("");
        $("#modalInsertTruck .modal-body #truckFuelTank").val(0);
        $("#modalInsertTruck .modal-body #truckFuelConsumption").val(0);
        $("#modalInsertTruck .modal-body #truckRegistrationPlate").val("");
        $("#modalInsertTruck .modal-body #truckMileage").val(0);
        $("#modalInsertTruck .modal-body #truckSpareTires").val(0);
        $("#modalInsertTruck .modal-body #truckWeight").val(0);
        $("#modalInsertTruck .modal-body #truckPayloadCapacity").val(0);
        $("#modalInsertTruck .modal-body #truckAdditionalEquipment").val("");
        $("#modalInsertTruck .modal-body #trailerInfo").addClass("d-none");

        $("#modalInsertTruck .modal-body #truckHeight").val(0.00);
        $("#modalInsertTruck .modal-body #selectTrailer").val("false");
        $("#modalInsertTruck .modal-body #trailerLength").val(0.00);
        $("#modalInsertTruck .modal-body #trailerWidth").val(0.00);
        $("#modalInsertTruck .modal-body #trailerHeight").val(0.00);
        $("#modalInsertTruck .modal-body #truckFreightSpace").val(0);
    }
}

function populateModalBus(data){
    $("#modalInsertBus #btnBusUpdate").show();
    $("#modalInsertBus #btnBusInsert").hide();
    if(data != null){
        $("#modalInsertBus #btnCarUpdate").show();
        $("#modalInsertBus #btnCarInsert").hide();
        $("#modalInsertBus .modal-body #busId").val(data.id);
        $("#modalInsertBus .modal-body #busAvailable").val(data.available+""); //Needs to be string
        $("#modalInsertBus .modal-body #busSubtype").val(data.vehicleSubtype);
        $("#modalInsertBus .modal-body #busManufacturer").val(data.manufacturer);
        $("#modalInsertBus .modal-body #busYear").val(data.year);
        $("#modalInsertBus .modal-body #busEngine").val(data.engine);
        $("#modalInsertBus .modal-body #busFuelTank").val(data.fuelTank);
        $("#modalInsertBus .modal-body #busFuelConsumption").val(data.fuelConsumption);
        $("#modalInsertBus .modal-body #busRegistrationPlate").val(data.registrationPlate);
        $("#modalInsertBus .modal-body #busMileage").val(data.mileage);
        $("#modalInsertBus .modal-body #busSpareTires").val(data.spareTires);
        $("#modalInsertBus .modal-body #busWeight").val(data.weight);
        $("#modalInsertBus .modal-body #busPayloadCapacity").val(data.payloadCapacity);
        $("#modalInsertBus .modal-body #busAdditionalEquipment").val(data.additionalEquipment);

        $("#modalInsertBus .modal-body #busSeats").val(data.seats);
        $("#modalInsertBus .modal-body #busTwoStory").val(data.twoStory+"");
        $("#modalInsertBus .modal-body #busBunkerCapacity").val(data.bunkerCapacity);
    }else{
        $("#modalInsertBus #btnBusInsert").show();
        $("#modalInsertBus #btnBusUpdate").hide();
        $("#modalInsertBus .modal-body #busId").val(-1);
        $("#modalInsertBus .modal-body #busAvailable").val("true"); //Needs to be string
        $("#modalInsertBus .modal-body #busSubtype").val("Band Bus");
        $("#modalInsertBus .modal-body #busManufacturer").val("");
        $("#modalInsertBus .modal-body #busYear").val(0);
        $("#modalInsertBus .modal-body #busEngine").val("");
        $("#modalInsertBus .modal-body #busFuelTank").val(0);
        $("#modalInsertBus .modal-body #busFuelConsumption").val(0);
        $("#modalInsertBus .modal-body #busRegistrationPlate").val("");
        $("#modalInsertBus .modal-body #busMileage").val(0);
        $("#modalInsertBus .modal-body #busSpareTires").val(0);
        $("#modalInsertBus .modal-body #busWeight").val(0);
        $("#modalInsertBus .modal-body #busPayloadCapacity").val(0);
        $("#modalInsertBus .modal-body #busAdditionalEquipment").val("");

        $("#modalInsertBus .modal-body #busSeats").val(0);
        $("#modalInsertBus .modal-body #busTwoStory").val("false");
        $("#modalInsertBus .modal-body #busBunkerCapacity").val(0);
    }
}
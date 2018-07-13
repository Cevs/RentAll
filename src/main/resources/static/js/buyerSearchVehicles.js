var searchValue = "";
var typeSelected = "All";
$(document).ready(function () {
    getAllOffers();
    $("#searchInput").keyup(function () {
        var input = $(this);
        searchValue = input.val();
        getAllOffers();
    });

    $("#vehicleTypeMenu .dropdown-menu a").click(function () {
        $("#vehicleTypeMenu button").text($(this).text());
        typeSelected = $("#vehicleTypeMenu button").text();
        getAllOffers();
    });

    $(".card-deck a").on("click", function (event) {
        event.preventDefault();
        body = $(this).parent();
        card = $(body).parent();
        companyId = card.find(".company-id").text();
        getCompanyInfo(companyId);
    });

    $(".card-deck button").on("click", function () {
        footer = $(this).parent();
        card = $(footer).parent();

        id = card.attr('id')
        vehicleType = card.find(".vehicle-type").text();
        value = $(this).text();
        title = card.find(".card-title").text();

        if (value == "Detail") {
            getVehicleData(id, vehicleType);
        } else {
            $("#modalReserve #vehicleId").val(id);
            $("#reserveFormInfo").text(title);
            $("#modalReserve").modal({backdrop: 'static', keyboard: false}, "show");
        }
    });

    $("#fromReserve").submit(function (event) {
       event.preventDefault();
       reserveVehicle();
    });

    $("#dateFrom").datepicker();
    $("#dateTo").datepicker();
});

function reserveVehicle() {
    endPoint = "http://localhost:8080/buyer/vehicle/reservation";
    vehicleId = $("#fromReserve #vehicleId").val();
    beginningDate = $("#dateFrom").val();
    endDate = $("#dateTo").val();

    $.ajax({
        url: endPoint,
        type: 'POST',
        data:{
            "vehicleId":vehicleId,
            "beginning":beginningDate,
            "end":endDate
        },
        success: function (response) {
            $('#modalReserve').modal('hide');
            $("#modalReserve .modal-body input").val("")
            getAllOffers();
        }
    });
}

function getCompanyInfo(id){
    endPoint = window.location + "/company-info";
    $.ajax({
        async: false,
        type: "GET",
        url: endPoint,
        data: {
            "id": id
        },
        success: function (data) {
            console.log(data);
            populateModalCompanyInfo(data);
            $("#modalCompanyInfo").modal({backdrop: 'static', keyboard: false}, "show");
        }
    });


}

function getVehicleData(id, vehicleType) {
    endPoint = window.location;
    if (vehicleType.indexOf("Car") >= 0) {
        endPoint += "/offers/car"
    } else if (vehicleType.indexOf("Bus") >= 0) {
        endPoint += "/offers/bus";
    } else if (vehicleType.indexOf("Truck") >= 0) {
        endPoint += "/offers/truck";
    }

    $.ajax({
        async: false,
        type: "GET",
        url: endPoint,
        data: {
            "id": id
        },
        success: function (data) {
            if (vehicleType.indexOf("Car") >= 0) {
                populateModalCar(data);
                $("#modalInsertCar").modal({backdrop: 'static', keyboard: false}, "show");
            } else if (vehicleType.indexOf("Bus") >= 0) {
                populateModalBus(data);
                $("#modalInsertBus").modal({backdrop: 'static', keyboard: false}, "show");
            } else if (vehicleType.indexOf("Truck") >= 0) {
                populateModalTruck(data);
                $("#modalInsertTruck").modal({backdrop: 'static', keyboard: false}, "show");
            }
        }
    });
}

function populateModalCar(data) {
    $("#modalInsertCar .modal-body #carId").val(data.id);
    $("#modalInsertCar .modal-body #carAvailable").val(data.available + ""); //Needs to be string
    $("#modalInsertCar .modal-body #carSubtype").val(data.vehicleSubtype);
    $("#modalInsertCar .modal-body #carManufacturer").val(data.manufacturer);
    $("#modalInsertCar .modal-body #carModel").val(data.model);
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
    $("#modalInsertCar .modal-body #carPricePerDay").val(data.pricePerDay);
    if (data.image == "") {
        $("#modalInsertCar .modal-body #carImage").attr('src', "/images/car_placeholder.jpg");
    } else {
        $("#modalInsertCar .modal-body #carImage").attr('src', "data:image/png;base64," + data.image);
    }

    $("#modalInsertCar .modal-body #carNuberOfDoors").val(data.doors);
    $("#modalInsertCar .modal-body #carColor").val(data.color);
    $("#modalInsertCar .modal-body #carTrunkCapacity").val(data.trunkCapacity);

}

function populateModalBus(data) {
    $("#modalInsertBus .modal-body #busId").val(data.id);
    $("#modalInsertBus .modal-body #busAvailable").val(data.available + ""); //Needs to be string
    $("#modalInsertBus .modal-body #busSubtype").val(data.vehicleSubtype);
    $("#modalInsertBus .modal-body #busManufacturer").val(data.manufacturer);
    $("#modalInsertBus .modal-body #busModel").val(data.model);
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
    $("#modalInsertBus .modal-body #busPricePerDay").val(data.pricePerDay);
    if (data.image == "") {
        $("#modalInsertBus .modal-body #busImage").attr('src', "/images/car_placeholder.jpg");
    } else {
        $("#modalInsertBus .modal-body #busImage").attr('src', "data:image/png;base64," + data.image);
    }

    $("#modalInsertBus .modal-body #busSeats").val(data.seats);
    $("#modalInsertBus .modal-body #busTwoStory").val(data.twoStory + "");
    $("#modalInsertBus .modal-body #busBunkerCapacity").val(data.bunkerCapacity);
}

function populateModalTruck(data) {
    $("#modalInsertTruck .modal-body #truckId").val(data.id);
    $("#modalInsertTruck .modal-body #truckAvailable").val(data.available + ""); //Needs to be string
    $("#modalInsertTruck .modal-body #truckSubtype").val(data.vehicleSubtype);
    $("#modalInsertTruck .modal-body #truckManufacturer").val(data.manufacturer);
    $("#modalInsertTruck .modal-body #truckModel").val(data.model);
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
    $("#modalInsertTruck .modal-body #truckPricePerDay").val(data.pricePerDay);
    if (data.image == "") {
        $("#modalInsertTruck .modal-body #truckImage").attr('src', "/images/car_placeholder.jpg");
    } else {
        $("#modalInsertTruck .modal-body #truckImage").attr('src', "data:image/png;base64," + data.image);
    }

    $("#modalInsertTruck .modal-body #truckHeight").val(data.truckHeight);
    $("#modalInsertTruck .modal-body #selectTrailer").val(data.trailer + "");
    if (data.trailer === true) {
        $("#modalInsertTruck .modal-body #trailerInfo").removeClass("d-none");
    } else {
        $("#modalInsertTruck .modal-body #trailerInfo").addClass("d-none");
    }
    $("#modalInsertTruck .modal-body #trailerLength").val(data.trailerLength);
    $("#modalInsertTruck .modal-body #trailerWidth").val(data.trailerWidth);
    $("#modalInsertTruck .modal-body #trailerHeight").val(data.trailerHeight);
    $("#modalInsertTruck .modal-body #truckFreightSpace").val(data.freightSpace);
}

function populateModalCompanyInfo(data){
    $("#modalCompanyInfo .modal-body #companyImg").attr('src', "data:image/png;base64," + data.image);
    $("#modalCompanyInfo .modal-body #companyName").val(data.companyName);
    $("#modalCompanyInfo .modal-body #companyEmail").val(data.email);
    $("#modalCompanyInfo .modal-body #companyPhone").val(data.companyPhoneNumber);
    $("#modalCompanyInfo .modal-body #companyCountry").val(data.location.country);
    $("#modalCompanyInfo .modal-body #companyCity").val(data.location.city);
    $("#modalCompanyInfo .modal-body #companyAddress").val(data.location.address);
    $("#modalCompanyInfo .modal-body #companyZipCode").val(data.location.zipCode);
    $("#modalCompanyInfo .modal-body #companyBankAccount").val(data.bankAccount);
}

function getAllOffers() {
    endPoint = window.location + "/offers";
    $.ajax({
        async: false,
        type: "GET",
        url: endPoint,
        data: {
            "search": searchValue,
            "type": typeSelected
        },
        success: function (data) {
            $("#card-deck-container").empty();
            $.each(data, function (i, item) {

                if (i % 3 == 0) {
                    id = i;
                    $newDiv = "<div id='card-deck-" + id + "' class='card-deck mx-auto'></div>";
                    $("#card-deck-container").append($newDiv);
                }
                srcImg = "/images/car_placeholder.jpg";
                if (item.image != "") {
                    srcImg = "data:image/png;base64," + item.image;
                }
                var $card = $(
                    "<div id ='" + item.id + "' class=\"card\">\n" +
                    "            <img class=\"card-img-top\" src='" + srcImg + "' style='height: 180px;' alt=\"Card image cap\">\n" +
                    "            <div class=\"card-body\">\n" +
                    "                <h5 class=\"card-title\">" + item.manufacturer + "\n" + item.model + "</h5>\n" +
                    "                <p class=\"card-text vehicle-type\"><strong>Type: </strong>" + item.vehicleType + "</p>" +
                    "                <p class=\"card-text company-id d-none\">" + item.companyId + "</p>" +
                    "                <p class=\"card-text\"><strong>SubType: </strong>" + item.vehicleSubtype + "</p>" +
                    "                <p class=\"card-text\"><strong>Year: </strong>" + item.year + "</p>" +
                    "                <p class=\"card-text\"><strong>Fuel consumption (L/100km): </strong>" + item.fuelConsumption + "</p>" +
                    "                <p class=\"card-text\"><strong>Price (price per day): </strong>" + item.pricePerDay + " &#8364</p>" +
                    "                <p class=\"card-text\"><strong>Company: </strong><a href=''>" + item.companyName + "</a></p>" +
                    "            </div>\n" +
                    "            <div class=\"card-footer\">\n" +
                    "                <button class=\"btn btn-info \" style='float:left;''>Detail</button>" +
                    "                <button class=\"btn btn-success \" style='float:right;'>Reserver</button>" +
                    "            </div>\n" +
                    "        </div>"
                )

                $("#card-deck-" + id).append($card);
            });
        }
    });
}
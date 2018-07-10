var vehicleSelected = 'All';
var statusSelected = 'All';
var searchValue = "";
$(document).ready(function () {
    $("#vehicleTypeMenu .dropdown-menu a").click(function () {
        vehicleSelected = $(this).text();
        $("#vehicleTypeMenu button").text(vehicleSelected);
        getReservations();
    });

    $("#reservationTypeMenu .dropdown-menu a").click(function () {
        statusSelected = $(this).text();
        $("#reservationTypeMenu button").text(statusSelected);
        getReservations();
    });

    $("#searchInput").keyup(function () {
        var input = $(this);
        searchValue = input.val();
        getReservations();
    });

    getReservations();

});

function getReservations() {
    endPoint = window.location;
    if (vehicleSelected == 'All' && statusSelected == 'All') {
        endPoint += "/all?";

        $.ajax({
            async: false,
            type: "GET",
            url: endPoint,
            data:{"data":searchValue},
            success: function (data) {
                updateTableData(data);
            }
        });
    } else if (vehicleSelected == "All" && statusSelected != "All") {
        endPoint += "/all/" + statusSelected;
        $.ajax({
            async: false,
            type: "GET",
            url: endPoint,
            data:{"data":searchValue},
            success: function (data) {
                updateTableData(data);
            }
        });
    } else if (vehicleSelected != "All" && statusSelected == "All") {
        endPoint += "/status/" + vehicleSelected;
        $.ajax({
            async: false,
            type: "GET",
            url: endPoint,
            data:{"data":searchValue},
            success: function (data) {
                updateTableData(data);
            }
        });
    } else {
        endPoint += "/" + vehicleSelected + "/" + statusSelected;
        $.ajax({
            async: false,
            type: "GET",
            url: endPoint,
            data:{"data":searchValue},
            success: function (data) {
                updateTableData(data);
            }
        });
    }
}

function getAllVehicleReservations() {
    endPoint = window.location + "/all";
    $.ajax({
        async: false,
        type: "GET",
        url: endPoint,
        success: function (data) {
            console.log(data);
            updateTableData(data);
        }
    });
}

function updateTableData(data) {
    $("#reservationTable tbody>tr").remove();
    $("#reservationTable thead>tr").remove();
    $tr = $("<tr class='text-center'>").append(
        $("<th>").text("#"),
        $("<th class='d-none'>").text("Id"),
        $("<th >").text("Firstname"),
        $("<th>").text("Lastname"),
        $("<th>").text("Phone"),
        $("<th>").text("Model"),
        $("<th>").text("Type"),
        $("<th>").text("Subtype"),
        $("<th>").text("Registration Plate"),
        $("<th>").text("Reserve From"),
        $("<th>").text("Reserve To"),
        $("<th>").text("Status"),
        $("<th>").text("Cancel Reservation")
    )
        .appendTo("#reservationTable thead");
    $.each(data, function (i, item) {
        if (item.status == "Pending") {
            $tr = $("<tr class='text-center'>").append(
                $("<th>").text(i + 1),
                $("<td class='d-none' id='tableReservationId'>").text(item.id),
                $("<td id='tableBuyerFirstname'>").text(item.buyerFirstname),
                $("<td id='tableBuyerLastname'>").text(item.buyerLastname),
                $("<td id='tableBuyerPhone'>").text(item.buyerPhoneNumber),
                $("<td id='tableModel'>").text(item.vehicleModel),
                $("<td id='tableType'>").text(item.vehicleType),
                $("<td id='tableSubtype'>").text(item.vehicleSubtype),
                $("<td id='tableRegistrationPlate'>").text(item.vehicleRegistrationPlate),
                $("<td id='tableReserveFrom'>").text(item.reserveFrom),
                $("<td id='tableReserveTo'>").text(item.reserveTo),
                $("<td id='tableStatus'>").text(item.status),
                $("<td id='tableReservationAction'>").html(
                    "<button id = 'btnApprove' class='btn btn-success btn-reservation'><i class='fas fa-check'></i></button>" +
                    "<button id = 'btnDecline' class='btn btn-warning btn-reservation'><i class='fas fa-ban'></i></button>" +
                    "<button id = 'btnDelete' class='btn btn-danger btn-reservation'><i class='far fa-trash-alt'></i></button>"
                )
            )
                .appendTo("#reservationTable");
        } else {
            if(item.status === "Approved"){
                statusClass = "status-approved";
            }else if (item.status === "Declined"){
                statusClass = "status-declined";
            }

            $tr = $("<tr class='text-center'>").append(
                $("<th>").text(i + 1),
                $("<td  class='d-none' id='tableReservationId'>").text(item.id),
                $("<td id='tableBuyerFirstname'>").text(item.buyerFirstname),
                $("<td id='tableBuyerLastname'>").text(item.buyerLastname),
                $("<td id='tableBuyerPhone'>").text(item.buyerPhoneNumber),
                $("<td id='tableModel'>").text(item.vehicleModel),
                $("<td id='tableType'>").text(item.vehicleType),
                $("<td id='tableSubtype'>").text(item.vehicleSubtype),
                $("<td id='tableRegistrationPlate'>").text(item.vehicleRegistrationPlate),
                $("<td id='tableReserveFrom'>").text(item.reserveFrom),
                $("<td id='tableReserveTo'>").text(item.reserveTo),
                //$("<td id='tableStatus'>").text(item.status),
                $("<td id='tableStatus'>").html(
                    "<p class='"+statusClass+" m-0'>"+item.status+"</p>"
                ),
                $("<td id='tableReservationAction'>").html(
                    "<button id = 'btnDelete' class='btn btn-danger btn-reservation'><i class='far fa-trash-alt'></i></button>"
                )
            )
                .appendTo("#reservationTable");
        }
    });
    $("#reservationTable tbody>tr>td>button").on("click", function (event) {

        btnId = $(this).attr("id");
        td = $(this).parent();
        tr = td.parent();
        reservationId = tr.children("#tableReservationId").text();
        console.log(btnId);
        if (btnId === "btnDelete") {
            cancelReservation(reservationId);
        } else if (btnId === "btnDecline") {
            changeReservationStatus(reservationId, "Declined");

        } else if (btnId === "btnApprove") {
            changeReservationStatus(reservationId, "Approved");

        } else {
            console.log("Unknown reservation action");
        }


    });
}

function changeReservationStatus(id, status) {
    endPoint = window.location + "/" + id;
    $.ajax({
        async: false,
        type: "PUT",
        url: endPoint,
        data: {
            status: status
        },
        success: function (response) {
            if (response == "true") {
                getReservations();
            }
            else {
                alert("Update failed");
            }
        }
    });
}

function cancelReservation(id) {
    endPoint = window.location + "/" + id;
    $.ajax({
        async: false,
        type: "DELETE",
        url: endPoint,
        success: function (response) {
            if (response == "true") {
                getReservations();
            }
            else {
                alert("Delete failed");
            }
        }
    });
}


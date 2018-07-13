var userTypeSelected = "All";
var searchValue = "";
$(document).ready(function () {
    $("#userTypeMenu .dropdown-menu a").click(function () {
        userTypeSelected = $(this).text();
        $("#userTypeMenu button").text(userTypeSelected);
        getUsers();
    });

    $("#searchInput").keyup(function () {
        var input = $(this);
        searchValue = input.val();
        getUsers();
    });

    getUsers();
});

function getUsers() {
    endPoint = window.location + "/get";
    $.ajax({
        async: false,
        type: "GET",
        url: endPoint,
        data: {
            "search": searchValue,
            "type": userTypeSelected
        },
        success: function (data) {
            updateTableData(data);
        }
    });
}

function updateTableData(data) {
    $("#usersTable tbody>tr").remove();
    $("#usersTable thead>tr").remove();
    console.log(data);
    $tr = $("<tr class='text-center'>").append(
        $("<th>").text("#"),
        $("<th>").text("Id"),
        $("<th >").text("email"),
        $("<th>").text("password"),
        $("<th>").text("user_type"),
        $("<th>").text("Country"),
        $("<th>").text("City"),
        $("<th>").text("Address"),
        $("<th>").text("Zip Code"),
        $("<th>").text("Image"),
        $("<th>").text("Locked"),
        $("<th>").text("Action")
    )
        .appendTo("#usersTable thead");
    $.each(data, function (i, item) {
        img = "/images/profile.png";
        if (item.image != null) {
            img = "data:image/png;base64," + item.image;
        }
        if(item.locked == true){
            $tr = $("<tr class='text-center'>").append(
                $("<th>").text(i + 1),
                $("<td class='' id='tableUserId'>").text(item.id),
                $("<td id='tableUserEmail'>").text(item.email),
                $("<td id='tableUserPassword'>").text(item.password),
                $("<td id='tableUserType'>").text(item.userType),
                $("<td id='tableUserCountry'>").text(item.location.country),
                $("<td id='tableUserCity'>").text(item.location.city),
                $("<td id='tableUserAddress'>").text(item.location.address),
                $("<td id='tableUserZipCode'>").text(item.location.zipCode),
                $("<td id='tableUserImage'>").html(
                    " <img src='" + img + "' id='carImage' style='width:50px;height: 50px'>"
                ),
                $("<td id='tableUserLocked'>").text(item.locked),
                $("<td id='tableUserAction'>").html(
                    "<button id = 'btnLock' class='btn btn-dark btn-reservation' disabled='disabled'><i class='fas fa-lock'></i></button>" +
                    "<button id = 'btnUnlock' class='btn btn-dark btn-reservation'><i class='fas fa-lock-open'></i></button>"+
                    "<button id = 'btnDelete' class='btn btn-danger btn-reservation'><i class='far fa-trash-alt'></i></button>"
                )
            )
                .appendTo("#usersTable");

        }else{
            $tr = $("<tr class='text-center'>").append(
                $("<th>").text(i + 1),
                $("<td class='' id='tableUserId'>").text(item.id),
                $("<td id='tableUserEmail'>").text(item.email),
                $("<td id='tableUserPassword'>").text(item.password),
                $("<td id='tableUserType'>").text(item.userType),
                $("<td id='tableUserCountry'>").text(item.location.country),
                $("<td id='tableUserCity'>").text(item.location.city),
                $("<td id='tableUserAddress'>").text(item.location.address),
                $("<td id='tableUserZipCode'>").text(item.location.zipCode),
                $("<td id='tableUserImage'>").html(
                    " <img src='" + img + "' id='carImage' style='width:50px;height: 50px'>"
                ),
                $("<td id='tableUserLocked'>").text(item.locked),
                $("<td id='tableUserAction'>").html(
                    "<button id = 'btnLock' class='btn btn-dark btn-reservation'><i class='fas fa-lock'></i></button>" +
                    "<button id = 'btnUnlock' class='btn btn-dark btn-reservation'  disabled='disabled'><i class='fas fa-lock-open'></i></button>"+
                    "<button id = 'btnDelete' class='btn btn-danger btn-reservation'><i class='far fa-trash-alt'></i></button>"
                )
            )
                .appendTo("#usersTable");

        }
    });

    $("#usersTable tbody>tr>td>button").on("click", function () {
        td = $(this).parent();
        tr = $(td).parent();
        id = tr.find("#tableUserId").text();
        if ($(this).attr('id') === "btnDelete") {
            deleteUser(id);
        } else if($(this).attr('id') === "btnLock"){
            lockUser(id, true);
        }else if($(this).attr("id") === "btnUnlock"){
            lockUser(id, false);
        }
    });
}

function lockUser(id, locked){
    endPoint = window.location + "/lock/" + id;
    $.ajax({
        async: false,
        type: "PUT",
        url: endPoint,
        data:{"locked":locked},
        success: function (response) {
            if (response == "true") {
                getUsers();
            }
            else {
                alert("Update failed");
            }
        }
    });
}

function deleteUser(id){
    endPoint = window.location + "/" + id;
    $.ajax({
        async: false,
        type: "DELETE",
        url: endPoint,
        success: function (response) {
            if (response == "true") {
                getUsers();
            }
            else {
                alert("Delete failed");
            }
        }
    });
}
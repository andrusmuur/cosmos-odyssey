async function fetchRoutes() {
    var formData = new FormData(document.getElementById("routeForm"));
    const origin = formData.get("origin");
    const destination = formData.get("destination");
    const sortBy = formData.get("sortBy");
    const companyName = formData.get("companyName")

    try{
        const response = await fetch(`/routes?origin=${origin}&destination=${destination}&sortBy=${sortBy}&companyName=${companyName}`);

        if (!response.ok) {
            throw new Error("Could not fetch resource");
        } else {
            const routes = await response.json();
            createTable(routes);
        }
    }
    catch(error) {
        console.error(error);
    }
}

function createTable(routes) {
    var table = document.createElement("table");
    var tr = table.insertRow(-1);

    const tableHeaders = ["Path", "Providers", "First flight start", "Last flight end", "Total price", "Total distance", "Total travel time"];
    for (const header of tableHeaders) {
        var th = document.createElement("th");
        th.innerHTML = header;
        tr.appendChild(th);
    }

    for (const route of routes) {
        tr = table.insertRow(-1);
        tr.dataset.route = JSON.stringify(route);
        tr.setAttribute("onclick", "selectRow(this);");
        tr.setAttribute("class", "routeRow");

        var cell = tr.insertCell(-1);
        cell.innerHTML = route.path.map((route) => " " + route.fromPlanet + " -> " + route.toPlanet);

        const providers = route.pathProviders;
        cell = tr.insertCell(-1);
        cell.innerHTML = providers.map((provider) => " " + provider.companyName);

        cell = tr.insertCell(-1);
        if (providers.length > 0) {
            cell.innerHTML = providers[0].flightStart
        }

        cell = tr.insertCell(-1);
        if (providers.length > 0) {
            cell.innerHTML = providers[providers.length - 1].flightEnd
        }

        cell = tr.insertCell(-1);
        cell.innerHTML = route.totalPrice;

        cell = tr.insertCell(-1);
        cell.innerHTML = route.totalDistance;

        cell = tr.insertCell(-1);
        cell.innerHTML = route.totalTravelTimeInDays + " days";
    }

    var routeTable = document.getElementById("routeTable");
    routeTable.innerHTML = "";
    routeTable.appendChild(table);
}

async function selectRow(row) {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    if (firstName == "" || lastName == "") {
        alert("Please enter your first and last name to make a reservation")
        return;
    }

    if (confirm("Are you sure you want to make a reservation?")) {
        const response = await fetch("/reservation", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                travelPath: JSON.parse(row.dataset.route)})
        });

        if (!response.ok) {
            alert("There was a problem with making the reservation, please try again");
            throw new Error("Could not fetch resource");
        } else {
            const reservationIsSaved = await response.json();
            if (reservationIsSaved) {
                alert("Reservation was made successfully");
            } else {
                alert("Price list is expired, please try again")
                var routeTable = document.getElementById("routeTable");
                routeTable.innerHTML = "";
            }
        }
    }
}
async function fetchRoutes() {

    try{
        const origin = document.getElementById("origin").value;
        const destination = document.getElementById("destination").value;
        const response = await fetch(`/routes?origin=${origin}&destination=${destination}`);

        if (!response.ok) {
            throw new Error("Could not fetch resource");
        } else {
            const routes = await response.json();
            var table = document.createElement("table");
            var tr = table.insertRow(-1);

            const tableHeaders = ["Path", "Providers", "Total price", "Total distance", "Total travel time in days"];
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
                cell.innerHTML = route.path.map((route) => route.fromPlanet + "->" + route.toPlanet);

                cell = tr.insertCell(-1);
                cell.innerHTML = route.pathProviders.map((provider) => provider.companyName);

                cell = tr.insertCell(-1);
                cell.innerHTML = route.totalPrice;

                cell = tr.insertCell(-1);
                cell.innerHTML = route.totalDistance;

                cell = tr.insertCell(-1);
                cell.innerHTML = route.totalTravelTimeInDays;
            }

            var routeTable = document.getElementById("routeTable");
            routeTable.innerHTML = "";
            routeTable.appendChild(table);
        }
    }
    catch(error) {
        console.error(error);
    }
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
            alert("Reservation was made successfully");
        }
    }
}
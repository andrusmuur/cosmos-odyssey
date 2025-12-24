async function fetchRoutes() {

    document.getElementById("routeTable").innerHTML = '';

    try{
        const origin = document.getElementById("origin").value;
        const destination = document.getElementById("destination").value;
        const response = await fetch(`/routes?origin=${origin}&destination=${destination}`);

        if (!response.ok) {
            throw new Error("Could not fetch resource");
        } else {
            const routes = await response.json();
            console.log(routes);
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

            var divContainer = document.getElementById("routeTable");
            divContainer.innerHTML = "";
            divContainer.appendChild(table);
        }
    }
    catch(error) {
        console.error(error);
    }
}
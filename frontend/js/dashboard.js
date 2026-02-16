
function loadSlots(){
    return JSON.parse(localStorage.getItem("slots")) || [
        { id: "A1", type: "Car", price: 50, status: "Available" },
        { id: "A2", type: "Bike", price: 20, status: "Occupied" }
    ];
}

function loadBookings(){
    return JSON.parse(localStorage.getItem("bookings")) || [];
}

let bookings = loadBookings();



let slots = loadSlots();


function showSection(id){
    const sections = document.querySelectorAll("section");
    sections.forEach(sec => sec.style.display = "none");
    document.getElementById(id).style.display = "block";
}

function logout(){
    alert("Logged out");
    window.location.href = "../login.html";
}

function addSlot(){
    const slotId = document.querySelector("#slotNumber").value;
    const slotType = document.querySelector("#slotType").value;
    const slotPrice = document.querySelector("#slotPrice").value;

    if(!slotId || !slotPrice){
        alert("Enter slot details");
        return;
    }

    slots.push({
        id: slotId,
        type: slotType,
        price: slotPrice,
        status: "Available"
    });

    localStorage.setItem("slots", JSON.stringify(slots));

    alert("Slot added");

    renderSlots();
}

function bookSlot(slotId){
    const slot = slots.find(s => s.id === slotId);

    if(slot){
        slot.status = "Occupied";

        // save slot update
        localStorage.setItem("slots", JSON.stringify(slots));

        // add booking record
        bookings.push({
            slot: slot.id,
            type: slot.type,
            price: slot.price,
            status: "Confirmed"
        });

        localStorage.setItem("bookings", JSON.stringify(bookings));

        alert("Booking confirmed for slot " + slotId);

        renderSlots();
        renderBookings();
        renderOwnerBookings();
        renderAdminStats();
    }
}


function renderSlots(){
    slots = loadSlots();
    const container = document.getElementById("slotList");
    if(!container) return;

    container.innerHTML = "";

    slots.forEach(slot => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>Mall A</td>
            <td>${slot.id}</td>
            <td>${slot.type}</td>
            <td>₹${slot.price}</td>
            <td class="${slot.status === "Available" ? "available" : "occupied"}">
                ${slot.status}
            </td>
            <td>
                ${slot.status === "Available"
                    ? `<button onclick="bookSlot('${slot.id}')">Book</button>`
                    : `<button disabled>Full</button>`}
            </td>
        `;

        container.appendChild(row);
    });
}



function renderBookings(){
    bookings = loadBookings();

    const container = document.getElementById("bookingList");
    if(!container) return;

    container.innerHTML = "";

    bookings.forEach((b, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${b.slot}</td>
            <td>${b.type}</td>
            <td>₹${b.price}</td>
            <td>${b.status}</td>
            <td>
                <button onclick="cancelBooking(${index})">Cancel</button>
            </td>
        `;

        container.appendChild(row);
    });
}

function cancelBooking(index){
    bookings = loadBookings();
    slots = loadSlots();

    const booking = bookings[index];

    // make slot available again
    const slot = slots.find(s => s.id === booking.slot);
    if(slot){
        slot.status = "Available";
    }

    // remove booking
    bookings.splice(index, 1);

    localStorage.setItem("bookings", JSON.stringify(bookings));
    localStorage.setItem("slots", JSON.stringify(slots));

    alert("Booking cancelled");

    renderSlots();
    renderBookings();
    renderOwnerBookings();
    renderAdminStats();
}

function renderOwnerDashboard(){
    slots = loadSlots();
    bookings = loadBookings();

    const total = slots.length;
    const available = slots.filter(s => s.status === "Available").length;
    const occupied = slots.filter(s => s.status === "Occupied").length;
    const totalBookings = bookings.length;

    const totalEl = document.getElementById("ownerTotal");
    const availEl = document.getElementById("ownerAvailable");
    const occEl = document.getElementById("ownerOccupied");
    const bookEl = document.getElementById("ownerBookings");

    if(totalEl) totalEl.textContent = total;
    if(availEl) availEl.textContent = available;
    if(occEl) occEl.textContent = occupied;
    if(bookEl) bookEl.textContent = totalBookings;

    // recent bookings
    const recent = document.getElementById("ownerRecentList");
    if(recent){
        recent.innerHTML = "";
        bookings.slice(-5).reverse().forEach(b => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${b.user || "User"}</td>
                <td>${b.slot}</td>
                <td>${b.type}</td>
                <td>${b.status}</td>
            `;
            recent.appendChild(row);
        });
    }
}

function renderOwnerBookings(){
    bookings = loadBookings();

    const list = document.getElementById("ownerBookingList");
    if(!list) return;

    list.innerHTML = "";

    let active = 0;
    let completed = 0;

    bookings.forEach(b => {
        if(b.status === "Confirmed") active++;
        if(b.status === "Completed") completed++;

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${b.user || "User"}</td>
            <td>${b.slot}</td>
            <td>${b.type}</td>
            <td>
                <span class="status ${b.status === "Confirmed" ? "active" : "done"}">
                    ${b.status}
                </span>
            </td>
        `;

        list.appendChild(row);
    });

    const totalEl = document.getElementById("ownerBookingCount");
    const activeEl = document.getElementById("ownerActiveCount");
    const compEl = document.getElementById("ownerCompletedCount");

    if(totalEl) totalEl.textContent = bookings.length;
    if(activeEl) activeEl.textContent = active;
    if(compEl) compEl.textContent = completed;
}


// function renderAdminStats(){
//     slots = loadSlots();
//     bookings = loadBookings();
function renderAdminReports(){
    bookings = loadBookings();
    slots = loadSlots();
    const total = slots.length;
    const available = slots.filter(s => s.status === "Available").length;
    const occupied = slots.filter(s => s.status === "Occupied").length;
    const totalBookings = bookings.length;

    const totalEl = document.getElementById("statTotal");
    const availEl = document.getElementById("statAvailable");
    const occEl = document.getElementById("statOccupied");
    const bookEl = document.getElementById("statBookings");

    if(totalEl) totalEl.textContent = total;
    if(availEl) availEl.textContent = available;
    if(occEl) occEl.textContent = occupied;
    if(bookEl) bookEl.textContent = totalBookings;
}

function renderUserDashboard(){
    slots = loadSlots();
    bookings = loadBookings();

    const available = slots.filter(s => s.status === "Available").length;
    const myBookings = bookings.length;
    const active = bookings.length;
    const spent = bookings.reduce((sum, b) => sum + Number(b.price), 0);

    const availEl = document.getElementById("userAvailable");
    const bookEl = document.getElementById("userBookings");
    const actEl = document.getElementById("userActive");
    const spentEl = document.getElementById("userSpent");

    if(availEl) availEl.textContent = available;
    if(bookEl) bookEl.textContent = myBookings;
    if(actEl) actEl.textContent = active;
    if(spentEl) spentEl.textContent = "₹" + spent;

    // recent bookings
    const recent = document.getElementById("recentBookingList");
    if(recent){
        recent.innerHTML = "";
        bookings.slice(-3).reverse().forEach(b => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${b.slot}</td>
                <td>${b.type}</td>
                <td>${b.status}</td>
            `;
            recent.appendChild(row);
        });
    }
}

function renderAdminReports(){
    const bookings = loadBookings();
    const slots = loadSlots();

    let revenue = 0;
    bookings.forEach(b => revenue += Number(b.price));

    const revenueEl = document.getElementById("totalRevenue");
    const bookingsEl = document.getElementById("totalBookings");
    const activeEl = document.getElementById("activeSlots");

    if(revenueEl) revenueEl.textContent = "₹" + revenue;
    if(bookingsEl) bookingsEl.textContent = bookings.length;
    if(activeEl) activeEl.textContent = slots.filter(s => s.status==="Available").length;

    renderReportTable(bookings);
    renderBookingChart(bookings);
}


function renderReportTable(bookings){
    const table = document.getElementById("reportTable");
    if(!table) return;

    table.innerHTML = "";

    bookings.forEach((b,i) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>Day ${i+1}</td>
            <td>1</td>
            <td>₹${b.price}</td>
        `;
        table.appendChild(row);
    });
}


function renderBookingChart(bookings){
    const ctx = document.getElementById("bookingChart");
    if(!ctx) return;

    const labels = bookings.map((_,i)=>"Day "+(i+1));
    const data = bookings.map(b=>b.price);

    new Chart(ctx,{
        type:"bar",
        data:{
            labels:labels,
            datasets:[{
                label:"Revenue",
                data:data,
                borderRadius:6
            }]
        },
        options:{
            responsive:true,
            plugins:{
                legend:{display:false}
            }
        }
    });
}

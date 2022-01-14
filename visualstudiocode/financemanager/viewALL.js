const url = "http://localhost:3000/"

window.addEventListener("load", allReimbursement);
document.getElementById("backButton").addEventListener("click", back);

function back(){
    // document.getElementById("text").innerText="GOING TO FINANCE MANAGER HOMEPAGE";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/financemanager.html";
    }

async function allReimbursement() {

    let response = await fetch (url + "reimbursement/stat/status");

    if (response.status === 200) {

        let data = await response.json();
        console.log(data);

        for(let reimbursement of data) {
            
            let row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell);

            let cell2 = reimbursement.author;
            let response2 = await fetch (url + "user/id/" + cell2)
            let datum2 = await response2.json();
            let actualcell2 = document.createElement("td");
            actualcell2.innerHTML = datum2.value.fname + " " + datum2.value.lname;
            row.appendChild(actualcell2);

            let cell3 = reimbursement.resolver;
            let response3 = await fetch (url + "user/id/" + cell3)
            let datum3 = await response3.json();
            let actualcell3 = document.createElement("td");
            actualcell3.innerHTML = datum3.value.fname + " " + datum3.value.lname;
            row.appendChild(actualcell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = reimbursement.amount;
            row.appendChild(cell4);

            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.typee;
            row.appendChild(cell5);

            let cell6 = document.createElement("td");
            cell6.innerHTML = reimbursement.status;
            row.appendChild(cell6);

            if (cell6.innerHTML == "APPROVED") {
                cell.style.backgroundColor = "rgba(0,255,0,0.5)";
                actualcell2.style.backgroundColor = "rgba(0,255,0,0.5)";
                actualcell3.style.backgroundColor = "rgba(0,255,0,0.5)";
                cell4.style.backgroundColor = "rgba(0,255,0,0.5)";
                cell5.style.backgroundColor = "rgba(0,255,0,0.5)";
                cell6.style.backgroundColor = "rgba(0,255,0,0.5)";
            } else if (cell6.innerHTML == "DENIED"){
                cell.style.backgroundColor = "rgba(255,0,0,0.5)";
                actualcell2.style.backgroundColor = "rgba(255,0,0,0.5)";
                actualcell3.style.backgroundColor = "rgba(255,0,0,0.5)";
                cell4.style.backgroundColor = "rgba(255,0,0,0.5)";
                cell5.style.backgroundColor = "rgba(255,0,0,0.5)";
                cell6.style.backgroundColor = "rgba(255,0,0,0.5)";
            }

            cell.style.color = "white"
            actualcell2.style.color = "white"
            actualcell3.style.color = "white"
            cell4.style.color = "white"
            cell5.style.color = "white"
            cell6.style.color = "white"

            document.getElementById("userBody").appendChild(row);

            
        }
    }


}
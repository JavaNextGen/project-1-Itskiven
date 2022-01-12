const url = "http://localhost:3000/"

window.addEventListener("load", allReimbursement);
document.getElementById("backButton").addEventListener("click", back);

function back(){
    document.getElementById("text").innerText="GOING TO FINANCE MANAGER HOMEPAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/financemanager.html";}, 1500);
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

            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.amount;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = reimbursement.typee;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = reimbursement.status;
            if (cell4.innerHTML == "PENDING") {
                cell4.style.backgroundColor = "yellow";
            } else if (cell4.innerHTML == "APPROVED"){
                cell4.style.backgroundColor = "green";
            } else{
                cell4.style.backgroundColor = "red";
            }
            row.appendChild(cell4);

            document.getElementById("userBody").appendChild(row);

            
        }
    }


}
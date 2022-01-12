const url = "http://localhost:3000/"

window.addEventListener("load", pending);
document.getElementById("backButton").addEventListener("click", back);

function back(){
    document.getElementById("text").innerText="GOING TO FINANCE MANAGER HOMEPAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/financemanager.html";}, 1500);
    }

async function pending() {

    actualauthor = localStorage.getItem('intauthor');

    let response = await fetch (url + "reimbursement");

    if (response.status === 200) {

        let data = await response.json();
        console.log(data);

        for(let reimbursement of data) {
            
            let row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell);

            let cell2 = reimbursement.author;
            let response = await fetch (url + "user/id/" + cell2)
            let datum = await response.json();
            let actualcell2 = document.createElement("td");
            actualcell2.innerHTML = datum.value.fname + " " + datum.value.lname;
            row.appendChild(actualcell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = reimbursement.amount;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = reimbursement.typee;
            row.appendChild(cell4);

            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.status;
            if (cell5.innerHTML == "PENDING") {
                cell5.style.backgroundColor = "yellow";
            } else{
                cell5.style.backgroundColor = "red";
            }
            row.appendChild(cell5);

            document.getElementById("userBody").appendChild(row);

            
        }
    }


}
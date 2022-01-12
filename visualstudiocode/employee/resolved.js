const url = "http://localhost:3000/"

// document.getElementById("wholebody").addEventListener("mouseover", resolved);
window.addEventListener("load", resolved);
document.getElementById("backButton").addEventListener("click", back);

function back(){
    document.getElementById("text").innerText="GOING TO EMPLOYEE HOMEPAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/employee/employee.html";}, 1500);
    }

async function resolved() {

    document.body.removeEventListener("mouseover", resolved);

    actualuser = localStorage.getItem('actualuser');

    let response = await fetch (url + "reimbursement/rs/" + actualuser);

    if (response.status === 200) {

        let data = await response.json();
        console.log(data);


        for(let reimbursement of data) {
            
            var row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell);

            let cell2 = reimbursement.resolver;
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
            if (cell5.innerHTML == "APPROVED") {
                cell5.style.backgroundColor = "green";
            } else if (cell5.innerHTML == "DENIED"){
                cell5.style.backgroundColor = "red";
            }
            row.appendChild(cell5);

            document.getElementById("userBody").appendChild(row);

            
        }
    }

}
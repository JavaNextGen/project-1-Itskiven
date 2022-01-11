const url = "http://localhost:3000/"

document.getElementById("wholebody").addEventListener("mouseover", pending);
document.getElementById("backButton").addEventListener("click", back);

function back(){
    document.getElementById("text").innerText="GOING TO EMPLOYEE HOMEPAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/employee/employee.html";}, 1500);
    }

async function pending() {

    document.body.removeEventListener("mouseover", pending);

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

            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.amount;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = reimbursement.typee;
            if (reimbursement.typee == "APPROVED") {
                document.getElementById("color").setAttribute(color)
            }
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = reimbursement.status;
            if (cell4.innerHTML == "APPROVED") {
                cell4.style.backgroundColor = "green";
            } else if (cell4.innerHTML == "DENIED"){
                cell4.style.backgroundColor = "red";
            }
            row.appendChild(cell4);

            document.getElementById("userBody").appendChild(row);

            
        }
    }

}
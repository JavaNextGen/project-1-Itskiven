// const url = "http://localhost:3000/"

document.getElementById("wholebody").addEventListener("mouseover", pending);
document.getElementById("backButton").addEventListener("click", back);

function back(){
    document.getElementById("text").innerText="GOING TO EMPLOYEE HOMEPAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/employee.html";}, 1500);
    }

async function pending() {

    document.body.removeEventListener("mouseover", pending);

    
    let response = await fetch (url + "http://localhost:3000/reimbursement" + );

    if (response.status === 200) {

        let data = await response.json();

        for(let reimbursement of data) {
            
            let row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell);

            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.amount;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = reimbursement.status;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = reimbursement.typee;
            row.appendChild(cell4);

            document.getElementById("userBody").appendChild(row);

            
        }
    }


}
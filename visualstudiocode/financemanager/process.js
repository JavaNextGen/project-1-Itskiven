const url = "http://localhost:3000/"

window.addEventListener("load", pending);
document.getElementById("backButton").addEventListener("click", back);
document.getElementById("decisionButton").addEventListener("click", process);

function back(){
    // document.getElementById("text").innerText="GOING TO FINANCE MANAGER HOMEPAGE";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/financemanager.html";
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
            row.appendChild(cell5);

            cell.style.backgroundColor = "rgba(255,255,0,0.8)";
            actualcell2.style.backgroundColor = "rgba(255,255,0,0.8)";
            cell3.style.backgroundColor = "rgba(255,255,0,0.8)";
            cell4.style.backgroundColor = "rgba(255,255,0,0.8)";
            cell5.style.backgroundColor = "rgba(255,255,0,0.8)";

            document.getElementById("userBody").appendChild(row);

            
        }
    }


}

async function process() {

    let idProcess = document.getElementById("number").value;
    let resolver = localStorage.getItem("intauthor");
    let decision = document.querySelector('input[name="choice"]:checked').value;
  
    // let response4 = await fetch()

    let processing = {
        id: idProcess,
        status: decision,
        resolver: resolver
    }

    console.log(processing);

    let response = await fetch(url + "reimbursement/process", {

        method: "PUT", 
        body: JSON.stringify(processing), 
        credentials: "include"

    });

    if(response.status === 202) {
        console.log("REIMBURSEMENT UPDATED!")
        document.getElementById("text").innerText="REIMBURSEMENT UPDATED!";
         window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/process.html";}, 3000);

    } else if (response.status === 404) {
       
        document.getElementById("text").innerText="REIMBURSEMENT ID DOES NOT EXIST! TRY AGAIN";
        window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/process.html";}, 3000);
    } else if (response.status === 400){
        document.getElementById("text").innerText="REIMBURSEMENT ALREADY RESOLVED!";
        window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/process.html";}, 3000);
    } else if (response.status === 403) {
        document.getElementById("text").innerText="YOU CANNOT PROCESS YOUR OWN REIMBURSEMENT REQUEST!";
        window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/process.html";}, 3000);
    }

    

}
const url = "http://localhost:3000/"

document.getElementById("cancelButton").addEventListener("click", cancel);
document.getElementById("submitButton").addEventListener("click", submit);

function cancel(){
    document.getElementById("text").innerText="GOING TO FINANCE MANAGER HOMEPAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/financemanager.html";}, 1500);
    }

async function submit(){
    let amount = document.getElementById("number").value;
    let selectedValue = document.getElementById("typee").value;
    actualuser = localStorage.getItem('intauthor');

    let user = {
        amount:amount,
        author:actualuser,
        status:"PENDING",
        typee:selectedValue
    }
    console.log(user)

    let response = await fetch(url + "reimbursement/submit", {

        method: "POST", 
        body: JSON.stringify(user), 
        credentials: "include"
    });

    if(response.status === 201) {
        document.getElementById("text").innerText="REIMBURSEMENT SUCCESSFULLY ADDED";
         window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/financemanager.html";}, 3000);

    } else {
        document.getElementById("text").innerText="REIMBUSEMENT SUBMISSION FAILED! TRY AGAIN";
        window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/submit2.html";}, 3000);
    }
}

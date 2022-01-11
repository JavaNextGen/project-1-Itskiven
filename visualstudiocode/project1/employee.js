const url = "http://localhost:3000/"

document.getElementById("logoutButton").addEventListener("click", logout);
// document.getElementById("submitButton").addEventListener("click", submit);
document.getElementById("viewPendingButton").addEventListener("click", pending);
// document.getElementById("viewResolvedButton").addEventListener("click", resolved);

function logout(){
    document.getElementById("text").innerText="GOING TO THE MAIN PAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/project1.html";}, 1500);
    }

function pending(){
    document.getElementById("text").innerText="PENDING REIMBURSEMENTS";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/pending.html";}, 1500);
    }
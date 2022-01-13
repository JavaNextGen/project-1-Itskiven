const url = "http://localhost:3000/"

document.getElementById("logoutButton").addEventListener("click", logout);
document.getElementById("submitButton").addEventListener("click", submit);
document.getElementById("viewPendingButton").addEventListener("click", pending);
document.getElementById("viewResolvedButton").addEventListener("click", resolved);

function logout(){
    document.getElementById("text").innerText="GOING TO MAIN PAGE";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/project1.html";
    }

   function submit(){
    document.getElementById("text").innerText="REIMBURSEMENT REQUEST SUBMISSION";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/employee/submit.html";
    }    

function pending(){
    document.getElementById("text").innerText="PENDING REIMBURSEMENTS";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/employee/pending.html";
    }


function resolved(){
    document.getElementById("text").innerText="RESOLVED REIMBURSEMENTS";   
   window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/employee/resolved.html";
    }    
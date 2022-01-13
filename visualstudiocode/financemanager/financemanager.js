const url = "http://localhost:3000/"

document.getElementById("logoutButton").addEventListener("click", logout);
document.getElementById("view1Button").addEventListener("click", view1);
document.getElementById("submitButton").addEventListener("click", submit);
document.getElementById("ProcessButton").addEventListener("click", Process);
document.getElementById("view2Button").addEventListener("click", view2);
document.getElementById("view3Button").addEventListener("click", view3);

function logout(){
    // document.getElementById("text").innerText="GOING TO FINANCE MANAGER HOMEPAGE";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/project1.html";
    }

function view1(){
    // document.getElementById("text").innerText="GOING TO VIEW ALL WORKERS";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/viewworkers.html";
    }    

function submit(){
    // document.getElementById("text").innerText="GOING TO SUBMIT A REIMBURSEMENT REQUEST";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/submit2.html";
    }


function Process(){
    // document.getElementById("text").innerText="GOING TO PROCESS REIMBURSEMENT REQUESTS";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/process.html";
    }
    
function view2(){
    // document.getElementById("text").innerText="OWN PENDING REIMBURSEMENTS REQUESTS";   
    //  window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/pending2.html";}, 1500);
     window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/pending2.html";
    }    

function view3(){
    //  document.getElementById("text").innerText="GOING TO VIEW ALL REIMBURSEMENTS";   
    window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/viewALL.html";
    }    
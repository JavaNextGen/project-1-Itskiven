const url = "http://localhost:3000/"

document.getElementById("cancelButton").addEventListener("click", cancel);
document.getElementById("createButton").addEventListener("click", submit);

function cancel(){
    document.getElementById("text").innerText="GOING TO EMPLOYEE HOMEPAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/employee/employee.html";}, 1500);
    }

async function submit(){
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;
    let userfn = document.getElementById("fname").value;
    let userln = document.getElementById("lname").value;
    let userea = document.getElementById("email").value;
    let selectedValue = document.getElementById("currentrole").value;
    

    let user = {
        username:usern,
        password:userp,
        fname:userfn,
        lname:userln,
        email:userea,
        role: selectedValue
    }
    console.log(user)

    let response = await fetch(url + "user/register", {

        method: "POST", 
        body: JSON.stringify(user), 
        credentials: "include"
    });

    if(response.status === 201) {
        document.getElementById("text").innerText="USER SUCCESSFULLY ADDED";
         window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/project1.html";}, 3000);

    } else {
        document.getElementById("text").innerText="USERNAME ALREADY EXIST! - PLEASE TRY AGAIN";
        window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/register.html";}, 3000);
    }
}

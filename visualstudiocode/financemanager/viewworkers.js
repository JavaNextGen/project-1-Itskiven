const url = "http://localhost:3000/"

// document.getElementById("wholebody").addEventListener("onload", pending);
window.addEventListener("load", worker);
document.getElementById("backButton").addEventListener("click", back);

function back(){
    document.getElementById("text").innerText="GOING TO FINANCE MANAGER HOMEPAGE";   
    window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/financemanager.html";}, 1500);
    }

async function worker() {

    let response = await fetch (url + "user");

    if (response.status === 200) {

        let data = await response.json();
        console.log(data);

        for(let user of data) {
            
            let row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = user.id;
            row.appendChild(cell);

            let cell2 = document.createElement("td");
            cell2.innerHTML = user.fname;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = user.lname;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = user.role;
            row.appendChild(cell4);

            document.getElementById("userBody").appendChild(row);

            
        }
    }


}
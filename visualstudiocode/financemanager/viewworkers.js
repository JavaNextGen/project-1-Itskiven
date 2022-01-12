const url = "http://localhost:3000/"

// document.getElementById("wholebody").addEventListener("onload", pending);
window.addEventListener("load", worker);
document.getElementById("backButton").addEventListener("click", back);
document.getElementById("stringorint").addEventListener("change", typing);

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

async function typing() {


    document.getElementById("userBody").innerHTML=""; 

    if (document.getElementById("search").value == "USERID") {
        console.log("USER ID")
        let inputprovided = document.getElementById("stringorint").value;
        let response = await fetch (url + "user/id/" + inputprovided);

        if (response.status === 200) {

            let data = await response.json();
            console.log(data);

                let row = document.createElement("tr");
    
                let cell = document.createElement("td");
                cell.innerHTML = data.value.id;
                row.appendChild(cell);
    
                let cell2 = document.createElement("td");
                cell2.innerHTML = data.value.fname;
                row.appendChild(cell2);
    
                let cell3 = document.createElement("td");
                cell3.innerHTML = data.value.lname;
                row.appendChild(cell3);
    
                let cell4 = document.createElement("td");
                cell4.innerHTML = data.value.role;
                row.appendChild(cell4);
    
                document.getElementById("userBody").appendChild(row);
                
            } else if (response.status === 404){
                alert("ID DOES NOT EXIST");
            }

    } else if (document.getElementById("search").value == "USERNAME") {

        console.log("USERNAME!")
        let inputprovided = document.getElementById("stringorint").value;
        let response = await fetch (url + "user/" + inputprovided);

        if (response.status === 200) {

           
            let data = await response.json();
            console.log(data);
            
                let row = document.createElement("tr");
    
                let cell = document.createElement("td");
                cell.innerHTML = data.value.id;
                row.appendChild(cell);
    
                let cell2 = document.createElement("td");
                cell2.innerHTML = data.value.fname;
                row.appendChild(cell2);
    
                let cell3 = document.createElement("td");
                cell3.innerHTML = data.value.lname;
                row.appendChild(cell3);
    
                let cell4 = document.createElement("td");
                cell4.innerHTML = data.value.role;
                row.appendChild(cell4);
    
                document.getElementById("userBody").appendChild(row);
    
                
            }
        
    } else {
        console.log("NONE")
    }

    // let response = await fetch (url + "reimbursement/" + actualuser);

    // if (response.status === 200) {

    //     let data = await response.json();
    //     console.log(data);

    //     for(let reimbursement of data) {
            
    //         let row = document.createElement("tr");

    //         let cell = document.createElement("td");
    //         cell.innerHTML = reimbursement.id;
    //         row.appendChild(cell);

    //         let cell2 = document.createElement("td");
    //         cell2.innerHTML = reimbursement.amount;
    //         row.appendChild(cell2);

    //         let cell3 = document.createElement("td");
    //         cell3.innerHTML = reimbursement.typee;
    //         row.appendChild(cell3);

    //         let cell4 = document.createElement("td");
    //         cell4.innerHTML = reimbursement.status;
    //         if (cell4.innerHTML == "PENDING") {
    //             cell4.style.backgroundColor = "yellow";
    //         } else{
    //             cell4.style.backgroundColor = "red";
    //         }
    //         row.appendChild(cell4);

    //         document.getElementById("userBody").appendChild(row);

            
    //     }
    // }

}
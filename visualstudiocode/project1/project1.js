const url = "http://localhost:3000/" //putting our base URL in a variable for cleaner code below
//eventually, we'll use this in our fetch requests and make calls to our server by appending endpoints

//add eventListeners to our buttons to give them functionality
// document.getElementById("getUsersButton").addEventListener("click", getUsers);
// const loginbutton = document.querySelector("loginButton");

document.getElementById("loginButton").addEventListener("click", login);
document.getElementById("registerButton").addEventListener("click", register);

//this function will send the user-inputted login credentials to our server
async function login() {

    //gather the user inputs from the login inputs
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

  

    //we want to send the user/pass as JSON, so we need a JS object to send
    let user = {
        username:usern,
        password:userp
    }

    // export {user};
    //This object will reflect our DTO in Java... This is the data we want to transfer!
    console.log(user)

    //fetch request to the server
    //remember the second parameter fetch can take? It's essentially for configuring our fetch request
    //fetch sends a GET by default, but this seconds parameter can change that and more!

    let response = await fetch(url + "login", {

        method: "POST", //send a POST request (would be a GET if we didn't do this...)
        body: JSON.stringify(user), //turn our user object into JSON
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a user session)
        //future fetches will also require this "include" value to send the cookie back
    });


    
    // let response = await fetch(url + "login", {

    //     method: "POST", //send a POST request (would be a GET if we didn't do this...)
    //     body: JSON.stringify(user), //turn our user object into JSON
    //     credentials: "include"
    //     //this last line will ensure that the cookie is captured (so that we can have a user session)
    //     //future fetches will also require this "include" value to send the cookie back
    // });


    //control flow based on successful/unsuccessful login
    if(response.status === 200) {
        document.getElementById("text").innerText="Welcome!!!";
         window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/employee/employee.html";}, 3000);
     
    } else if (response.status === 202){
        document.getElementById("text").innerText="Welcome!!!";
        window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/financemanager/financemanager.html";}, 3000);
     
    }
    else {
        document.getElementById("text").innerText="LOGIN FAILED! PAGE WILL AUTOMATICALLY REFRESH";
        window.setTimeout(function(){window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/project1.html";}, 3000);
    }

    localStorage.setItem("actualuser", usern);

    let response2 = await fetch(url + "user/author/" + usern);
    let data2 = await response2.json();
    localStorage.setItem("intauthor", data2);

    
}

function register(){
// document.getElementById("text").innerText="REGISTRATION";   
window.location.href = "file:///C:/Users/admin/OneDrive/Desktop/PROJECT%201/project-1-Itskiven/visualstudiocode/project1/register.html";
}
//login button listener goes here

//remember, async returns a promise (which fetch request return)
// async function getUsers() {

    //we will send a fetch request to get our employee data
    //by default, fetch requests send GET requests
    // let response = await fetch(url + "user");

    //logging the response in the console just to see the response object
    // console.log(response);

    //control flow for is the request is successful
    // if(response.status === 200){

        // let data = await response.json(); //parse the JSON data from the response into a JS object

    //     //logging the actual employee data that has been parsed from JSON -> JS
        // console.log(data);

    //     //For every Employee object we got back (stored in the data variable), put it in the table
        // for(let employee of data){

    //         //create a table row
            // let row = document.createElement("tr");

    //         //create a data cell for each employee field
            // let cell = document.createElement("td");
    //         //fill the cell with the appropriate employee data
            // cell.innerHTML = employee.id;
    //         //add the td element (data cell) to the tr element (table row)
            // row.appendChild(cell);

    //         //we'll do this^ for every column in employees

            // let cell2 = document.createElement("td");
            // cell2.innerHTML = employee.fname;
            // row.appendChild(cell2);

            // let cell3 = document.createElement("td");
            // cell3.innerHTML = employee.lname;
            // row.appendChild(cell3);

            // let cell4 = document.createElement("td");
            // cell4.innerHTML = employee.role;
            // row.appendChild(cell4);

    //         //append the tr called row that we created in the for loop to the table body
    //         //a new row will be appended for every employee object that comes in
//             document.getElementById("userBody").appendChild(row);
//         }

//     }


// }


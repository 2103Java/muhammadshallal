(function() {
    document.getElementById("sidebarCollapse").addEventListener("click", function() {
        document.querySelector("#sidebar").classList.toggle("active");
        document.querySelector("#content").classList.toggle("active");
        document.querySelector(".collapse").classList.toggle("in");
    });

    function getNumOfEmployees() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this);
                document.getElementById("empCount").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "http://localhost:8080/ERS/get/employee/count", true);
        xhttp.send();
    }

    function getNumOfClaims() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("claimCount").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "http://localhost:8080/ERS/get/claim/count", true);
        xhttp.send();
    }

    getNumOfEmployees();
    getNumOfClaims();

})();
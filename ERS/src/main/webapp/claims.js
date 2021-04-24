(function() {
    document.getElementById("sidebarCollapse").addEventListener("click", function() {
        document.querySelector("#sidebar").classList.toggle("active");
        document.querySelector("#content").classList.toggle("active");
        document.querySelector(".collapse").classList.toggle("in");
    });

    function getAllClaims() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
            }
        };
        xhttp.open("GET", "http://localhost:8080/ERS/all/claim", true);
        xhttp.send();
    }

})();
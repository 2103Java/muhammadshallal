(function() {
    document.getElementById("sidebarCollapse").addEventListener("click", function() {
        document.querySelector("#sidebar").classList.toggle("active");
        document.querySelector("#content").classList.toggle("active");
        document.querySelector(".collapse").classList.toggle("in");
    });

    function getNumOfClaims(filter1, filter2) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (filter2 == 'all')
                    document.getElementById("claimCount").innerHTML = ":" + this.responseText;
                else document.getElementById("reimCount").innerHTML = ":" + this.responseText;
            }
        };
        xhttp.open("GET", "http://localhost:8080/ERS/get/empclaim/count?filter1="+filter1+"&filter2="+filter2, true);
        xhttp.send();
    }

    getNumOfClaims('all','all');
    getNumOfClaims('all', 'approved');

})();
(function() {
    document.getElementById("sidebarCollapse").addEventListener("click", function() {
        document.querySelector("#sidebar").classList.toggle("active");
        document.querySelector("#content").classList.toggle("active");
        document.querySelector(".collapse").classList.toggle("in");
    });
    
    document.getElementById("typeSelect").addEventListener("change", function () {
        getAllClaims(document.getElementById("typeSelect").value, document.getElementById("statusSelect").value);
    });

    document.getElementById("statusSelect").addEventListener("change", function () {
        getAllClaims(document.getElementById("typeSelect").value, document.getElementById("statusSelect").value);
    });
    
    function getAllClaims(filter1, filter2) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("claimTable").innerHTML = this.responseText;
            }
        };
        xhttp.open("POST", "http://localhost:8080/ERS/show/employee/reimbursments?filter1="+filter1+"&filter2="+filter2, true);
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send();
    }
    
    getAllClaims('all', 'all');
})();
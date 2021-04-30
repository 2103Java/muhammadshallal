(function() {
    document.getElementById("sidebarCollapse").addEventListener("click", function() {
        document.querySelector("#sidebar").classList.toggle("active");
        document.querySelector("#content").classList.toggle("active");
        document.querySelector(".collapse").classList.toggle("in");
    });

    document.getElementById("typeSelect").addEventListener("change", function () {
    	document.getElementById("statusSelect").value = 'all';
        getAllClaims(document.getElementById("typeSelect").value);
    });

    document.getElementById("statusSelect").addEventListener("change", function () {
    	document.getElementById("typeSelect").value = 'all';
        getAllClaims(document.getElementById("statusSelect").value);
    });

    document.getElementById("approve").addEventListener("click", function () {
        setStatus(document.getElementById("reimID").value, "approved");
    });

    document.getElementById("deny").addEventListener("click", function () {
        setStatus(document.getElementById("reimID").value, "denied");
    });

    document.getElementById("reimID").addEventListener("keyup", function() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseText=="pending") {
                    document.getElementById("approve").disabled = false;
                    document.getElementById("deny").disabled = false;
                    document.getElementById("errorMsg").innerHTML = "";
                }
                else {
                    document.getElementById("approve").disabled = true;
                    document.getElementById("deny").disabled = true;
                    if (this.responseText == "approved" || this.responseText == "denied")
                        document.getElementById("errorMsg").innerHTML = "This claim is already "+ this.responseText;
                    else document.getElementById("errorMsg").innerHTML = this.responseText;
                }
            }
        };
        xhttp.open("POST", "http://localhost:8080/ERS/claim/status?id="+document.getElementById("reimID").value, true);
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send();
    });

    function getAllClaims(filter) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("claimTable").innerHTML = this.responseText;
            }
        };
        xhttp.open("POST", "http://localhost:8080/ERS/all/claim?filter="+filter, true);
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send();
    }

    function setStatus (id, status) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
            	swal("Success", "Claim "+status, "success").then(() => {
            		getAllClaims('all');
            	})
                
            }
        };
        xhttp.open("POST", "http://localhost:8080/ERS/set/status?id="+id+"&status="+status, true);
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send();
    }
    
    getAllClaims('all');

})();
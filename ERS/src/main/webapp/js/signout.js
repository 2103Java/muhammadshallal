(function(){

    document.getElementById("signOut").addEventListener("click", () => {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                window.location.href = "../html/login.html";
            }
        };
        xhttp.open("GET", "http://localhost:8080/ERS/logout", true);
        xhttp.send();
    });

})();
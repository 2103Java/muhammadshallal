(function() {
    document.getElementById("sidebarCollapse").addEventListener("click", function() {
        document.querySelector("#sidebar").classList.toggle("active");
        document.querySelector("#content").classList.toggle("active");
        document.querySelector(".collapse").classList.toggle("in");
    })
})();
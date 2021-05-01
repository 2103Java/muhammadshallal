(function(){
    document.getElementById('pass2').addEventListener("keyup", function() {
        let pass1 = document.getElementById('pass1');
        let pass2 = document.getElementById('pass2');
        let bt = document.getElementById('register');
        if (pass1.value != pass2.value) 
            bt.disabled = true;
        else bt.disabled = false;
    });
})();
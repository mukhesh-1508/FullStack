 const added = () =>
    {
        if(
            validatefname(document.getElementById("firstName")) &&
            validatefname(document.getElementById("lastName"))&&
            validateemail(document.getElementById("email"))&&
            validatephoneno(document.getElementById("phoneNo")) &&
            validateaddress(document.getElementById("address")) &&
            validateDate(document.getElementById("dateOfBirth"))
        )
        {
        document.getElementById("userForm").submit();

        let message = document.getElementById("sbtn").innerHTML;
        message += message === "Add" ? "ed" : "d";
               alert("User data " + message + " successfully");

        }


    }
    const validateDate = (event) => {
    if(event.value === ""){
    alert("DOB Required");
    return false;
    }
    return true;
    }
    const handleAddBtn = () => {
        console.log("hi")
        document.getElementById("form-title").innerHTML = "Add Employee"
        document.getElementById("sbtn").innerHTML = "Add"
        document.getElementById("table").classList.add("opacity-5");
        document.getElementById("container").classList.add("display-block");
    }
    function validatefname(fname){
        console.log("yet to reach"+fname.value);
         let samp = fname.value;
         let regName = /[0-9]/;
         let test=samp.replaceAll(" ","");
         if (samp == "" || regName.test(samp) || samp.length!=test.length){
            window.alert("Please enter your firstname properly.");
            return false;
        }
        return true;
       }
         function validatelname(lname){
             let samp1 = lname.value;
             var regName1 = /[0-9]/;
              let test=samp1.replaceAll(" ","");
             if (samp1 == "" || regName1.test(samp1) || samp1.length!=test.length){
            window.alert("Please enter your lastname properly.");
            return false;
            }
             return true;
           }

          function validateemail(email){
            let samp2=email.value;
            var regEmail=/^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
            if(samp2== "" || !regEmail.test(samp2)){
            window.alert("Please enter your valid email");
            return false;
            }
            return true;
         }
        function validatephoneno(phNo){
            let samp3=phNo.value;
            var regPhone=  /^(\d{3})[- ]?(\d{3})[- ]?(\d{4})$/;
            if(samp3== "" || !regPhone.test(samp3)){
            window.alert("Please enter your valid PhoneNumber");
            return false;
            }else{
            return true;
            }
         }
         function validateaddress(address){
             let adds=address.value;
             if(adds==""){
             window.alert("Please enter your valid address");
             return false;
             }
             else{
             return true;
             }
         }
        function limitDate(){
            let today = new Date().toISOString().split("T")[0];
            document.getElementById("dateOfBirth").setAttribute("max",today);
        }

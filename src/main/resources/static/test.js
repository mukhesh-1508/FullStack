//console.log("test.js");
(function () {
    // "it" function defines the test case
    function it(desc, func) {
        //encapsulate the func call in try/catch block so that testing does not stop if one test fails
        try {
            func();
            // If the test case passes then log the test case description in the browser console with a checkmark
            console.log('\x1b[32m%s\x1b[0m', '\u2714 ' + desc);
        } catch (error) {
            // log the error on the console with an 'x'
            console.log('\n');
            console.log('\x1b[31m%s\x1b[0m', '\u2718 ' + desc);
            console.error(error);
            console.log('\n');
        }
    }

    function assert(isTrue) {
        if (!isTrue) {
            throw new Error();
        }
    }

    it('Add User button functionality Should Pass', function handleAddBtn() {
        assert((document.getElementById("add-btn").innerHTML).includes("Add"));
    });
    it('First name Should Pass', function validatefname() {
    document.getElementById("firstName").value = "Mukesh";
    assert(document.getElementById("firstName").value !== "")
    });
    it('Empty First name should not Pass', function validatefname() {
    document.getElementById("firstName").value = "";
       assert(document.getElementById("firstName").value === "")

    });
    it('Last name Should Pass', function validatelname() {
        document.getElementById("lastName").value = "Ganesan";
        assert(document.getElementById("lastName").value !== "")
        });
        it('Empty Last name should not Pass', function validatelname() {
        document.getElementById("lastName").value = "";
           assert(document.getElementById("lastName").value === "")

        });
    it('Email Should Pass', function validateemail() {
    var regEmail=/^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
        assert(regEmail.test("mukheshg@gmail.com"));
    });
    it('Invalid Email Should Not Pass', function validateemail() {
    var regEmail=/^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
        assert(!regEmail.test("mukheshg@gmail,com"));
    });
    it('Mobile Number Should Pass', function validatephoneno() {
            var regPhone=  /^(\d{3})[- ]?(\d{3})[- ]?(\d{4})$/;
            assert(regPhone.test("1212121212"));
    });
    it('Invalid Mobile Number not Should Pass', function checkPhno() {
            var regPhone=  /^(\d{3})[- ]?(\d{3})[- ]?(\d{4})$/;
            assert(!regPhone.test(""));
    });
    it('Address Should Pass', function checkAdd() {
    document.getElementById("address").innerText = "My address";
        assert(document.getElementById("address").innerText !== "");
    });
    it('Invalid Address not Should Pass', function checkAdd() {
      document.getElementById("address").innerText = "";
            assert(document.getElementById("address").innerText === "");
    });


})();
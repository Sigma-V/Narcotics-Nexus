

function VendorSignUp() {
    return (
        <div>
            <h1>Create account (Vendor)</h1>
            <label htmlFor={"username"}>Username:</label>
            <input type={"text"} id={"username"} required/>
            <br/>
            <label htmlFor={"Password"}>Password:</label>
            <input type={"text"} id={"Password"} required/>
            <br/>
            <label htmlFor={"LicenseID"}>LicenseID:</label>
            <input type={"text"} id={"LicenseID"} required/>
            <br/>
            <label htmlFor={"PancardID"}>PancardID:</label>
            <input type={"text"} id={"PancardID"} required/>
            <br/>
            <label htmlFor={"Address"}>Address:</label>
            <input type={"text"} id={"Address"} required/>
            <br/>
            <label htmlFor={"CompanyName"}>CompanyName:</label>
            <input type={"text"} id={"CompanyName"} required/>
            <br/>
            <label htmlFor={"City"}>City:</label>
            <input type={"text"} id={"City"} required/>
            <br/>
            <label htmlFor={"PhoneNumber"}>PhoneNumber:</label>
            <input type={"text"} id={"PhoneNumber"} required/>
            <br/>
            <label htmlFor={"EmailId"}>EmailId:</label>
            <input type={"text"} id={"EmailId"} required/>
            <br/>
            <button>Create Account</button>
        </div>
    );
}

export default VendorSignUp;
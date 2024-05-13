import {useNavigate} from "react-router-dom";


function SignUp() {
    const navigate=useNavigate();
    function customerHandle() {
        navigate('/sign/customer',{replace:true});
    }

    function vendorHandle() {
        navigate('/sign/vendor',{replace:true});
    }

    return (

        <div>
            <h1>Sign up page</h1>
            <button onClick={customerHandle}>Customer</button>
            <button onClick={vendorHandle}>Vendor</button>
        </div>
    );
}

export default SignUp;
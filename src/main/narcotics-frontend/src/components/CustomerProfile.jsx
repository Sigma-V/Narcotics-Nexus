import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import "../css/Profile.scss";
import BalancePopup from "./BalancePopup.jsx";

function CustomerProfile() {
    const navigate=useNavigate();
    const {username}=useParams();
    const [customer, setCustomer] = useState({});
    const [wallet, setWallet] = useState({});
    const [popTrigger, setPopTrigger] = useState(false);
    function goBack() {
        navigate(`/customer/${username}/home`,{replace:true});
    }

    useEffect(() => {
        getCustomer().then();
        getWallet().then();
    }, [popTrigger]);

    function walletHandler(walletDetails){
        const walletDiv = document.querySelector(".wallet");
        const walletButton = document.querySelector("#wallet-button");
        console.log(walletDetails.id);
        if(walletDetails.id===0){
            walletDiv.classList.add("hidden");
            walletButton.classList.remove("hidden");
        }
        else{
            walletButton.classList.add("hidden");
            walletDiv.classList.remove("hidden");
        }
    }

    async function getWallet(){
        const walletResponse = await fetch(`http://localhost:8080/user/customer/${username}/wallet`)
        const walletDetails=await walletResponse.json();
        console.log(walletDetails);
        setWallet(walletDetails);
        walletHandler(walletDetails);
    }
    async function getCustomer(){
        const customerResponse= await fetch(`http://localhost:8080/user/customer/${username}`);
        const customerDetails= await customerResponse.json();
        console.log(customerDetails);
        setCustomer(customerDetails);
    }

    return (
        <div className={"profile"}>
            <button onClick={goBack}>Back</button>
            <h1>PROFILE</h1>
            <div className={"details"}>
                    <div>
                        Name: {customer.firstName} {customer.lastName}
                    </div>
                    <div>
                        Date of Birth: {customer.dob}
                    </div>
                    <div>
                        Nexus Points: {customer.nexusPoints}
                    </div>
            </div>
            <BalancePopup trigger={popTrigger} setTrigger={setPopTrigger} id={wallet.id} username={username}/>
            <h2>Wallet</h2>
            <div className={"wallet"}>
                <div>
                    Wallet ID: {wallet.id}
                </div>
                <div>
                    Balance: {wallet.balance}
                </div>
                <div>
                    Date of Creation: {wallet.dateOfCreation}
                </div>
                <button onClick={()=>setPopTrigger(true)}>Add Balance</button>
            </div>
            <button id={"wallet-button"}>Add Wallet</button>
        </div>
    );
}

export default CustomerProfile;
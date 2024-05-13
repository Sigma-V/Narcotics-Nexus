import React, {useState} from 'react';
import "../css/Popup.scss"
function BalancePopup(props) {
    const [amount, setAmount] = useState(0);
    const [message, setMessage] = useState("");
    function closePopup() {
        props.setTrigger(false);
    }
    function amountHandler(event){
        setAmount(event.target.value);
    }

    function balanceHandler(){
        if(amount<=0){
            setMessage("Invalid Amount!!!");
            return;
        }
        setMessage("");
        addBalance().then(()=>closePopup());
    }
    async function addBalance() {
        console.log(props.username);
        console.log(props.id);
        const balanceResponse = await fetch(`http://localhost:8080/user/customer/${props.username}/wallet/${props.id}/amount/${amount}`,{
            method:"PUT",
            headers:{
                "content-type":"application/json"
            }
        });
    }

    return props.trigger? (
        <div className={"popup"}>
            <div className={"popup-inner"}>
                <div>
                    Add Balance!!!
                </div>
                <div className={"error-message"}>{message}</div>
                <input type={"number"} onChange={amountHandler}/>
                <button onClick={balanceHandler}>Add Balance</button>
                <button onClick={closePopup}>close</button>
            </div>
        </div>
    ):"";
}

export default BalancePopup;
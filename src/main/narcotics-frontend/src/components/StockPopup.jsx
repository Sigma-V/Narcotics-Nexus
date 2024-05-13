import {useState} from "react";
import "../css/Popup.scss";

function StockPopup(props) {
    const [quantity, setQuantity] = useState(0);
    const [message, setMessage] = useState("");

    function handleQuantityChange(event) {
        setQuantity(event.target.value);
    }

    async function putStock(stock) {
        const stockResponse=await fetch(`http://localhost:8080/user/vendor/${props.username}/product/${props.productId}/restock/${stock}`,{
            method:"PUT",
            headers:{
                "content-type":"application/json"
            }
        });
    }

    function addStock(){
        if(quantity<=0){
            setMessage("Enter valid quantity");
            return;
        }
        setMessage("");
        console.log(props.productId);
        console.log(props.username);
        putStock(quantity).then(()=>{
            console.log("stock added! ", quantity);
            closePopup();
        });
    }


    function closePopup() {
        props.setTrigger(false);
    }

    return (props.trigger)?(
        <div className={"popup"}>
            <div className={"popup-inner"}>
                <div className="error-message">{message}</div>
                <label htmlFor={"quantity"}>Quantity:</label>
                <input name={"quantity"} type={"number"} value={quantity} onChange={handleQuantityChange}/>
                <button onClick={addStock}>Add to stock</button>
                <button onClick={closePopup}>close</button>
            </div>
        </div>
    ):"";
}

export default StockPopup;
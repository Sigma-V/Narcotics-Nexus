import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

function Cart() {
    const {username}=useParams();
    const navigate=useNavigate();
    const [wallet, setWallet] = useState({});
    const [message, setMessage] = useState("")
    const [itemList, setItemList] = useState([{
        "cartId": 2,
        "customer": {
            "username": "",
            "membership": false,
            "nexusPoints": 0,
            "firstName": "Joe",
            "lastName": "Biden",
            "aadharNum": "1234567890",
            "dob": "1999-08-03",
            "phoneNum": "1234567890",
            "emailId": "sample@g.com",
            "upiId": "12345",
            "address": "sample, address",
            "login": null
        },
        "product": {
            "productId": -1,
            "productName": "",
            "cost": 0,
            "weight": 200.0,
            "image": null,
            "length": 30.0,
            "width": 30.0,
            "height": 1.0,
            "category": "painkiller",
            "vendor": {
                "username": "SampleVendorTwo",
                "licenseId": null,
                "panCardId": null,
                "address": null,
                "companyName": "SampleVendorTwo",
                "phoneNumber": null,
                "emailId": null,
                "city": null,
                "login": null
            }
        },
        "quantity": 0
    }])
    function goBack() {
        navigate(`/customer/${username}/home`,{replace:true});
    }

    async function getCartItems(){
        const cartResponse = await fetch(`http://localhost:8080/user/customer/${username}/cart`);
        let items = await cartResponse.json();
        console.log(items);
        setItemList(items);
    }

    useEffect(() => {
        getCartItems().then();
        getWalletDetails().then();
    }, []);

    function removeItem(index){
        console.log("delete "+index);
        deleteCartItem(index).then(()=>getCartItems().then());
    }
    async function deleteCartItem(index){
        const deleteResponse = await fetch(`http://localhost:8080/user/customer/${username}/cart/item/${itemList[index].cartId}`,{
            method:"DELETE",
            headers:{
                "content-type":"application/json"
            }
        });
    }

    function checkout() {
        let cost=0;
        itemList.forEach((item)=>{
            cost+=(item.quantity*item.product.cost);
        })
        if (cost>wallet.balance){
            setMessage("Not enough Funds!!!!")
            return
        }
        setMessage("");
        buyCartItems().then(()=>{
            getCartItems().then();
        })
    }

    async function buyCartItems(){
        const cartResponse = await fetch(`http://localhost:8080/user/customer/${username}/order/cart`,{
            method:"POST",
            headers:{
                "content-type":"application/json"
            }
        });
    }
    async function getWalletDetails(){
        const walletResponse = await fetch(`http://localhost:8080/user/customer/${username}/wallet`)
        const walletDetails=await walletResponse.json();
        console.log(walletDetails);
        setWallet(walletDetails);
    }

    return (
        <div>
            <button onClick={goBack}>back</button>
            <h1>Cart Items</h1>
            <ol className={"product-list"}>
                {itemList.map((item,index)=> <li className={"product-list-item"} key={index}>
                    <div>Name: {item.product.productName}</div>
                    <div>Cost: {item.product.cost * item.quantity}</div>
                    <div>Quantity: {item.quantity}</div>
                    <button  onClick={()=>removeItem(index)}>Remove</button>
                </li>)}
            </ol>
            <div>{message}</div>
            <button onClick={checkout}>checkout</button>
        </div>
    );
}

export default Cart;
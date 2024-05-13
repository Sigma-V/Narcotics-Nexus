import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";

function PastOrders() {
    const{username} = useParams();
    const navigate =useNavigate();
    const [orderList, setOrderList] = useState([{
        "orderId": 3,
        "product": {
            "productId": 2,
            "productName": "Satmola",
            "cost": 5.0,
            "weight": 10.0,
            "image": null,
            "length": 10.0,
            "width": 1.0,
            "height": 1.0,
            "category": "stomach",
            "vendor": {}
        },
        "customer": {
            "username": "SampleCustomer",
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
        "quantity": 5,
        "dateOfOrder": "2024-03-31"
    }]);

    async function getPastOrders(){
        const orderResponse = await fetch(`http://localhost:8080/user/customer/${username}/orders`);
        let orders= await orderResponse.json();
        console.log(orders)
        setOrderList(orders);
    }

    useEffect(() => {
        getPastOrders().then();
    }, []);

    function goBack() {
        navigate(`/customer/${username}/home`,{replace:true});
    }

    return (
        <div>
            <h1>Past Orders</h1>
            <ol className={"product-list"}>
                {orderList.map((order,index)=><li key={index} className={"product-list-item"}>
                    <div>Name: {order.product.productName}</div>
                    <div>Cost: {order.product.cost*order.quantity}</div>
                    <div>Quantity: {order.quantity}</div>
                    <div>Date: {order.dateOfOrder}</div>
                </li>)}
            </ol>
            <button onClick={goBack}>back</button>
        </div>
    );
}

export default PastOrders;
import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

function CustomerHome() {
    const [productList, setProductList] = useState([])
    const {username}=useParams();
    const navigate=useNavigate();
    async function displayProducts(){
        const productsResponse = await fetch('http://localhost:8080/products');
        let products = await productsResponse.json();
        setProductList([...products]);
        console.log(products)
    }

    useEffect(() => {
        displayProducts().then();
    }, []);

    function logOut() {
        navigate('/',{replace:true});
    }

    function showProduct(productId) {
        navigate(`/customer/${username}/product/${productId}`,{replace:true});
    }

    function pastOrder() {
        navigate(`/customer/${username}/past-orders`);
    }

    function goCart() {
        navigate(`/customer/${username}/cart`,{replace:true});
    }

    function goProfile() {
        navigate(`/customer/${username}/profile`,{replace:true});
    }

    return (
        <div>
            <h1>Home Page</h1>
            <h3>Name: {username}</h3>
            <button onClick={goProfile}>profile</button>
            <button onClick={goCart}>cart</button>
            <button onClick={pastOrder}>Past Orders</button>
            <br/>
            <button onClick={logOut}>Logout</button>
            <br/>
            <ul className={"product-list"}>
                {productList.map((product,index) => {
                    if(product.stock>0) {
                        return (
                            <li key={index} id={product.productId} className={"product-list-item"}
                                onClick={() => showProduct(product.productId)}>
                                <div>
                                    Name: {product.productName}
                                </div>
                                <div>
                                    Cost: {product.cost}
                                </div>
                            </li>
                        );
                    }
                })}
            </ul>
        </div>
    );
}

export default CustomerHome;
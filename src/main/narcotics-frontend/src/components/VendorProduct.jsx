import { useEffect, useState} from "react";
import { useNavigate, useParams } from "react-router-dom";

function VendorProduct(){
    const{username, productId} = useParams();
    const navigate = useNavigate();
    const [product, setProduct] = useState({
        productName:"Product",
        sale:0,
        earnings:0
    });

    async function getStats(){
        const statsResponse = await fetch(`http://localhost:8080/user/vendor/${username}/product/${productId}/stats`);
        let productStats = await statsResponse.json();
        setProduct(productStats);
    }

    useEffect(() => {
        getStats().then();
    },[]);

    function goBack(){
        console.log("clicked");
        navigate(`/vendor/${username}/home`,{replace:true});
    }

    return(
        <div>
            <h1>{product.productName}</h1>
            <h2>Sell stats</h2>
            <ul className={"product-list"}>
                <li className={"product-list-item"}>
                    Name: {product.productName}
                </li>
                <li className={"product-list-item"}>
                    Sale: {product.sale}
                </li>
                <li className={"product-list-item"}>
                    Earnings: {product.earnings}
                </li>
            </ul>
            <button onClick={goBack}>Back</button>
        </div>
    );
}

export default VendorProduct;
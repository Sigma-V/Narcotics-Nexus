import {useNavigate, useParams} from "react-router-dom";
import {useState} from "react";


function AddProduct() {
    const {username}=useParams();
    const [productName, setProductName] = useState(null);
    const [cost, setCost] = useState(null);
    const [weight, setWeight] = useState(null)
    const [length, setLength] = useState(null);
    const [width, setWidth] = useState(null);
    const [height, setHeight] = useState(null);
    const [category, setCategory] = useState(null);
    const navigate =useNavigate();

    function addHandler() {
        console.log("button clicked");
        if(productName!=null){
            postProduct().then(()=>navigate(`/vendor/${username}/home`));
        }
        else{
            console.log("enter name")
        }
    }
    async function postProduct(){
        const addProductResponse= await fetch(`http://localhost:8080/user/vendor/${username}/product`,{
            method:"POST",
            headers:{
                "content-type":"application/json"
            },
            body: JSON.stringify({
                productName:productName,
                cost: cost,
                weight:weight,
                length:length,
                width:width,
                height:height,
                category:category
            })
        });
    }

    function changeProductName(event){
        setProductName(event.target.value);
    }
    function changeCost(event){
        setCost(event.target.value);
    }
    function changeWeight(event){
        setWeight(event.target.value);
    }
    function changeLength(event){
        setLength(event.target.value);
    }
    function changeWidth(event){
        setWidth(event.target.value);
    }
    function changeHeight(event){
        setHeight(event.target.value);
    }
    function changeCategory(event){
        setCategory(event.target.value);
    }


    return (
        <div>
            <h1>Add Product</h1>
            <h3>name: {username}</h3>
            <label htmlFor={"ProductName"}>ProductName:</label>
            <input type={"text"} id={"ProductName"} onChange={changeProductName} required/>
            <br/>
            <label htmlFor={"Cost"}>Cost:</label>
            <input type={"number"} id={"Cost"} onChange={changeCost} required/>
            <br/>
            <label htmlFor={"Weight"}>Weight:</label>
            <input type={"number"} id={"Weight"} onChange={changeWeight} required/>
            <br/>
            <label htmlFor={"Length"}>Length:</label>
            <input type={"number"} id={"Length"} onChange={changeLength} required/>
            <br/>
            <label htmlFor={"Width"}>Width:</label>
            <input type={"number"} id={"Width"} onChange={changeWidth} required/>
            <br/>
            <label htmlFor={"Height"}>Height:</label>
            <input type={"number"} id={"Height"} onChange={changeHeight} required/>
            <br/>
            <label htmlFor={"Category"}>Category:</label>
            <input type={"text"} id={"Category"} onChange={changeCategory} required/>
            <br/>
            <button onClick={addHandler}>Add Product</button>
        </div>
    );
}

export default AddProduct;
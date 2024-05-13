// import React from 'react';

import {useNavigate} from "react-router-dom";
import {useState} from "react";
import '../css/Login.scss';

function Login() {
    const navigate= useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [numIncorrectPassword, setNumIncorrectPassword] = useState(0);
    let loginDetails={username:"",pass:"",userType:""};
    function changeUsername(event){
        setUsername(event.target.value);
    }
    function changePassword(event){
        setPassword(event.target.value);
    }

    function sign(){
        navigate("/sign");
    }

    function login() {
        getLoginDetails().then(()=>{
            console.log(loginDetails);
            if(loginDetails.username==null){
                setMessage("Such user doesn't exist!");
                return;
            }
            else if(loginDetails.blocked){
                setMessage("Your account is blocked!");
                return;
            }
            else if (loginDetails.pass!==password){
                setMessage("Wrong Password");
                setNumIncorrectPassword(n=>++n);
                console.log(numIncorrectPassword);
                if(numIncorrectPassword>=3) {
                    blockAccount(loginDetails).then();
                }
                return;
            }
            else{
                setMessage("Correct Credentials");
                if(loginDetails.userType==="CUSTOMER"){
                    navigate(`/customer/${username}/home`);
                }
                else{
                    navigate(`/vendor/${username}/home`);
                }
            }
        });
    }
    async function blockAccount(loginDetails){
        loginDetails.blocked=true;
        console.log(loginDetails);
        const blockResponse = await fetch(`http://localhost:8080/login/customer`,{
            method:"PUT",
            headers:{
                "content-type":"application/json"
            },
            body:JSON.stringify(loginDetails)
        });
    }
    async function getLoginDetails(){
        const loginResponse = await fetch(`http://localhost:8080/login/${username}`);
        let login = await loginResponse.json();
        console.log(login);
        loginDetails=login;
    }

    return (
        <div>
            <div className={"login"}>
                <div className={"login-container"}>
                    <div id={"login-title"}>Log in</div>
                    <div id={"message"}>{message}</div>
                    <label htmlFor={"username"} className={"label"}>Username:</label>
                    <input id={"username"} type="text" className={"input"} onChange={changeUsername}/>
                    <label htmlFor={"password"} className={"label"}>Password:</label>
                    <input id={"password"} type="text" className={"input"} onChange={changePassword}/>
                    <button onClick={login} className={"submit-button"}>Sign IN</button>
                    <button onClick={sign} className={"sign-button"}>New to Narcotics Nexus?(Sign up)</button>
                </div>
            </div>
        </div>
    );
}

export default Login;
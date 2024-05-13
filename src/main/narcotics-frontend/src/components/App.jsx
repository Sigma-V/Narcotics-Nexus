import '../css/App.scss'
import Login from "./Login.jsx";
import {Route, Routes} from "react-router-dom";
import SignUp from "./SignUp.jsx";
import CustomerSignUp from "./CustomerSignUp.jsx";
import VendorSignUp from "./VendorSignUp.jsx";
import CustomerHome from "./CustomerHome.jsx";
import VendorHome from "./VendorHome.jsx";
import AddProduct from "./AddProduct.jsx";
import CustomerProduct from "./CustomerProduct.jsx";
import BoughtSuccesfully from "./BoughtSuccesfully.jsx";
import PastOrders from "./PastOrders.jsx";
import Cart from "./Cart.jsx";
import VendorProduct from './VendorProduct.jsx';
import CustomerProfile from "./CustomerProfile.jsx";

function App() {
  return(
      <div>
          <Routes>
              <Route path={"/"} element={<Login/>}></Route>
              <Route path={"/sign"} element={<SignUp/>}></Route>
              <Route path={"/sign/customer"} element={<CustomerSignUp/>}></Route>
              <Route path={"/sign/vendor"} element={<VendorSignUp/>}></Route>
              <Route path={"/customer/:username/home"} element={<CustomerHome/>}></Route>
              <Route path={"/customer/:username/profile"} element={<CustomerProfile/>}></Route>
              <Route path={"/customer/:username/past-orders"} element={<PastOrders/>}></Route>
              <Route path={"/customer/:username/cart"} element={<Cart/>}></Route>
              <Route path={"/customer/:username/product/:productId"} element={<CustomerProduct/>}></Route>
              <Route path={"/vendor/:username/home"} element={<VendorHome/>}></Route>
              <Route path={"/vendor/:username/product/:productId"} element={<VendorProduct/>}></Route>
              <Route path={"/vendor/:username/add-product"} element={<AddProduct/>}></Route>
              <Route path={"/bought"} element={<BoughtSuccesfully/>}></Route>
          </Routes>
      </div>
  )
}

export default App

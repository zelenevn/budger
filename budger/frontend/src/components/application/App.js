import './styles/App.css';
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";

//components imports
import Main from "../main/Main";
import About from "../about/About";
import Contacts from "../contacts/Contacts";
import Calculator from "../calculator/Calculator";
import SignUp from "../registration/SignUp";
import SignIn from "../login/SignIn";
import Account from "../account/Account";
import Logout from "../logout/Logout";
import Goals from "../goals/Goals";
import Recommendations from "../recommendations/Recommendations";
import Report from "../report/Report";

const App = () => {
    return (
        <div className="App">
            <Router>
                <Switch>
                    <Route exact path="/" component={Main}/>
                    <Route path="/about" component={About}/>
                    <Route path="/contacts" component={Contacts}/>
                    <Route path="/calculator" component={Calculator}/>
                    <Route path="/login" component={SignIn}/>
                    <Route path="/register" component={SignUp}/>
                    <Route path="/account" component={Account}/>
                    <Route path="/logout" component={Logout}/>
                    <Route path="/goals" component={Goals}/>
                    <Route path="/recommendations" component={Recommendations}/>
                    <Route path="/report" component={Report}/>
                </Switch>
            </Router>
        </div>
    );
}

export default App;

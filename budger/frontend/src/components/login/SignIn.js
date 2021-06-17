import React, {useState} from "react";
import {useStateValue} from "../../StateProvider";
import {useHistory} from "react-router-dom";
import style from "./styles/SignIn.module.css";
import Header from "../header/Header";
import {Link} from "react-router-dom";

const SignIn = () => {

    const [{}, dispatch] = useStateValue();
    const history = useHistory();

    const [login, setLogin] = useState("")
    const [password, setPassword] = useState("")
    const [isError, setIsError] = useState(false);

    const confirmLogin = async event => {
        event.preventDefault();

        const url = "https://budger-backend.herokuapp.com/login";

        const data = {
            login: login,
            password: password
        };
        await fetch(
            url,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            }
        ).then(response => {
            if (response.ok) {
                return response.json();
            }
        }).then(data => {
            const value = {
                login: data.login,
                token: data.token
            };
            dispatch({
                type: "LOGIN",
                login: value.login,
                token: value.token
            });
            history.push("/account");
        }).catch(error => {
            setIsError(true);
        }).finally();
    }

    return (
        <div className={style.SignIn}>

            <Header/>

            <div className={style.SignInContainer}>

                <h1>Войти</h1>

                <form>
                    <fieldset>

                        <fieldset>
                            <input
                                type="text"
                                placeholder="Логин"
                                value={login}
                                onChange={event => {setLogin(event.target.value)}}
                            />
                        </fieldset>

                        <fieldset>
                            <input
                                type="password"
                                placeholder="Пароль"
                                value={password}
                                onChange={event => {setPassword(event.target.value)}}
                            />
                        </fieldset>

                        <button
                        onClick={confirmLogin}>
                            Войти
                        </button>

                        {isError && <p className={style.Error}> Неверный логин или пароль. Попробуйте еще раз</p>}

                        <p>Еще не <Link to="/register">зарегестрированы?</Link></p>

                    </fieldset>
                </form>
            </div>
        </div>
    );
}

export default SignIn;
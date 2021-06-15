import React, {useState} from "react";
import style from "./styles/SignIn.module.css";
import Header from "../header/Header";
import {Link} from "react-router-dom";

const SignIn = () => {

    const [login, setLogin] = useState("")
    const [password, setPassword] = useState("")

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

                        <button>Войти</button>

                        <p>Еще не <Link to="/register">зарегестрированы?</Link></p>

                    </fieldset>
                </form>
            </div>
        </div>
    );
}

export default SignIn;
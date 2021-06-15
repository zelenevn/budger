import React, {useState} from "react";
import style from "./styles/SignUp.module.css";
import Header from "../header/Header";
import {Link} from "react-router-dom";

const SignUp = () => {

    const [login, setLogin] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    return (
        <div className={style.SignUp}>

            <Header/>

            <div className={style.SignUpContainer}>

                <h1>Зарегистрироваться</h1>

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
                                type="text"
                                placeholder="Почтовый адрес"
                                value={email}
                                onChange={event => {setEmail(event.target.value)}}
                            />
                        </fieldset>

                        <fieldset>
                            <input
                                type="password"
                                placeholder="Пароль"
                                value={password}
                                onChange={event => setPassword(event.target.value)}
                            />
                        </fieldset>

                        <button>Зарегестриорваться</button>

                    </fieldset>

                </form>

                <p>Уже зарегестрированы?</p>

                <p>
                    <Link to="/login">
                        Войти
                    </Link>
                </p>

            </div>
        </div>
    )
}

export default SignUp;
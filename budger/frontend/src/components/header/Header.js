import React, {useState} from "react";
import {Link} from "react-router-dom";
import {useStateValue} from "../../StateProvider";
import style from "./styles/Header.module.css";
import logo from "../../assets/images/logo.svg";


const Header = () => {

    const [{isLoggedIn, login}, dispatch] = useStateValue();


    return (
        <header className={style.Header}>

            <div className={style.HeaderContainer}>

                <div className={style.ImageContainer}>
                    <img src={logo} alt="Logo image"/>
                </div>


                <div className={style.StaticLinksContainer}>
                    <ul>
                        <li>
                            <Link to="/">
                                Главная
                            </Link>
                        </li>

                        <li>
                            <Link to="/about">
                                О нас
                            </Link>
                        </li>

                        <li>
                            <Link to="/contacts">
                                Контакты
                            </Link>
                        </li>
                    </ul>
                </div>

                <div className={style.DynamicLinksContainer}>
                    {
                        isLoggedIn ?
                            (<div>
                                <ul>
                                    <li className={style.Login}>
                                        <Link to="/account">
                                            {login}
                                        </Link>
                                    </li>

                                    <li className={style.LongLink}>
                                        <Link to="/logout">
                                            Выйти
                                        </Link>
                                    </li>
                                </ul>
                            </div>) :
                            (<div>
                                <ul>
                                    <li>
                                        <Link to="/login">
                                            Войти
                                        </Link>
                                    </li>

                                    <li className={style.LongLink}>
                                        <Link to="/register">
                                            Зарегистрироваться
                                        </Link>
                                    </li>
                                </ul>
                            </div>)
                    }
                </div>
            </div>
        </header>
    )
}

export default Header;
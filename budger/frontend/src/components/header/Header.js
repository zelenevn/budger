import React from "react";
import {Link} from "react-router-dom";
import style from "./styles/Header.module.css"
import logo from "../../assets/images/logo.svg"


const LoggedInLinks = <div></div>

const Header = () => {

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
                </div>
            </div>
        </header>
    )
}

export default Header;
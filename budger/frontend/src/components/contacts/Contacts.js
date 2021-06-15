import React from "react";
import Header from "../header/Header";
import style from "./styles/Contacts.module.css"

//images imports
import contacts from "../../assets/images/contacts.svg";
import gmail from "../../assets/images/gmail.svg";
import github from "../../assets/images/github.svg";
import discord from "../../assets/images/discord.svg";

const Contacts = () => {

    return (
        <div>

            <Header/>

            <div className={style.Contacts}>

                <div className={style.ContactsContainer}>

                    <h1>
                        Возникли вопросы – свяжитесь с нами
                    </h1>

                    <div className={style.ImagesContainer}>

                        <div className={style.LinksImagesContainer}>

                            <ul>

                                <li>
                                    <a href="">
                                        <img src={gmail} alt="Gmail logo"/>
                                    </a>
                                </li>

                                <li>
                                    <a href="">
                                        <img src={github} alt="Github logo"/>
                                    </a>
                                </li>

                                <li>
                                    <a href="">
                                        <img src={discord} alt="Discord image"/>
                                    </a>
                                </li>

                            </ul>

                        </div>

                        <div className={style.MainImageContainer}>

                            <img src={contacts} alt=""/>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    )
}

export default Contacts
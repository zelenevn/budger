import React from "react";

import Header from "../header/Header";
import image from "../../assets/images/about.svg";
import style from "./styles/About.module.css";

//images imports
import google from "../../assets/images/google.svg";
import microsoft from "../../assets/images/microsoft.svg";
import apple from "../../assets/images/apple.svg";
import twitter from "../../assets/images/twitter.svg";
import facebook from "../../assets/images/facebook.svg";
import starbucks from "../../assets/images/starbucks.svg";
import github from "../../assets/images/github.svg";

const About = () => {

    return (
        <div className={style.About}>

            <Header/>

            <div className={style.AboutContainer}>

                <div className={style.ContentContainer}>

                    <h1>
                        Узнайте о нашем приложении
                    </h1>

                    <div className={style.TextContainer}>

                        <div>
                            <p>
                                Budger – это способ отслеживать ваше финансовое состояние в любое время.
                                Вы можете проверить состояние своего счета и расходы со своего компьютера.
                                Благодаря финансовому менеджеру вы можете легко планировать свой бюджет,
                                не тратя на это свое драгоценное время. Ознакомтесь с нашим проектом по ссылке ниже.
                            </p>

                            <a href="https://github.com/c-addict/budger">
                                <img src={github} alt="Github logo"/>
                            </a>

                        </div>


                        <div className={style.ImageContainer}>
                            <img src={image} alt="Main image"/>
                        </div>

                    </div>

                </div>

                <div className={style.PartnersContainer}>

                    <h2>Наши партнеры</h2>

                    <div className={style.PartnersImagesContainer}>

                        <ul>

                            <li>
                                <img src={google} alt="Google logo"/>
                            </li>

                            <li>
                                <img src={microsoft} alt="Microsoft logo"/>
                            </li>

                            <li>
                                <img src={apple} alt="Apple logo"/>
                            </li>

                            <li>
                                <img src={twitter} alt="Twitter logo"/>
                            </li>

                            <li>
                                <img src={facebook} alt="Facebook logo"/>
                            </li>

                            <li>
                                <img src={starbucks} alt="Starbucks logo"/>
                            </li>

                        </ul>

                    </div>

                </div>

            </div>

        </div>
    )
}

export default About

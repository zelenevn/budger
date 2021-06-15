import React from "react";
import style from "./styles/Main.module.css"
import image from "../../assets/images/main.svg"
import Header from "../header/Header";
import {Link} from "react-router-dom";

const Main = () => {

    return (
        <div>
            <Header/>

            <main className={style.Main}>

                <div className={style.MainContainer}>

                    <div className={style.ContentContainer}>
                        <h1>
                            Budger – ваш главный <br/>
                            финансовый помощник
                        </h1>

                        <p>
                            Отслеживайте свое финансвое состояние в любой момент.
                            Ведите учет расходов и доходов, чтобы всегда знать сколько у вас денег.
                            Возпользуйтесь нашим калькулятором, чтобы узнать как много времени вам понадоиться,
                            чтобы достигнуть своей цели.
                        </p>

                        <Link to="/calculator">
                            Расчитать
                        </Link>

                    </div>

                    <div className={style.ImageContainer}>
                        <img src={image} alt="People with money"/>
                    </div>


                </div>

            </main>
        </div>
    )
}

export default Main
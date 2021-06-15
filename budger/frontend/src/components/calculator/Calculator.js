import React, {useState, useEffect} from "react";
import style from "./styles/Calculator.module.css";
import Header from "../header/Header";
import {Link} from "react-router-dom";

const Calculator = () => {

    const [calculated, setCalculated] = useState(false);
    const [income, setIncome] = useState("");
    const [expenses, setExpenses] = useState("");
    const [goal, setGoal] = useState("");
    const [month, setMonth] = useState(0);


    useEffect(() => {

    }, [])

    const calculate = (event) => {
        event.preventDefault();
        setMonth(goal / (income - expenses));
        setCalculated(!calculated);

    };

    return (
        <div className={style.Calculator}>
            <Header/>

            <div className={style.CalculatorContainer}>

                <form>

                    <fieldset>

                        <fieldset>
                            <input
                                type="number"
                                placeholder="Ваш месячный доход"
                                value={income}
                                onChange={event => {
                                    setIncome(Number(event.target.value))
                                }}
                            />
                        </fieldset>

                        <fieldset>
                            <input
                                type="number"
                                placeholder="Ваши месячные расходы"
                                value={expenses}
                                onChange={event => {
                                    setExpenses(Number(event.target.value))
                                }}
                            />
                        </fieldset>

                        <fieldset>
                            <input
                                type="number"
                                placeholder="Ваша финансовая цель"
                                value={goal}
                                onChange={event => {
                                    setGoal(Number(event.target.value))
                                }}
                            />
                        </fieldset>

                        <button
                            onClick={calculate}
                        >
                            Расчитать
                        </button>

                    </fieldset>

                </form>

                {calculated &&
                <p>
                    {`Чтобы накопить ${goal} вам потребуется ${month} месяцев. Начните пользоваться нашим приложением
                    и доберитесь до своей цели быстрее!
                    `}
                </p>
                }

                {calculated &&
                <Link to="/register">
                    Зарегестрироваться
                </Link>
                }


            </div>

        </div>
    );
}

export default Calculator;

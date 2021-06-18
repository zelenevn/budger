import React, {useState, useEffect} from "react";
import {useStateValue} from "../../StateProvider";
import {useHistory} from "react-router-dom";
import ReactModal from "react-modal";
import style from "./styles/Account.module.css";
import Header from "../header/Header";
import Transaction from "../transaction/Transaction";
import DatePicker from "react-date-picker";
import Menu from "../menu/Menu";
import Statistics from "../statistics/Statistics";
import ym from "react-yandex-metrika";


const Account = () => {

    const history = useHistory();
    const [{isLoggedIn, login, token}, dispatch] = useStateValue();

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [transactions, setTransactions] = useState([]);
    const [categories, setCategories] = useState([]);

    const [value, setValue] = useState(0);
    const [date, setDate] = useState(new Date());
    const [category, setCategory] = useState("Еда");

    useEffect(async () => {

        if (!isLoggedIn) {
            const login = localStorage.getItem("login");
            const token = localStorage.getItem("token");
            if (login != null && token != null) {
                dispatch({
                    type: "LOGIN",
                    login: login,
                    token: token
                });
            } else {
                history.push("/");
            }

        }

        const url = "https://budger-backend.herokuapp.com/transaction/all";
        const data = {
            login: login
        }

        await fetch(
            url,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(data)
            }
        ).then(response => {
            if (response.ok) {
                return response.json();
            }
            throw response;
        }).then(data => {
            setTransactions(data)
        }).catch(error => {

        });

        const c_url = "https://budger-backend.herokuapp.com/category/all";
        await fetch(
            c_url,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                }
            }
        ).then(response => {
            if (response.ok) {
                return response.json();
            }
            throw response;
        }).then(data => {
            setCategories(data)
        })
    }, [isModalOpen, login])

    const addTransaction = async event => {
        event.preventDefault();

        if (typeof window['ym'] !== undefined) {
            window['ym'](80995588,'reachGoal','create_transaction')
        }

        const url = "https://budger-backend.herokuapp.com/transaction";
        const data = {
            login: login,
            value: value,
            date: date,
            category: category
        }

        await fetch(
            url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(data)
            }
        )

        closeModal();
        setValue(0);
        setDate(new Date());
        setCategory("Еда");
    };

    const openModal = () => {

        if (typeof window['ym'] !== undefined) {
            window['ym'](80995588,'reachGoal', 'add_transaction')
        }

        setIsModalOpen(true);
    }

    const closeModal = () => {
        setIsModalOpen(false);
    }

    const getMoney = () => {
        var sum = 0;

        transactions.forEach((item, i, arr) => {
            if (item.category === "Доход") {
                sum += Number(item.value)
            } else {
                sum -= Number(item.value)
            }

        })

        return sum
    }

    const getCategories = () => {
        var categories = new Set();
        transactions.forEach((item, i, arr) => {
            categories.add(item.category)
        })
        return Array.from(categories)
    }


    return (
        <div className={style.Account}>
            <Header/>

            <div className={style.AccountContainer}>

                {transactions.length > 0 && <Statistics money={getMoney()} categories={getCategories()}/>}

                <div className={style.Transactions}>
                    <ul>
                        {transactions.map((value, index) => {
                            return <Transaction
                                type={value.type}
                                value={value.value}
                                date={value.date}
                                category={value.category}
                                key={index}
                            />
                        })}
                    </ul>

                    <button onClick={openModal}>
                        +
                    </button>

                    <ReactModal
                        isOpen={isModalOpen}
                        className={style.Modal}
                    >
                        <form>
                            <fieldset>
                                <fieldset>
                                    <input
                                        className={style.Value}
                                        type="number"
                                        placeholder="Сколько вы потратили?"
                                        value={value}
                                        onChange={event => {
                                            setValue(Number(event.target.value))
                                        }}
                                    />
                                </fieldset>

                                <fieldset>
                                    <DatePicker
                                        className={style.Data}
                                        onChange={setDate}
                                        value={date}
                                    />
                                </fieldset>

                                <fieldset>
                                    <select
                                        onChange={event => {setCategory(event.target.value)}}
                                        className={style.Category} name="Категории" id="categories">
                                        {categories.map((value, index) => {
                                            return <option value={value.title} key={index}>{value.title}</option>
                                        })}
                                    </select>

                                </fieldset>
                            </fieldset>
                        </form>

                        <button
                            className={style.Save}
                            onClick={addTransaction}
                        >Сохранить
                        </button>

                        <button
                            className={style.Close}
                            onClick={closeModal}
                        >
                            Закрыть
                        </button>
                    </ReactModal>
                </div>

                <Menu/>

            </div>
        </div>
    );
}

export default Account;
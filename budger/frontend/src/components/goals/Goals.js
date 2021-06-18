import React, {useState, useEffect} from "react";
import {useStateValue} from "../../StateProvider";
import style from "./styles/Goals.module.css";
import {useHistory} from "react-router-dom";
import Header from "../header/Header";
import Menu from "../menu/Menu";
import ReactModal from "react-modal";
import Goal from "../goal/Goal";
import DatePicker from "react-date-picker";
import ym from "react-yandex-metrika";


const Goals = () => {

    const history = useHistory();
    const [{isLoggedIn, login, token}, dispatch] = useStateValue();

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [goals, setGoals] = useState([]);

    const [title, setTitle] = useState("");
    const [value, setValue] = useState(0);
    const [date, setDate] = useState(new Date());
    const [description, setDescription] = useState("");

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

        const url = "https://budger-backend.herokuapp.com/goal/all";
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
                body: JSON.stringify(data),
                mode: "cors"
            }
        ).then(response => {
            if (response.ok) {
                return response.json();
            }
            throw response;
        }).then(data => {
            setGoals(data);
        }).catch(error => {

        });

    }, [isModalOpen, login])

    const openModal = () => {
        setIsModalOpen(true);
    }

    const closeModal = () => {
        setIsModalOpen(false);
    }

    const addGoal = async event => {
        event.preventDefault();

        if (typeof window['ym'] !== undefined) {
            window['ym'](80995588,'reachGoal','create_transaction')
        }
        const url = "https://budger-backend.herokuapp.com/goal";
        const data = {
            login: login,
            title: title,
            value: value,
            date: date,
            description: description
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
        setTitle("");
        setValue(0);
        setDate(new Date());
        setDescription("");
    }

    return (
        <div className={style.Goals}>

            <Header/>

            <div className={style.GoalsContainer}>

                <div className={style.GoalsAndButton}>

                    <div className={style.GoalsSection}>
                        {goals.map((value, index) => {
                            return <Goal
                                key={index}
                                title={value.title}
                                value={value.value}
                                date={value.date}
                                description={value.description}
                            />
                        })}


                    </div>

                    <button onClick={openModal}>
                        +
                    </button>

                </div>

                <ReactModal
                    isOpen={isModalOpen}
                    className={style.Modal}
                >
                    <form>
                        <fieldset>
                            <fieldset>
                                <input
                                    className={style.Title}
                                    type="text"
                                    placeholder="Название"
                                    value={title}
                                    onChange={event => {
                                        setTitle(event.target.value)
                                    }}
                                />
                            </fieldset>

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
                                <textarea
                                    className={style.Description}
                                    placeholder="Описание"
                                    value={description}
                                    onChange={event => {setDescription(event.target.value)}}
                                    cols="3"
                                    rows="5"
                                > </textarea>

                            </fieldset>
                        </fieldset>
                    </form>

                    <button
                        className={style.Save}
                        onClick={addGoal}
                    >Сохранить
                    </button>

                    <button
                        className={style.Close}
                        onClick={closeModal}
                    >
                        Закрыть
                    </button>
                </ReactModal>

                <Menu/>
            </div>

        </div>
    );
}

export default Goals;
import React, {useState, useEffect} from "react";
import {useStateValue} from "../../StateProvider";
import {useHistory} from "react-router-dom";
import style from "./styles/Report.module.css";
import Header from "../header/Header";
import Menu from "../menu/Menu";
import DatePicker from "react-date-picker";
import ReportView from "../ReportView/ReportView";
import ym from "react-yandex-metrika";


const Report = () => {

    const [{isLoggedIn, login, token}, dispatch] = useStateValue();
    const history = useHistory();

    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [transactions, setTransactions] = useState([]);
    const [isShowReport, setIsShowReport] = useState(false);
    const [isUpdateReport, setIsUpdateReport] = useState(false);


    useEffect(() => {
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
            hideReport();
        }
    }, [isShowReport, isUpdateReport])

    const showReport = () => {
        setIsShowReport(true);
    }

    const hideReport = () => {
        setIsShowReport(false);
    }


    const generateReport = async event => {
        event.preventDefault();
        if (typeof window['ym'] !== undefined) {
            window['ym'](80995588,'reachGoal','show_report')
        }
        const url = "https://budger-backend.herokuapp.com/report";
        const data = {
            login: login,
            startDate: startDate,
            endDate: endDate
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

        }).finally();

        showReport();
    };

    const getCategories = () => {
        var categories = new Set();

        let sorted = transactions.sort((t1, t2) => { return t2.value - t1.value})
        sorted.forEach((transaction, i, arr) => {
            categories.add(transaction.category);
        })
        return Array.from(categories);
    }


    const getData = () => {
        let result = [];

        let categories = getCategories();

        for (let i = 0; i < categories.length; i++) {
            result[categories[i]] = 0;
        }

        for (let i = 0; i < transactions.length; i++) {
            result[transactions[i].category] += transactions[i].value;
        }

        let data = []
        for (let r in result) {
            data.push({
                name: r,
                value: result[r]
            })
        }
        return data;
    }

    return (
        <div className={style.Report}>

            <Header/>

            <div className={style.ReportContainer}>

                <div className={style.Form}>

                    <fieldset>

                        <fieldset>
                            <DatePicker
                                className={style.Date}
                                onChange={setStartDate}
                                value={startDate}
                            />
                        </fieldset>

                        <fieldset>
                            <DatePicker
                                className={style.Date}
                                onChange={setEndDate}
                                value={endDate}
                            />
                        </fieldset>

                        <button
                            className={style.ReportButton}
                            onClick={generateReport}
                        >
                            Получить отчет
                        </button>

                    </fieldset>
                </div>

                {
                    isShowReport &&
                <ReportView data={getData()} categories={getCategories()}
                callback={hideReport}/>
                }


                <Menu/>

            </div>

        </div>
    );
}

export default Report;
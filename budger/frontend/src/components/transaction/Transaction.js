import React from "react";
import style from "./styles/Transaction.module.css";

//images imports
import income from "../../assets/images/income.svg";
import expenses from "../../assets/images/expenses.svg";

const Transaction = props => {

    return (
        <div className={style.Transaction}>
            {props.type ?
                <img src={income} alt=""/> :
                <img src={expenses} alt=""/>}

            <p className={style.Value}>{props.value}</p>

            <p className={style.Date}>{props.date}</p>
        </div>
    );
}

export default Transaction;
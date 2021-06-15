import React, {useState, useEffect} from "react";
import style from "./styles/Account.module.css";
import Header from "../header/Header";
import Transaction from "../transaction/Transaction";

const Account = () => {

    const [transactions, setTransactions] = useState([])

    return(
        <div className={style.Account}>
            <Header/>

            <div className={style.AccountContainer}>

                <div className={style.Statistics}>

                </div>

                <div className={style.Transactions}>
                    <ul>
                        {transactions.map((value, index) => {
                            return <Transaction
                                type={value.type}
                                value={value.value}
                                date={value.date}
                            />
                        })}
                    </ul>
                </div>

                <div className={style.Filter}>

                </div>

            </div>
        </div>
    );
}

export default Account;
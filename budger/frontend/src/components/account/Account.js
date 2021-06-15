import React from "react";
import style from "./styles/Account.module.css";
import Header from "../header/Header";

const Account = () => {

    return(
        <div className={style.Account}>
            <Header/>

            <div className={style.AccountContainer}>

                <div className={style.Statistics}>

                </div>

                <div className={style.Transactions}>

                </div>

                <div className={style.Filter}>

                </div>

            </div>
        </div>
    );
}

export default Account;
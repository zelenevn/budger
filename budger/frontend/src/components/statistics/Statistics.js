import React from "react";
import style from "./styles/Statistics.module.css";


const Statistics = props => {

    return(
        <div className={style.Statistics}>

            <div className={style.Money}>
                {props.money > 0 ? (<p className={style.Income}>{props.money}</p>) : <p className={style.Expenses}>{props.money}</p>}
            </div>

            <div className={style.List}>
                <ul>
                    {props.categories.map((value, index) => {
                        return <li key={index}>{value}</li>
                    })}
                </ul>
            </div>

        </div>
    );
}

export default Statistics;
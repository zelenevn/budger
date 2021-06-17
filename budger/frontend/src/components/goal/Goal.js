import React from "react";
import style from "./styles/Goal.module.css";


const Goal = props => {

    return(
        <div className={style.Goal}>
            <h2>{props.title}</h2>
            <p className={style.Value}>{props.value}</p>
            <p className={style.Date}>{props.date}</p>
            <p className={style.Description}>{props.description}</p>
        </div>
    );
}

export default Goal;
import React from "react";
import style from "./styles/Recommendation.module.css";


const Recommendation = props => {

    return(
        <div className={style.Recommendation}>
            <p>{`Снизьте расходы по категории ${props.category.category}`}</p>
        </div>
    );
}

export default Recommendation;
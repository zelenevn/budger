import React from "react";
import style from "./styles/ReportView.module.css";
import { PieChart, Pie, Legend, Tooltip, ResponsiveContainer } from 'recharts';

const ReportView = props => {

    return( props.categories.length === 0 ? <div className={style.Empty}><p>Отсутствуют транзакции за данный период.</p></div> :
        <div className={style.ReportView}>

            <ResponsiveContainer width="100%" height="100%">
                <PieChart width={400} height={400}>
                    <Pie
                        dataKey="value"
                        startAngle={360}
                        endAngle={0}
                        data={props.data}
                        cx="50%"
                        cy="50%"
                        outerRadius={100}
                        fill="#8884d8"
                        label
                    />
                </PieChart>
            </ResponsiveContainer>

            <div className={style.Categories}>

                <p>Топ категории:</p>

                <ul>
                    {props.categories.map((item, index) => {
                        return <li key={index}>{item}</li>
                    })}
                </ul>
            </div>

            <button className={style.Close} onClick={props.callback}>
                Скрыть
            </button>


        </div>
    )
}

export default ReportView;
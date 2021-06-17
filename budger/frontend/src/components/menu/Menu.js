import React from "react";
import {Link} from "react-router-dom";
import style from "./styles/Menu.module.css";

const Menu = () => {

    return(
        <div className={style.Menu}>

            <ul>
                <li>
                    <Link to="/account">
                        Расходы/Доходы
                    </Link>
                </li>

                <li>
                    <Link to="/goals">
                        Цели
                    </Link>
                </li>

                <li>
                    <Link to="/recommendations">
                        Рекомендации
                    </Link>
                </li>

                <li>
                    <Link to="/report">
                        Отчет
                    </Link>
                </li>
            </ul>

        </div>
    );
}

export default Menu;
import React, {useEffect, useState} from "react";
import {useHistory} from "react-router-dom";
import {useStateValue} from "../../StateProvider";
import style from "./styles/Recommendations.module.css";
import Header from "../header/Header";
import Menu from "../menu/Menu";
import Recommendation from "../recommendation/Recommendation";


const Recommendations = () => {

    const history = useHistory();
    const [{isLoggedIn, login, token}, dispatch] = useStateValue();

    const [recommendations, setRecommendations] = useState([]);

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

        const url = "https://budger-backend.herokuapp.com/recommendations";
        const data = {
            login: login
        }

        await fetch(
            url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(data),
            }
        ).then(response => {
            if (response.ok) {
                return response.json();
            }
            throw response;
        }).then(data => {
            setRecommendations(data);
        }).catch(error => {

        }).finally();

    }, [login])

    return(
        <div className={style.Recommendations}>

           <Header/>

            <div className={style.RecommendationsContainer}>

                <div className={style.List}>
                    <ul>

                        {recommendations.map((item, index) => {
                            return <li key={index}>
                                <Recommendation category={item}/>
                            </li>
                        })}

                    </ul>
                </div>

                <Menu/>
            </div>

        </div>
    );
}

export default Recommendations;
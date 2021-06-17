import React, {useState} from "react";
import style from "./styles/SignUp.module.css";
import Header from "../header/Header";
import {Link} from "react-router-dom";
import {useStateValue} from "../../StateProvider";
import {useHistory} from "react-router-dom";

const SignUp = () => {

    const history = useHistory();
    const [{}, dispatch] = useStateValue();

    const [isError, setIsError] = useState(false);
    const [login, setLogin] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const confirmRegistration = async event => {
      event.preventDefault();

      const url = "https://budger-backend.herokuapp.com/registration";
      const data = {
          login: login,
          email:email,
          password:password
      };

      await fetch(
          url,
          {
              method: "POST",
              headers: {
                  "Content-Type": "application/json"
              },
              body: JSON.stringify(data)
          }
      ).then(response => {
          if (response.ok) {
              return response.json();
          }
      }).then(data => {
          const value = {
              login: data.login,
              token: data.token
          };
          dispatch({
              type: "LOGIN",
              login: value.login,
              token: value.token
          });
          history.push("/account");
      }).catch(error => {
          setIsError(true);
      }).finally();
    };

    return (
        <div className={style.SignUp}>

            <Header/>

            <div className={style.SignUpContainer}>

                <h1>Зарегистрироваться</h1>

                <form>

                    <fieldset>

                        <fieldset>
                            <input
                                type="text"
                                placeholder="Логин"
                                value={login}
                                onChange={event => {setLogin(event.target.value)}}
                            />
                        </fieldset>

                        <fieldset>
                            <input
                                type="text"
                                placeholder="Почтовый адрес"
                                value={email}
                                onChange={event => {setEmail(event.target.value)}}
                            />
                        </fieldset>

                        <fieldset>
                            <input
                                type="password"
                                placeholder="Пароль"
                                value={password}
                                onChange={event => setPassword(event.target.value)}
                            />
                        </fieldset>

                        <button
                            onClick={confirmRegistration}
                        >
                            Зарегистрироваться
                        </button>

                    </fieldset>

                </form>

                {isError && <p className={style.Error}>Логин и почтовый адрес уже зарегистрированы</p>}

                <p>Уже зарегистрированы?</p>

                <p>
                    <Link to="/login">
                        Войти
                    </Link>
                </p>

            </div>
        </div>
    )
}

export default SignUp;
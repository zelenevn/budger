export const initialState = {
    isLoggedIn: false,
    login: "",
    token: ""
}

const reducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            localStorage.setItem("login", action.login);
            localStorage.setItem("token", action.token);
            return {
                isLoggedIn: true,
                login: action.login,
                token: action.token
            };

        case "LOGOUT":
            localStorage.clear();
            return initialState;
        default:
            return state;
    }
}

export default reducer;
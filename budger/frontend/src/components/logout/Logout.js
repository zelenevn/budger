import React, { useEffect } from "react";
import { useHistory } from "react-router-dom";
import { useStateValue } from "../../StateProvider";


const Logout = () => {

    const history = useHistory();
    const [{}, dispatch] = useStateValue();

    useEffect(() => {
        dispatch({
            type: "LOGOUT"
        });

        history.push("/");
    }, [])

    return(
        <div>

        </div>
    );
}

export default Logout;

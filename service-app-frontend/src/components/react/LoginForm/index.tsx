import React, {useState} from "react";
import styles from './LoginForm.module.css';
import axios from "axios";
import {toast} from "react-toastify";

interface Props {

}

const LoginForm = ({}: Props) => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();

        const formData = new FormData();
        formData.set("email", email);
        formData.set("password", password);
        axios.post("/api/login", formData)
            .then(_ => window.location.href = "/")
            .catch(_ => toast("Incorrect username or password", { type: "error" }));
    }

    return (
        <>
            <h2 className={styles['login-header']}>Login</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="email">Email</label>
                <input
                    className={styles['form-input']}
                    type="email"
                    id="email"
                    value={email}
                    onInput={(e: React.ChangeEvent<HTMLInputElement>) => setEmail(e.target.value)}
                    required
                />

                <label htmlFor="password">Password</label>
                <input
                    className={styles['form-input']}
                    type="password"
                    id="password"
                    value={password}
                    onInput={(e: React.ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)}
                    required
                />

                <button className={styles['submit-btn']} type="submit">Login</button>
            </form>
        </>
    )
}

export default LoginForm;
import React from 'react';
import { useRef, useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faDog } from '@fortawesome/free-solid-svg-icons';

const LoginForm = () => {
    const userRef = useRef();
    const errorRef = useRef();

    const [user, setUser] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrorMessage('');
    }, [user, password])

    const handleSubmit = async (e) => {
        e.preventDefault();
        alert(user, password);
        setUser('');
        setPassword('');
        setSuccess(true);
    }



    return (
        <>
            {success ?
                (<section>
                    <h1>Zalogowales sie!</h1>
                    <br />
                    <p>
                        <a href='src/main/walk-a-dog-fe-1-create-login-view/src/components/LoginForm#'>Strona główna</a>
                    </p>
                </section>) : (
                    <section className='loginForm'>
                        <p ref={errorRef} className={errorMessage ? "errorMessage" :
                            "offscreen"} aria-live="assertive">{errorMessage}</p>
                        <h1>Walk a dog!</h1>
                        
                        <FontAwesomeIcon icon={faDog} size="5x" style={{color: "black", margin: "25px"}} />
                        <form onSubmit={handleSubmit}>
                            <label htmlFor='username'></label>
                            <input style={{textAlign: "center"}}
                                type="text"
                                id="username"
                                ref={userRef}
                                placeholder="login"
                                autoComplete="off"
                                onChange={(e) => setUser(e.target.value)}
                                value={user}
                                required
                            />
                            <label htmlFor='password'></label>
                            <input style={{textAlign: "center"}}
                                type="password"
                                id="password"
                                placeholder='hasło'
                                onChange={(e) => setPassword(e.target.value)}
                                value={password}
                                required
                            />
                            <button>Zaloguj</button>
                        </form>
                            <p style={{textAlign:"center"}}>
                            Nie masz konta? <br />
                            <span className="line">
                                <a href="src/main/walk-a-dog-fe-1-create-login-view/src/components/LoginForm#">Zarejestruj się!</a>
                            </span>
                        </p>
                    </section>
                )}
        </>

    );
};

export default LoginForm
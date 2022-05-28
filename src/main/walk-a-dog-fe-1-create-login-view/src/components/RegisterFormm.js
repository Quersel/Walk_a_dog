import React, {useState} from "react";
import {faPhone, faEnvelope, faLock, faUndo, faUserPlus, faUser, faDog,} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Row, Col, Card, Form, InputGroup, FormControl, Button,} from "react-bootstrap";
import MyToast from "./MyToast";
import {registerUser} from "../services";
import {useDispatch} from "react-redux";


const Register = (props) => {

    const [show, setShow] = useState(false)
    const [message, setMessage] = useState('')

    const initialState = {
        username:'', email:'', password:'', phoneNumber:'', firstName:'', lastName:''
    };

    const [user, setUser] = useState(initialState)

    const userChange = (event) => {
        const {name, value} = event.target;
        setUser({...user, [name]:value})
    };

    const dispatch = useDispatch()

    const saveUser = () => {
        dispatch(registerUser(user))
            .then((response) => {
                setShow(true);
                setMessage(response.message)
                resetRegisterForm()
                setTimeout(() => {
                    setShow(false)
                    props.history.push("/login")
                },2000)
            })
            .catch((error) => {
                console.log(error);
            })
    }

    const resetRegisterForm = () => {
        setUser(initialState);
    };

    return (
        <div className={"section"}>
            <div style={{"display": show ? "block" : "none"}}>
                <MyToast show ={ show } message = {message} type = {"success"}/>
            </div>
            <Row className="row">
                <Col xs={5}>
                    <Card className={""}>
                        <Card.Header>
                            <h1>Walk a dog!</h1>
                            <FontAwesomeIcon icon={faDog} size="4x" style={{color: "black", marginLeft: "150px", marginBottom: "10px"}} />
                            <h3>Rejestracja</h3>
                        </Card.Header>
                        <Card.Body style={{marginBottom:"-30px"}}>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faUser}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl autoComplete="off" type="text" name="username" value={user.username} onChange={userChange}
                                                     className={"bg-dark text-white"} placeholder="Podaj login" />
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faUser}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl autoComplete="off" type="text" name="firstName" value={user.firstName} onChange={userChange}
                                                     className={"bg-dark text-white"} placeholder="Podaj imie"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faUser}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl autoComplete="off" type="text" name="lastName" value={user.lastName} onChange={userChange}
                                                     className={"bg-dark text-white"} placeholder="Podaj nazwisko"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faEnvelope}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl required autoComplete="off" type="text" name="email" value={user.email} onChange={userChange}
                                                     className={"bg-dark text-white"} placeholder="Podaj adres e-mail"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faLock}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl required autoComplete="off" type="password" name="password" value={user.password} onChange={userChange}
                                                     className={"bg-dark text-white"} placeholder="Podaj Hasło"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faLock}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl required autoComplete="off" type="password" name="passwordRepeat"
                                                     //value={user.password}
                                                     onChange={userChange}
                                                     className={"bg-dark text-white"} placeholder="Powtórz Hasło"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faPhone}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl autoComplete="off" type="text" name="phoneNumber" value={user.phoneNumber} onChange={userChange}
                                                     className={"bg-dark text-white"} placeholder="Podaj numer telefonu"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"center"}}>
                            <Button size="sm" type="button" variant="success"
                                    onClick={saveUser}
                                    disabled={user.email.length === 0 || user.password.length === 0 || user.phoneNumber.length === 0 || user.firstName.length === 0 || user.lastName.length === 0 || user.username.length === 0}>
                                <FontAwesomeIcon icon={faUserPlus}/> Zarejestruj
                            </Button>{' '}
                            <Button size="sm" type="button" variant="info" onClick={resetRegisterForm}>
                                <FontAwesomeIcon icon={faUndo}/> Wyczyść
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            </Row>

        </div>

    );
}


export default Register;
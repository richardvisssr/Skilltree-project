import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { BsArrowReturnLeft } from "react-icons/bs";
import { useNavigate } from "react-router-dom";

import FormFieldComponent from "../FormFieldComponent";
import MessageComponent from "../MessageComponent";
import "../../styles/styles.css";
import { fetchRegisterNewUserAsyncAction } from "../../actions/UserAction";

function RegisterAccountComponent() {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const registeredCorrectly = useSelector((state) => state.user.registeredCorrectly);

    const [formData, setFormData] = useState({
        firstname: "",
        lastname: "",
        email: "",
        roleId: 1, // Docent is default role
        password: "",
    });

    const onChangeFields = (e) => {
        const { name, value } = e.target;

        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value
        }));
    };

    const handleRegisterButton = () => {
        dispatch(fetchRegisterNewUserAsyncAction(formData));
    };

    const showMessage = () => {
        if (registeredCorrectly) {
            return <MessageComponent type="success" headText="Gelukt!" text="Registreren is gelukt" />
        } else if (!registeredCorrectly && registeredCorrectly !== null) {
            return <MessageComponent type="error" headText="Fout!" text="Registreren is mislukt" />
        }
        return null;
    }
    
    const handleGoBackButton = () => {
        navigate("/home");
    }

    return (
        <div className="flex min-h-screen items-center justify-center bg-gray-100">
            <div className="w-5/6 max-w-md">
                <div className="bg-white rounded-lg shadow-xl p-6">
                    <BsArrowReturnLeft
                        className="cursor-pointer"
                        onClick={handleGoBackButton}
                    />
                    <h2 className="text-2xl font-semibold mb-4 text-center">Registreer</h2>
                    {showMessage()}
                    <FormFieldComponent
                        fieldType="input"
                        title="firstname"
                        label="Voornaam"
                        value={formData.firstname}
                        onChange={onChangeFields}
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="lastname"
                        label="Achternaam"
                        value={formData.lastname}
                        onChange={onChangeFields}
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="email"
                        label="E-mail"
                        value={formData.email}
                        onChange={onChangeFields}
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="password"
                        label="Wachtwoord"
                        value={formData.password}
                        onChange={onChangeFields}
                    />
                    <FormFieldComponent
                        fieldType="dropdown"
                        title="roleId"
                        label="Rol"
                        options={[
                            { value: "1", label: "Docent" },
                            { value: "2", label: "Student" }
                        ]}
                        value={formData.role}
                        onChange={onChangeFields}
                    />
                    <div className="mt-6 flex items-center justify-center">
                        <button
                            type="submit"
                            className="w-full bg-indigo-600 text-white font-semibold py-2 px-4 rounded hover:bg-indigo-500 focus:outline-none focus:ring-2 focus:ring-indigo-500"
                            onClick={handleRegisterButton}
                        >
                            Registreren
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default RegisterAccountComponent;

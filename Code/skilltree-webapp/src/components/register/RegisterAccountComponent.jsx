import React, { useState } from "react";
import { useDispatch } from "react-redux";

import FormFieldComponent from "../node/createNode/FormFieldComponent";
import "../../styles/styles.css";
import { fetchRegisterNewUserAsyncAction } from "../../actions/UserAction";

function RegisterAccountComponent() {
    const dispatch = useDispatch();

    const [formData, setFormData] = useState({
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        role: 1 // Docent is default role
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

    return (
        <div className="flex min-h-screen items-center justify-center bg-gray-100">
            <div className="w-5/6 max-w-md">
                <div className="bg-white rounded-lg shadow-xl p-6">
                    <h2 className="text-2xl font-semibold mb-4 text-center">Registreer</h2>
                    <FormFieldComponent
                        fieldType="input"
                        title="firstname"
                        value={formData.firstname}
                        onChange={onChangeFields}
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="lastname"
                        value={formData.lastname}
                        onChange={onChangeFields}
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="email"
                        value={formData.email}
                        onChange={onChangeFields}
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="password"
                        value={formData.password}
                        onChange={onChangeFields}
                    />
                    <FormFieldComponent
                        fieldType="dropdown"
                        title="role"
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

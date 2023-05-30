import React from "react";
import FormFieldComponent from "../node/createNode/FormFieldComponent";
import "../../styles/styles.css";

function RegisterAccountComponent() {
    return (
        <div className="flex min-h-screen items-center justify-center bg-gray-100">
            <div className="w-5/6 max-w-md">
                <div className="bg-white rounded-lg shadow-xl p-6">
                    <h2 className="text-2xl font-semibold mb-4">Registratie</h2>
                    <FormFieldComponent
                        fieldType="input"
                        title="Naam"
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="Achternaam"
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="E-mail"
                    />
                    <FormFieldComponent
                        fieldType="input"
                        title="Wachtwoord"
                    />
                    <FormFieldComponent
                        fieldType="dropdown"
                        title="Rol"
                        options={[
                            { value: "Docent", label: "Docent" },
                            { value: "Student", label: "Student" }
                        ]}
                    />
                    <div className="mt-6 flex items-center justify-center">
                        <button
                            type="submit"
                            className="w-full bg-indigo-600 text-white font-semibold py-2 px-4 rounded hover:bg-indigo-500 focus:outline-none focus:ring-2 focus:ring-indigo-500"
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

import React from "react";

function RegisterFormFieldComponent({
    fieldType = "input",
    title,
    type = "text",
    value,
    options
}) {

    const validateEmail = (email) => {
        // Regex voor e-mailvalidatie
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    };

    const validatePassword = (password) => {
        // Regex voor wachtwoordvalidatie (minimaal 8 tekens, minstens 1 hoofdletter, 1 kleine letter, 1 cijfer en 1 speciaal teken)
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return passwordRegex.test(password);
    };

    const showField = () => {
        if (fieldType === "input") {
            return (
                <input
                    type={type}
                    name={title}
                    id={title}
                    className="block w-full px-4 py-2 placeholder-gray-400 border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
            );
        } else if (fieldType === "dropdown") {
            return (
                <select
                    name={title}
                    id={title}
                    value={value}
                    className="block w-full px-4 py-2 placeholder-gray-400 border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                >
                    <option value="">Selecteer een rol</option>
                    {options.map((option) => (
                        <option key={option.value} value={option.value}>{option.label}</option>
                    ))}
                </select>
            );
        }
    };

    const validateField = () => {
        if (type === "text" && value) {
            if (title === "E-mail" && !validateEmail(value)) {
                return <p className="text-red-500 text-sm mt-1">Ongeldig e-mailadres</p>;
            } else if (title === "Wachtwoord" && !validatePassword(value)) {
                return (
                    <p className="text-red-500 text-sm mt-1">
                        Het wachtwoord moet minimaal 8 tekens bevatten en minstens 1 hoofdletter, 1 kleine letter, 1 cijfer en 1 speciaal teken.
                    </p>
                );
            }
        }
        return null;
    };

    if (type === "text") {
        return (
            <div className="mt-6">
                <label
                    htmlFor={title}
                    className="block text-sm font-medium text-gray-700"
                >
                    {title}
                </label>
                <div className="mt-1">
                    {validateField()}
                    {showField()}
                </div>
            </div>
        );
    }
    return null;
}

export default RegisterFormFieldComponent;

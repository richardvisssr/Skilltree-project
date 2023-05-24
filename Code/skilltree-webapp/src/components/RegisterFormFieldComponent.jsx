import React from "react";

function RegisterFormFieldComponent({
    fieldType = "input",
    title,
    type = "text",
    value,
    onChange,
    options
}) {
    const showField = () => {
        if (fieldType === "input") {
            return (
                <input
                    type={type}
                    name={title}
                    id={title}
                    value={value}
                    onChange={onChange}
                    className="block w-full px-4 py-2 placeholder-gray-400 border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
            );
        } else if (fieldType === "dropdown") {
            return (
                <select
                    name={title}
                    id={title}
                    value={value}
                    onChange={onChange}
                    className="block w-full px-4 py-2 placeholder-gray-400 border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                >
                    <option value="">Selecteer een optie</option>
                    {options.map((option) => (
                        <option key={option.value} value={option.value}>{option.label}</option>
                    ))}
                </select>
            );
        }
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
                <div className="mt-1">{showField()}</div>
            </div>
        );
    }
    return null;
}

export default RegisterFormFieldComponent;
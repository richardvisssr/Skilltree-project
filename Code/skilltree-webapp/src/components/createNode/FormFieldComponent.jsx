/* eslint-disable max-len */
import React from "react";

function formFieldComponent({
    fieldType = "input", title, type = "text", value, onChange,
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
                    className="text-center block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-600 sm:text-sm sm:leading-6"
                />
            )
        } else if (fieldType === "textarea") {
            return (
                <textarea
                    name={title}
                    id={title}
                    value={value}
                    onChange={onChange}
                    className="text-center block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-600 sm:text-sm sm:leading-6"
                />
            )
        }
    }

    if (type === "text") {
        return (
            <div className="mt-10 gap-x-6 gap-y-8 justify-center flex">
                <div className="w-3/5 text-center">
                    <label
                        htmlFor={title}
                        className="text-sm font-medium leading-6 text-gray-900">{title}
                    </label>
                    <div className="mt-2">
                        {showField()}
                    </div>
                </div>
            </div>
        );
    }
    return null;
}

export default formFieldComponent;

import React from "react";

function formFieldComponent({ titel, type }) {
    return (
        <div className="mt-10 gap-x-6 gap-y-8 justify-center flex">
            <div className="w-3/5 text-center">
                <label htmlFor={titel} className="text-sm font-medium leading-6 text-gray-900">{titel}</label>
                <div className="mt-2">
                    <input type={type} name={titel} id={titel} className="text-center block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-600 sm:text-sm sm:leading-6" />
                </div>
            </div>
        </div>
    );
}

export default formFieldComponent;

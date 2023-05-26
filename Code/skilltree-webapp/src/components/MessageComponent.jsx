import React, { useEffect, useState } from "react";

function MessageComponent({
    type,
    headText,
    text
}) {
    const errorStyle = "bg-red-100 border border-red-400 text-red-700";
    const successStyle = "bg-green-100 border border-green-400 text-green-700";

    const [style, setStyle] = useState("");

    useEffect(() => {
        console.log(type)
        if (type === "error") {
            setStyle(errorStyle);
        } else if (type === "success") {
            setStyle(successStyle);
        }
    }, [type]);

    return (
        <div className={`px-4 py-3 rounded relative ${style}`} role="alert">
            <strong className="font-bold">{headText}</strong>
            <span className="block sm:inline"> &nbsp; {text}</span>
        </div>
    )
}

export default MessageComponent;

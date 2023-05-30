import React, { useState } from 'react';
import FormFieldComponent from "./FormFieldComponent";


function FeedbackNodeComponent() {

    const [feedback, setFeedback] = useState("");    

    const handleFeedbackChange = (event) => {
        setFeedback(event.target.value);
    };
    
return (
    <>
      <FormFieldComponent
        fieldType="dropdown"
        title="Feedback geven aan"
        options={[
          { value: "Kees", label: "Kees" },
          { value: "Klaas", label: "Student" }
        ]}
      />
      <FormFieldComponent
        fieldType="textarea"
        title="Feedback"
        value={feedback}
        onChange={handleFeedbackChange}
      />
    <div className="mt-6 flex items-center justify-center">
        <button
            type="submit"
            className="save-button w-50  text-white font-semibold py-2 px-4 rounded  focus:outline-none focus:ring-2 focus:ring-indigo-500"
        >
            Feedback opslaan
        </button>
    </div>
    </>
  );
}

export default FeedbackNodeComponent;

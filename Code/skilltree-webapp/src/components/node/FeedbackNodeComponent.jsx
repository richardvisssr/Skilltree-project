import React, {useState} from 'react';
import FormFieldComponent from "./FormFieldComponent";
import {useSelector, useDispatch} from "react-redux";
import {addFeedbackActionAsync} from "../../actions/StudentAction";


function FeedbackNodeComponent() {
    const dispatch = useDispatch();
    const students = useSelector((state) => state.student.selectedStudents);
    const currentNodeId = useSelector((state) => state.node.currentNode);

    const [feedback, setFeedback] = useState("");
    const [studentId, setStudentId] = useState("");

    const handleFeedbackChange = (event) => {
        setFeedback(event.target.value);
    };

    const handleStudentChange = (event) => {
        setStudentId(event.target.value);
    }

    const addFeedback = () => {
      dispatch(addFeedbackActionAsync(currentNodeId, studentId, feedback));
    }
    
return (
    <>
      <FormFieldComponent
        fieldType="dropdown"
        title="Feedback geven aan"
        options={students}
        onChange={handleStudentChange}
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
            onClick={addFeedback}
        >
            Feedback opslaan
        </button>
    </div>
    </>
  );
}

export default FeedbackNodeComponent;

import React, {useState , useEffect} from 'react';
import FormFieldComponent from "./FormFieldComponent";
import {useSelector, useDispatch} from "react-redux";
import {addFeedbackActionAsync, fetchFeedbackSelectedStudentActionAsync} from "../../actions/StudentAction";

// Dummy gebruikersobject
const user = {
  id: 1,
  name: 'John Doe',
  feedback: 'Dit is mijn feedback',
};

// Dummy sessionStorage instellen
sessionStorage.setItem('currentUser', JSON.stringify(user));

function FeedbackNodeComponent() {
    const dispatch = useDispatch();
    const students = useSelector((state) => state.student.selectedStudents);
    const getFeedback = useSelector((state) => state.student.feedback);
    const currentNodeId = useSelector((state) => state.node.currentNode);
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    // const userId = currentUser.id;

    const [feedback, setFeedback] = useState("");
    const [studentId, setStudentId] = useState("");
    const [customAlert, setCustomAlert] = useState("");

    useEffect(() => {
      if (currentUser) {
        setFeedback(currentUser.feedback);
      }
    }, [currentUser]);

    const handleFeedbackChange = (event) => {
        setFeedback(event.target.value);
    };

    const handleStudentChange = (event) => {
      const selectedStudentId = event.target.value;
      setStudentId(selectedStudentId);
      dispatchFeedback(currentNodeId, selectedStudentId);
    }

    const dispatchFeedback = (currentNodeId, selectedStudentId) => {
      setFeedback(fetchFeedbackSelectedStudentActionAsync(currentNodeId, selectedStudentId));
    }
  

    const addFeedback = () => {
      if (feedback === "") {
        setCustomAlert("Feedback mag niet leeg zijn");
        return;
      }
      if (studentId === "") {
        setCustomAlert("Selecteer een student");
        return;
      }
      setCustomAlert("");
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
        value={getFeedback}
        onChange={handleFeedbackChange}
      />
      {customAlert !== "" ?
        <div className="mt-10 rounded relative justify-center flex" role="alert">
          <div className="w-3/5 text-center bg-red-100 border border-red-400 text-red-700 px-4 py-3 ">
            <span className="block sm:inline">{customAlert}</span>
          </div>
        </div> : null}
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

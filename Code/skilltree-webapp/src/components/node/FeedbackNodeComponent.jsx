import React, {useState , useEffect} from 'react';
import FormFieldComponent from "../FormFieldComponent";
import {useSelector, useDispatch} from "react-redux";
import {addFeedbackActionAsync, fetchFeedbackSelectedStudentActionAsync, fetchAllStudentsFromSkilltreeActionAsync} from "../../actions/StudentAction";

function FeedbackNodeComponent() {
    const dispatch = useDispatch();
    const currentNodeId = useSelector((state) => state.node.currentNode);
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    const userId = currentUser.id;
    const [students, setStudents] = useState(null);
    const [feedback, setFeedback] = useState("");
    const [studentId, setStudentId] = useState("");
    const [customAlert, setCustomAlert] = useState("");

    useEffect(() => {
      const fetchStudents = async() => {
        const students = await dispatch(fetchAllStudentsFromSkilltreeActionAsync(skilltree.id));
        setStudents(students);
        if (currentUser.roleId === 1) {
          setStudentId(students[0].id);
        } else if (currentUser.roleId === 2) {
          setStudentId(currentUser.id);
        }
      } 
      fetchStudents();
    }, []);

    useEffect(() => {
      if (studentId) {
        fetchFeedback(currentNodeId, studentId);
      }
    }, [studentId])

    const handleFeedbackChange = (event) => {
      setFeedback(event.target.value);
    };

    const handleStudentChange = (event) => {
      const selectedStudentId = event.target.value;
      setStudentId(selectedStudentId);
    }

    const fetchFeedback = async (currentNodeId, selectedStudentId) => {
      const feedbacks = await dispatch(fetchFeedbackSelectedStudentActionAsync(currentNodeId, selectedStudentId));
      if (feedbacks.length > 0) {
        setFeedback(feedbacks[0].feedback);
      } else {
        setFeedback("Nog geen feedback");
      }
    }

    const addFeedback = () => {
      if (feedback === "") {
        setCustomAlert("Feedback mag niet leeg zijn");
        return;
      }
      if (studentId === "" || userId === "") {
        setCustomAlert("Selecteer een student");
        return;
      }
      setCustomAlert("");
      dispatch(addFeedbackActionAsync(currentNodeId, studentId, feedback));
    }
    
    return (
        <>
          <div className="mt-3 gap-x-6 gap-y-8 justify-center flex">
            <select
              name={"Feedback geven aan"}
              onChange={handleStudentChange}
              className="text-center inline-flex items-center block w-80 px-4 py-2 placeholder-gray-400 border border-black rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            >
              {
                currentUser && currentUser.roleId === 2 ? (
                  <option value={currentUser.id}>
                    {currentUser.firstname} {currentUser.lastname}
                  </option>
                ) : null
              }
              {
                students !== null ? (
                  students.map(option => {
                    if (currentUser && option.id === currentUser.id) {
                      return null; 
                    }
                    return (
                      <option key={option.id} value={option.id}>
                        {option.firstname} {option.lastname}
                      </option>
                    );
                  })
                ) : null
              }
            </select>
          </div>
          <FormFieldComponent
            fieldType="textarea"
            title="Feedback"
            value={feedback}
            onChange={handleFeedbackChange}
          />
          {customAlert !== "" ?
            <div className="mt-3 rounded relative justify-center flex" role="alert">
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

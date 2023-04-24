/* eslint-disable max-len */
import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import FormFieldComponent from "./FormFieldComponent";
import { fetchCreateNodeActionAsync } from "../../actions/Node";
// import { deleteNodeCardAction } from "../../actions/NodeCard";

import "../../styles/styles.css";

function CreateNodeComponent() {
    const [skill, setSkill] = useState("");
    const [description, setDescription] = useState("");
    const [assessmentCriteria, setAssessmentCriteria] = useState([]);
    const [learningOutcome, setLearningOutcome] = useState("");
    const allowedValues = ["o", "v", "g"];

    const handleSkillChange = (event) => {
        setSkill(event.target.value);
    };

    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };

    const handleAssessmentCriteriaChange = (event) => {
        const { value } = event.target;
        if (allowedValues.includes(value)) {
            if (!assessmentCriteria.includes(value)) {
                setAssessmentCriteria([...assessmentCriteria, value]);
            }
        } else {
            setAssessmentCriteria(assessmentCriteria.filter((val) => val !== value));
        }
    };

    const handleLearningOutcomeChange = (event) => {
        setLearningOutcome(event.target.value);
    };

    const dispatch = useDispatch();
    // const navigate = useNavigate();

    const [show, setShow] = useState(true);
    const deleteCard = () => {
        setShow(false);
        // navigate("/home");
    };

    // TODO: x,y,en ID meegeven
    const handleSave = () => {
        dispatch(fetchCreateNodeActionAsync(skill, description, assessmentCriteria, learningOutcome));
        deleteCard();
    };
    /* eslint-disable react/jsx-wrap-multilines */
    /* eslint-disable operator-linebreak */
    return (
        <div>
            {show ?
                <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                    <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-5/6">
                        <div className="bg-white px-4 pb-4 pt-5">
                            <FormFieldComponent titel="Vaardigheid" type="text" value={skill} onChange={handleSkillChange} />
                            <FormFieldComponent titel="Beschrijving" type="text" value={description} onChange={handleDescriptionChange} />
                            <FormFieldComponent titel="Leeruitkomst" type="text" value={learningOutcome} onChange={handleLearningOutcomeChange} />
                            <FormFieldComponent titel="BeoordelingsCriteria" type="select" value={assessmentCriteria} onChange={handleAssessmentCriteriaChange} allowedValues={["o", "v", "g"]} />
                            <div className="mt-6 flex items-center justify-center gap-x-6">
                                <button
                                    type="button"
                                    className="text-m font-semibold leading-6 text-gray-900"
                                    onClick={deleteCard}
                                >
                                    Cancel
                                </button>
                                <button
                                    type="submit"
                                    className="rounded-md bg-indigo-600 px-3 py-2 text-m font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                                    onClick={handleSave}
                                >
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div> : null}
        </div>
    );
}

export default CreateNodeComponent;

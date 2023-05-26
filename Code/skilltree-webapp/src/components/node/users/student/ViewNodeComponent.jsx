/* eslint-disable max-len */
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import FormFieldComponent from "../docent/FormFieldComponent";
import { showCreateCard } from "../../../../actions/NodeAction";
import "../../../../styles/styles.css";

function UpdateNodeComponent() {
    const nodes = useSelector((state) => state.skilltree.nodes)
    const currentNodeId = useSelector((state) => state.node.currentNode)
    const [skill, setSkill] = useState("");
    const [description, setDescription] = useState("");
    const [assessmentCriteria, setAssessmentCriteria] = useState([]);
    const [learningOutcome, setLearningOutcome] = useState("");
    const [feedback, setFeedback] = useState("");

    const dispatch = useDispatch();

    useEffect(() => {
        let currentNode = {};
        nodes.map(node => {
            if (node.id == currentNodeId) {
                currentNode = node;
                return;
            }
        })
        
        const tempArr = [];
        if(currentNode.assessmentCriteria !== undefined){
            currentNode.assessmentCriteria.map(assessmentCriterium => {
                tempArr.push(assessmentCriterium.description)
            })
        }

        setSkill(currentNode.skill ?? "");
        setDescription(currentNode.description ?? "");
        setAssessmentCriteria(tempArr ?? []);
        setLearningOutcome(currentNode.learningOutcome ?? "");
    }, []);

    const mapAssessmentCriteria = () => {
        let returnString = "";

        for (let i = 0; i < assessmentCriteria.length; i++) {
            returnString += assessmentCriteria[i];
            // zorgt er voor dat er geen '#' wordt gezet na de laatse assessmentcriterium 
            if (i !== (assessmentCriteria.length - 1)) {
                returnString += "#";
            }
        }
        return returnString;
    }

    const handleFeedbackChange = (event) => {
        setFeedback(event.target.value);
    };
    

    const hideCard = () => {
        dispatch(showCreateCard())
    };

    return (
        <div>
            <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-5/6">
                    <div className="bg-white px-4 pb-4 pt-5">
                        <FormFieldComponent
                            title="Vaardigheid" 
                            value={skill}
                            disabled={true}
                        />
                        <FormFieldComponent
                            title="Beschrijving"
                            value={description}
                            disabled={true}
                        />
                        <FormFieldComponent
                            fieldType="textarea"
                            title="Leeruitkomst"
                            value={learningOutcome}
                            disabled={true}
                        />
                        <FormFieldComponent
                            fieldType="textarea"
                            title="BeoordelingsCriteria"
                            value={mapAssessmentCriteria()}
                            disabled={true}
                        />
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
                            disabled={false}
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
                        <div className="mt-6 flex items-center justify-center space-x-4">
                            <button
                                type="button"
                                className="back-button w-50  text-white font-semibold py-2 px-4 rounded  focus:outline-none focus:ring-2 focus:ring-indigo-500"
                                onClick={hideCard}
                            >
                                Terug
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default UpdateNodeComponent;

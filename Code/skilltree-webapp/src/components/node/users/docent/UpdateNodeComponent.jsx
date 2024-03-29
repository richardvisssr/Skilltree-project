/* eslint-disable max-len */
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import FeedbackNodeComponent from "../../FeedbackNodeComponent"

import FormFieldComponent from "../../../FormFieldComponent";
import { fetchUpdateNodeActionAsync, showCreateCard } from "../../../../actions/NodeAction";
import "../../../../styles/styles.css";
import { fetchAllNodesFromSkilltree } from "../../../../actions/SkilltreeAction";
import "../nodeInfoStyle.css";

function UpdateNodeComponent() {
    const dispatch = useDispatch();

    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const currentNodeId = useSelector((state) => state.node.currentNode)

    const [skill, setSkill] = useState("");
    const [description, setDescription] = useState("");
    const [assessmentCriteria, setAssessmentCriteria] = useState([]);
    const [learningOutcome, setLearningOutcome] = useState("");
    const [positionX, setPositionX] = useState("");
    const [positionY, setPositionY] = useState("");
    const [nodes, setNodes] = useState([]);

    useEffect( () => {
        getNodes();
    }, []);

    const getNodes = async () => {
        const result = await dispatch(fetchAllNodesFromSkilltree(skilltree.id));
        setNodes(result);
    }

    useEffect(() => {
        if (nodes.length === 0) {
            return;
        }
        const currentNode = nodes.find(node => parseInt(node.id) === parseInt(currentNodeId));

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
        setPositionX(currentNode.positionX ?? "");
        setPositionY(currentNode.positionY ?? "");
    }, [nodes]);

    const handleSkillChange = (event) => {
        setSkill(event.target.value);
    };

    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };

    const handleAssessmentCriteriaChange = (event) => {
        const criteriaArray = event.target.value.split("#");
        setAssessmentCriteria(criteriaArray);
    };

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

    const handleLearningOutcomeChange = (event) => {
        setLearningOutcome(event.target.value);
    };

    const hideCard = () => {
        dispatch(showCreateCard())
    };

    const handleSave = () => {
        dispatch(fetchUpdateNodeActionAsync(skill, description, positionX, positionY, assessmentCriteria, learningOutcome, skilltree.id, currentNodeId));
        hideCard();
      };

    return (
        <div className="nodeInfoComponent">
            <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-5/6">
                    <div className="bg-white px-4 pb-4 pt-5">
                        <FormFieldComponent
                            title="Vaardigheid" 
                            label="Vaardigheid" 
                            value={skill}
                            onChange={handleSkillChange}
                        />
                        <FormFieldComponent
                            title="Beschrijving"
                            label="Beschrijving"
                            value={description}
                            onChange={handleDescriptionChange}
                        />
                        <FormFieldComponent
                            fieldType="textarea"
                            title="Leeruitkomst"
                            label="Leeruitkomst"
                            value={learningOutcome}
                            onChange={handleLearningOutcomeChange}
                        />
                        <FormFieldComponent
                            fieldType="textarea"
                            title="BeoordelingsCriteria"
                            label="Beoordelingscriteria"
                            value={mapAssessmentCriteria()}
                            onChange={handleAssessmentCriteriaChange}
                        />
                        <p className="text-center">Gebruik een '#' om een nieuwe beoordelingscriteria toe te voegen</p>
                        <FeedbackNodeComponent />
                        <div className="mt-6 flex items-center justify-center space-x-4">
                            <button
                                type="button"
                                className="back-button w-50  text-white font-semibold py-2 px-4 rounded  focus:outline-none focus:ring-2 focus:ring-indigo-500"
                                onClick={hideCard}
                            >
                                Terug
                            </button>
                            <button
                                type="submit"
                                className="save-button w-50  text-white font-semibold py-2 px-4 rounded  focus:outline-none focus:ring-2 focus:ring-indigo-500"
                                onClick={handleSave}
                            >
                                Opslaan
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default UpdateNodeComponent;

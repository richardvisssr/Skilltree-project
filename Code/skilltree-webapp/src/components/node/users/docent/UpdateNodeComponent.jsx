/* eslint-disable max-len */
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import FormFieldComponent from "../../../FormFieldComponent";
import { fetchUpdateNodeActionAsync, showCreateCard } from "../../../../actions/NodeAction";
import "../../../../styles/styles.css";
import { fetchAllNodesFromSkilltree } from "../../../../actions/SkilltreeAction";

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
        <div>
            <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-5/6">
                    <div className="bg-white px-4 pb-4 pt-5">
                        <FormFieldComponent
                            title="skill"
                            label="Vaardigheid" 
                            value={skill}
                            onChange={handleSkillChange}
                        />
                        <FormFieldComponent
                            title="description"
                            label="Beschrijving"
                            value={description}
                            onChange={handleDescriptionChange}
                        />
                        <FormFieldComponent
                            fieldType="textarea"
                            title="learningOutcome"
                            label="Leeruitkomst"
                            value={learningOutcome}
                            onChange={handleLearningOutcomeChange}
                        />
                        <FormFieldComponent
                            fieldType="textarea"
                            title="assessmentCriteria"
                            label="Beoordelingscriteria"
                            value={mapAssessmentCriteria()}
                            onChange={handleAssessmentCriteriaChange}
                        />
                        <p className="text-center">Gebruik een '#' om een nieuwe beoordelingscriteria toe te voegen</p>
                        <div className="mt-6 flex items-center justify-center gap-x-6">
                            <button
                                type="button"
                                className="text-m font-semibold leading-6 text-gray-900"
                                onClick={hideCard}
                            >
                                Terug
                            </button>
                            <button
                                type="submit"
                                className="
                                    rounded-md
                                    bg-indigo-600
                                    px-3
                                    py-2
                                    text-m
                                    font-semibold
                                    text-white
                                    shadow-sm
                                    hover:bg-indigo-500
                                    focus-visible:outline
                                    focus-visible:outline-2
                                    focus-visible:outline-offset-2
                                    focus-visible:outline-indigo-600"
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

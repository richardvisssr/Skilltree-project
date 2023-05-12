/* eslint-disable max-len */
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import FormFieldComponent from "./FormFieldComponent";
import { fetchUpdateNodeActionAsync, showCreateCard } from "../../actions/NodeAction";
import "../../styles/styles.css";

function CreateNodeComponent() {
    const skilltreeId = useSelector((state) => state.skilltree.currentSkilltree.id);
    const nodes = useSelector((state) => state.skilltree.nodes)
    const currentNodeId = useSelector((state) => state.node.currentNode)

    const [skill, setSkill] = useState("");
    const [description, setDescription] = useState("");
    const [assesmentCriteria, setAssessmentCriteria] = useState([]);
    const [learningOutcome, setLearningOutcome] = useState("");
    const [positionX, setPositionX] = useState("");
    const [positionY, setPositionY] = useState("");

    const dispatch = useDispatch();

    const updateNode = (skill, description, positionX, positionY, assesmentCriteria, learningOutcome, skilltreeId, currentNode) => {
        return fetchUpdateNodeActionAsync(skill, description, positionX, positionY, assesmentCriteria, learningOutcome, skilltreeId, currentNode);
      };
    // const [learningOutcome, setLearningOutcome] = useState("");


    useEffect(() => {
        let currentNode = {}
        nodes.map(node => {
            if (node.id == currentNodeId) {
                currentNode = node;
                return;
            }
        })
        
        const tempArr = [];
        currentNode.assesmentCriteria.map(assesmentCriterium => {
            tempArr.push(assesmentCriterium)
        })

        setSkill(currentNode.skill);
        setDescription(currentNode.description);
        setAssessmentCriteria(tempArr);
        setLearningOutcome(currentNode.learningOutcome);
        setPositionX(currentNode.positionX);
        setPositionY(currentNode.positionY);
    }, [currentNodeId]);

    const handleSkillChange = (event) => {
        setSkill(event.target.value);
    };

    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };

    const handleAssessmentCriteriaChange = (event) => {
        const criteriaArray = event.target.value.split(",");
        setAssessmentCriteria(criteriaArray);
    };

    const mapAssesmentCriteria = () => {
        let returnString = "";

        for (let i = 0; i < assesmentCriteria.length; i++) {
            returnString += assesmentCriteria[i].description;
            // zorgt er voor dat er geen komma wordt gezet na de laatse assesmentcriterium 
            if (i !== (assesmentCriteria.length - 1)) {
                returnString += ",";
            }
        }
        return returnString;
    }

    const handleLearningOutcomeChange = (event) => {
        setLearningOutcome(event.target.value);
    };

    const [cardShowState, setCardShowState] = useState(true);

    const hideCard = () => {
        dispatch(showCreateCard())
    };

    const handleSave = () => {
        dispatch(updateNode(skill, description, positionX, positionY, assesmentCriteria, learningOutcome, skilltreeId, currentNodeId));
        hideCard();
      };

    return (
        <div>
            { cardShowState ?
                <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                    <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-5/6">
                        <div className="bg-white px-4 pb-4 pt-5">
                            <FormFieldComponent
                                titel="Vaardigheid"
                                type="text" value={skill}
                                onChange={handleSkillChange}
                            />
                            <FormFieldComponent
                                titel="Beschrijving"
                                type="text"
                                value={description}
                                onChange={handleDescriptionChange}
                            />
                            <FormFieldComponent
                                titel="Leeruitkomst"
                                type="text"
                                value={learningOutcome}
                                onChange={handleLearningOutcomeChange}
                            />
                            <FormFieldComponent
                                titel="BeoordelingsCriteria"
                                type="text"
                                value={mapAssesmentCriteria()}
                                onChange={handleAssessmentCriteriaChange}
                            />
                            <p className="text-center">Gebruik een komma om een nieuwe BeoordelingsCriteria toe te voegen</p>
                            <div className="mt-6 flex items-center justify-center gap-x-6">
                                <button
                                    type="button"
                                    className="text-m font-semibold leading-6 text-gray-900"
                                    onClick={hideCard}
                                >
                                    Annuleren
                                </button>
                                <button
                                    type="submit"
                                    className="rounded-md bg-indigo-600 px-3 py-2 text-m font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                                    onClick={handleSave}
                                >
                                    Opslaan
                                </button>
                            </div>
                        </div>
                    </div>
                </div> : null }
        </div>
    );
}

export default CreateNodeComponent;

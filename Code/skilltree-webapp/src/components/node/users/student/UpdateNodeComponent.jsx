/* eslint-disable max-len */
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import FormFieldComponent from "../docent/FormFieldComponent";
import { fetchUpdateNodeActionAsync, showCreateCard } from "../../../../actions/NodeAction";
import "../../../../styles/styles.css";

function UpdateNodeComponent() {
    const skilltreeId = useSelector((state) => state.skilltree.currentSkilltree.id);
    const nodes = useSelector((state) => state.skilltree.nodes)
    const currentNodeId = useSelector((state) => state.node.currentNode)
    const [skill, setSkill] = useState("");
    const [description, setDescription] = useState("");
    const [assessmentCriteria, setAssessmentCriteria] = useState([]);
    const [learningOutcome, setLearningOutcome] = useState("");
    const [positionX, setPositionX] = useState("");
    const [positionY, setPositionY] = useState("");

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
        setPositionX(currentNode.positionX ?? "");
        setPositionY(currentNode.positionY ?? "");
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

    const [cardShowState, setCardShowState] = useState(true);

    const hideCard = () => {
        dispatch(showCreateCard())
    };

    return (
        <div>
            { cardShowState ?
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
                            <div className="mt-6 flex items-center justify-center gap-x-6">
                                <button
                                    type="button"
                                    className="text-m font-semibold leading-6 text-gray-900"
                                    onClick={hideCard}
                                >
                                    Terug
                                </button>
                            </div>
                        </div>
                    </div>
                </div> : null }
        </div>
    );
}

export default UpdateNodeComponent;

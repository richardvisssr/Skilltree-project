/* eslint-disable max-len */
import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import FormFieldComponent from "../../../FormFieldComponent";
import { showCreateCard } from "../../../../actions/NodeAction";
import "../../../../styles/styles.css";
import {fetchAllNodesFromSkilltree} from "../../../../actions/SkilltreeAction";

function ViewNodeComponent() {
    const dispatch = useDispatch();

    const currentNodeId = useSelector((state) => state.node.currentNode)
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);

    const [skill, setSkill] = useState("");
    const [description, setDescription] = useState("");
    const [assessmentCriteria, setAssessmentCriteria] = useState([]);
    const [learningOutcome, setLearningOutcome] = useState("");

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
    }, [nodes]);

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

    const hideCard = () => {
        dispatch(showCreateCard())
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
                            disabled={true}
                        />
                        <FormFieldComponent
                            title="description"
                            label="Beschrijving"
                            value={description}
                            disabled={true}
                        />
                        <FormFieldComponent
                            fieldType="textarea"
                            title="learningOutcome"
                            label="Leeruitkomst"
                            value={learningOutcome}
                            disabled={true}
                        />
                        <FormFieldComponent
                            fieldType="textarea"
                            title="assessmentCriteria"
                            label="Beoordelingscriteria"
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
            </div>
        </div>
    );
}

export default ViewNodeComponent;

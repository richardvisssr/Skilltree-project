import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import { fetchDeleteNodeActionAsync } from "../../../../actions/NodeAction";

import "../../../../styles/styles.css";
import {fetchDeleteEdgeActionAsync} from "../../../../actions/EdgeAction";
import {fetchAllEdgesFromSkilltree} from "../../../../actions/SkilltreeAction";

export function DeleteNodeComponent(props) {
    const dispatch = useDispatch();
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const nodeId = props.nodeId;
    const [edges, setEdges] = useState([]);

    useEffect( () => {
        getEdges();
    }, []);

    const getEdges = async () => {
        const result = await dispatch(fetchAllEdgesFromSkilltree(skilltree.id));
        setEdges(result);
    }

    const deleteCard = () => {
        console.log(edges);
        edges.map(edge => {
            if (edge.sourceId === nodeId) {
                dispatch(fetchDeleteEdgeActionAsync(edge.edgeId, nodeId));
            } else if (edge.targetId === nodeId) {
                dispatch(fetchDeleteEdgeActionAsync(edge.edgeId, nodeId));
            }
        });

        dispatch(fetchDeleteNodeActionAsync(nodeId));
        hideCard();
    };

    const hideCard = () => {
        props.showDeleteNodeComponent();
    };

    return (
        <div>
            <div className="relative z-[10000]" aria-labelledby="modal-title" role="dialog" aria-modal="true">
                <div className="fixed inset-0  top-0 left-0 right-0 h-80 w-80 flex min-h-full justify-center p-4 text-center sm:items-center sm:p-0">
                    <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-5/6">
                        <div className="bg-white px-4 pb-4 pt-5">
                            <p className="text-center">Weet u zeker dat u deze node wil verwijderen?</p>
                            <div className="mt-6 flex items-center justify-center gap-x-6">
                                <button type="button" className="text-m font-semibold leading-6 text-gray-900" onClick={hideCard}>Annuleren</button>
                                <button
                                    type="submit"
                                    onClick={deleteCard}
                                    className="rounded-md bg-indigo-600 px-3 py-2 text-m
                                     font-semibold text-white shadow-sm hover:bg-indigo-500
                                      focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2
                                       focus-visible:outline-indigo-600"
                                >
                                    Verwijderen
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

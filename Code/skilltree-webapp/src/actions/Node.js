const API_PATH = process.env.REACT_APP_API_URL;

export function setCreateNodeAction(skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria) {
    return {
        type: "node/createNode",
        payload: {
            skill,
            description,
            positionX,
            positionY,
            skilltreeId,
            learningOutcome,
            assesmentCriteria: [assesmentCriteria],
        },
    };
}

export const fetchCreateNodeActionAsync = (skill, description, assesmentCriteria, learningOutcome) => async (dispatch) => {
    const positionX = 100;
    const positionY = 200;
    const skilltreeId = 3;

    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify({
            skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria,
        }),

    };
    fetch(`${API_PATH}/nodes/skilltrees/${skilltreeId}`, options)
        .then((response) => response.json())
        .then(() => {
            dispatch(setCreateNodeAction(skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria));
        });
};

export function setDeleteNodeAction(nodeId) {
    return {
        type: "node/deleteNode",
        payload: {
            nodeId,
        }
    }
}

export const fetchDeleteNodeActionAsync = (nodeId) => async (dispatch) => {

    const options = {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify({
            nodeId
        }),

    };

    fetch(`${API_PATH}/nodes/${nodeId}`, options)
        .then((response) => response.json())
        .then(() => {
            dispatch(setDeleteNodeAction(nodeId));
        });

}

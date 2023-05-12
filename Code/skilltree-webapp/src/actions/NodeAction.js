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

export function fetchCreateNodeActionAsync(skill, description, positionX, positionY, assesmentCriteria, learningOutcome, skilltreeId) {
    return async (dispatch) => {
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
};

export function setHighestNodeIdAction(nodeId) {
    return {
        type: "node/highestNodeId",
        payload: nodeId,
    };
}

export function fetchHighestNodeIdActionAsync() {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        fetch(`${API_PATH}/nodes`, options)
            .then((response) => response.json())
            .then((result) => dispatch(setHighestNodeIdAction(result)));
    };
}

export function showCreateCard() {
    return {
        type: "node/showNodeCard"
    };
}

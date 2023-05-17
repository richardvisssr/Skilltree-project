const API_PATH = process.env.REACT_APP_API_URL;

export function setCreateNodeAction(nodeId, skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria) {
    console.log("3");
    return {
        type: "skilltree/createNode",
        payload: {
            id: nodeId,
            skill,
            description,
            positionX,
            positionY,
            skilltreeId,
            learningOutcome,
            assesmentCriteria,
        },
    };
}

export function fetchCreateNodeActionAsync(nodeId, skill, description, positionX, positionY, assesmentCriteria, learningOutcome, skilltreeId) {
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
                dispatch(setCreateNodeAction(nodeId, skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria));
            });
    };
};

export function setUpdateNodeAction(skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria,) {
    return {
        type: "node/updateNode",
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

export const fetchUpdateNodeActionAsync = (skill, description, positionX, positionY, assesmentCriteria, learningOutcome, skilltreeId, nodeId) => async (dispatch) => {
    const options = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify({
            skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria,
        }),

    };
    fetch(`${API_PATH}/nodes/${nodeId}`, options)
        .then((response) => response.json())
        .then(() => {
            dispatch(setUpdateNodeAction(skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria));
    });
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

export function currentNodeSelectedAction(nodeId) {
    return {
        type: "node/setCurrentNode",
        payload: nodeId
    }
};

export function showCreateCard(currentNodeId) {
    return {
        type: "node/showNodeCard",
    };

}

export function fetchAllNodesPositionsActionAsync(skilltreeId, nodes) {
    return async (dispatch) => {
        const options = {
            headers: {
                "Content-Type": "application/json",
            },
            method: "PUT",
            mode: "cors",
            body: JSON.stringify({
                nodes,
            }),
        };
        fetch(`${API_PATH}/nodes/skilltrees/${skilltreeId}`, options)
            .then((response) => response.json())
    };
}

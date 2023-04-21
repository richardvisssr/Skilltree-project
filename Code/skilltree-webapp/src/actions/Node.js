const API_PATH = process.env.REACT_APP_API_URL;

export function setCreateNodeAction(skill, description, assessmentCriteria, learningOutcome) {
    return {
        type: "node/createNode",
        payload: {
            skill,
            description,
            assessmentCriteria,
            learningOutcome,
        },
    };
}

export const fetchCreateNodeActionAsync = (skill, description, assessmentCriteria, learningOutcome) => async (dispatch) => {
    const options = {
        method: "POST",
        body: JSON.stringify({
            skill, description, assessmentCriteria, learningOutcome,
        }),
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
    };
    fetch(`${API_PATH}/nodes/create/`, options)
        .then((response) => response.json())
        .then(() => {
            dispatch(setCreateNodeAction(skill, description, assessmentCriteria, learningOutcome));
        });
};

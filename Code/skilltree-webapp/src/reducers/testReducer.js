const initialState = {
    skill: "",
    description: "",
    assessmentCriteria: "",
    learningOutcome: "",
};

// eslint-disable-next-line default-param-last
const createNodeReducer = (state = initialState, action) => {
    switch (action.type) {
    case "node/createNode":
        return {
            ...state,
            skill: action.payload.skill,
            description: action.payload.description,
            assessmentCriteria: action.payload.assessmentCriteria,
            learningOutcome: action.payload.learningOutcome,
        };
    default:
        return state;
    }
};

export default createNodeReducer;

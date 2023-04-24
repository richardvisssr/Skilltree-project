const initialState = {
    skill: "",
    description: "",
    positionX: "",
    positionY: "",
    skillTreeId: "",
    learningOutcome: "",
    assesmentCriteria: "",
};

// eslint-disable-next-line default-param-last
const createNodeReducer = (state = initialState, action) => {
    switch (action.type) {
    case "node/createNode":
        return {
            ...state,
            skill: action.payload.skill,
            description: action.payload.description,
            positionX: action.payload.positionX,
            positionY: action.payload.positionY,
            skillTreeId: action.payload.skillTreeId,
            learningOutcome: action.payload.learningOutcome,
            assesmentCriteria: action.payload.assesmentCriteria,
        };
    default:
        return state;
    }
};

export default createNodeReducer;

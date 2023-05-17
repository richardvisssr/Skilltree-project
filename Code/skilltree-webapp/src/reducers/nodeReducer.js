const initialState = {
    skill: "",
    description: "",
    positionX: "",
    positionY: "",
    skillTreeId: "",
    learningOutcome: "",
    assessmentCriteria: "",
    showCard: false,
    highestNodeId: null,
};

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
            assessmentCriteria: action.payload.assessmentCriteria,
        };
    case "node/showNodeCard":
        return {
            ...state,
            showCard: !state.showCard,
        }
    case "node/highestNodeId":
        return {
            ...state,
            highestNodeId: action.payload
        }
    default:
        return state;
    }
};

export default createNodeReducer;

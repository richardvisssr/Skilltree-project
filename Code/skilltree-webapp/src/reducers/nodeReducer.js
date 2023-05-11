const initialState = {
    nodeId: null,
    skill: "",
    description: "",
    positionX: "",
    positionY: "",
    skillTreeId: "",
    learningOutcome: "",
    assesmentCriteria: "",
    showCard: false,
    highestNodeId: null,
};

// eslint-disable-next-line default-param-last
const nodeReducer = (state = initialState, action) => {
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
    case "node/deleteNode":
        return {
            ...state,
            nodeId: action.payload.nodeId,
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

export default nodeReducer;

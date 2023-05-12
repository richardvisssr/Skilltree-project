const initialState = {
    students: [],
    showCard: false,
};

// eslint-disable-next-line default-param-last
function studentsReducer(state = initialState, action) {
    switch (action.type) {
    case "students/setStudents":
        return {
            ...state,
            students: action.payload,
        };
        case "students/showStudentCard":
            return {
                ...state,
                showCard: !state.showCard,
            }
    default:
        return state;
    }
}
export default studentsReducer;
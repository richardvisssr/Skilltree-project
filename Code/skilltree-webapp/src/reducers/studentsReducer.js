const initialState = {
    students: [],
    showCard: false,
};

function studentsReducer(state = initialState, action) {
    switch (action.type) {
    case "students/setStudents":
        return {
            ...state,
            students: action.payload.students,
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
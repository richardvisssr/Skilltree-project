const initialState = {
    students: [],
    showCard: false,
    selectedStudents: []
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
        case "students/setSelectedStudents":
            return {
                ...state,
                selectedStudents: action.payload,
            }
        case "students/clearStudentCard":
            return {
                ...state,
                selectedStudents : [],
                showCard: false,
            }
    default:
        return state;
    }
}
export default studentsReducer;
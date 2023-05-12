const API_PATH = process.env.REACT_APP_API_URL;

export function setStudentsAction(students) {
    return {
        type: "students/setStudents",
        payload: students,
    };
}

export function fetchAllStudentsActionAsync(userId, currentSkilltreeId) {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        fetch(`${API_PATH}/skilltrees/gebruikers/${userId}`, options)
            .then((response) => response.json())
            .then((result) => dispatch(setStudentsAction(result.students)));
    };
}

export function showStudentCard() {
    return {
        type: "students/showStudentCard"
    };
}
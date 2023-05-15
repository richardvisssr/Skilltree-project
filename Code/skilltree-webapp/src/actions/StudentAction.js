const API_PATH = process.env.REACT_APP_API_URL;

export function setStudentsAction(students) {
    return {
        type: "students/setStudents",
        payload: students,
    };
}

export function fetchAllStudentsActionAsync() {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        fetch(`${API_PATH}/students`, options)
            .then((response) => response.json())
            .then((result) => dispatch(setStudentsAction(result)));
    };
}

export function showStudentCard() {
    return {
        type: "students/showStudentCard"
    };
}

export function setSelectedStudensAction(students) {
    return {
        type: "students/setSelectedStudents",
        payload: students
    }
}

export function fetchLinkStudentsToSkilltreeActionAsync(skilltreeId, students) {
    return async (dispatch) => {
        //TODO GET gekoppelde studenten en vergelijk de arrays
        console.log(skilltreeId, students);
        // const options = {
        //     method: "POST",
        //     headers: {
        //         "Content-Type": "application/json",
        //     },
        //     mode: "cors",
        //     body: JSON.stringify({
        //         students
        //     }),

        // };

        // fetch(`${API_PATH}/students/skilltrees/${skilltreeId}`, options)
    };
}
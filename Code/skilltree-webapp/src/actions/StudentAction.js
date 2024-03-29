const API_PATH = process.env.REACT_APP_API_URL;

export function setStudentsAction(students) {
    return {
        type: "students/setStudents",
        payload: students,
    };
}

export function fetchAllStudentsActionAsync() {
    return async () => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        return fetch(`${API_PATH}/students`, options)
            .then((response) => response.json())
            .then((result) => result.users);
    };
}

export function showStudentCard() {
    return {
        type: "students/showStudentCard"
    };
}

export function setSelectedStudentsAction(students) {
    return {
        type: "students/setSelectedStudents",
        payload: students
    }
}

export function fetchLinkStudentsToSkilltreeActionAsync(skilltreeId, students) {
    return async () => {
        const studentIds = [];
        students.forEach(student => {
            studentIds.push({"id": student.id})
        });

        const options = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            mode: "cors",
            body: JSON.stringify({
                "students": studentIds
            }),
        };

        return fetch(`${API_PATH}/students/skilltrees/${skilltreeId}`, options)
            .then(response => response.json())
            .then((result) => result.users);
    };
}

export function fetchAllStudentsFromSkilltreeActionAsync(skilltreeId) {
    return async () => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        return fetch(`${API_PATH}/students/skilltrees/${skilltreeId}`, options)
            .then((response) => response.json())
            .then((result) => result.users);
    };
}

export function clearStudentCardAction() {
    return {
        type: "students/clearStudentCard"
    };
}

export function addFeedbackActionAsync(currentNodeId, studentId, feedback) {
    return async () => {
        const options = {
            body: JSON.stringify({
                "userId": parseInt(studentId),
                "nodeId": parseInt(currentNodeId),
                feedback
            }),
            headers: {
                "Content-Type": "application/json",
            },
            method: "POST",
            mode: "cors"
        };
        fetch(`${API_PATH}/feedback`, options)
            .then((response) => response.json())
    }
}

export function fetchFeedbackSelectedStudentActionAsync(nodeId, studentId) {
    return async () => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        return fetch(`${API_PATH}/feedback/nodes/${nodeId}/students/${studentId}`, options)
            .then((response) => response.json())
            .then((result) => result.feedbacks)
    };
}


// const API_PATH = process.env.REACT_APP_API_URL;

export function deleteNodeCardAction(id) {
    return {
        type: "node/deleteNodeCard",
        payload: {
            id,
        },
    };
}

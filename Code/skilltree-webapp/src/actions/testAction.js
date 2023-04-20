export function setTestAction(test = "test") {
    return {
        type: "test/testAction",
        payload: test,
    };
}

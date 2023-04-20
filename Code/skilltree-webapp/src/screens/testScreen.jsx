import React from "react";

import TestComponent from "../components/testComponent";
import CreateNodeComponent from "../components/createNode/CreateNodeComponent";

export default function testScreen() {
    return (
        <div>
            <h1>Test screen</h1>
            <TestComponent />
            <CreateNodeComponent />
        </div>
    );
}

import React from "react";
import { useSelector } from "react-redux";

import SidebarComponentStudent from "../../components/users/student/SidebarComponent";
import TopbarComponentStudent from "../../components/users/student/TopbarComponent";
import ReactFlowComponentStudent from "../../components/users/student/ReactFlowComponent";
import NoSkilltreeComponent from "../../components/NoSkilltreeComponent";
import ViewNodeComponent from "../../components/node/users/student/ViewNodeComponent";

export default function StudentView() {
    const currentSkilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const newSkilltree = useSelector((state) => state.skilltree.newSkilltree);
    const showNodeCard = useSelector((state) => state.node.showCard);

    const showScreen = () => {
        if (showNodeCard) {
            return (
                <ViewNodeComponent />
            )
        }
        else if (currentSkilltree !== null) {
            return (
                <ReactFlowComponentStudent />
            );
        }
        return null;
    }

    const skilltreeSelected = () => {
        if (currentSkilltree !== null) {
            return (
                <div className="w-full h-full flex flex-col">
                    <TopbarComponentStudent />
                    {showScreen()}
                </div>
            );
        } else if (currentSkilltree == null && newSkilltree){
            return (
                <div className="w-full h-full flex flex-col">
                    <TopbarComponentStudent currentSkilltree={currentSkilltree} newSkilltree={newSkilltree} />
                    <div className="text-center align-middle m-auto"><NoSkilltreeComponent /></div>
                    {showScreen()}
                </div>
            );
        }
        return null;
    };

    return (
        <div className="flex-row flex w-screen">
            <SidebarComponentStudent />
            <div className="flex flex-col w-full h-screen">
                {skilltreeSelected()}
            </div>
        </div>
    );
}

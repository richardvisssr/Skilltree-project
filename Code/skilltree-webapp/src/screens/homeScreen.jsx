import React from "react";
import { useSelector } from "react-redux";

import SidebarComponent from "../components/SidebarComponent";
import TopbarComponent from "../components/TopbarComponent";
import ReactFlowComponent from "../components/ReactFlowComponent";
import NoSkilltreeComponent from "../components/NoSkilltreeComponent";
import CreateNodeComponent from "../components/createNode/UpdateNodeComponent";

export default function HomeScreen() {
    const currentSkilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const newSkilltree = useSelector((state) => state.skilltree.newSkilltree);
    const showNodeCard = useSelector((state) => state.node.showCard);

    const showScreen = () => {
        if (showNodeCard) {
            return (
                <CreateNodeComponent />
            )
            }else if(currentSkilltree == null){
                <ReactFlowComponent />
            }else if(currentSkilltree !== null){
            return (
                <ReactFlowComponent />
            )
        }
    }

    const skilltreeSelected = () => {
        if (currentSkilltree !== null) {
            return (
                <div className="w-full h-full flex flex-col">
                    <TopbarComponent />
                    {showScreen()}
                </div>
            );
        } else if (currentSkilltree == null && newSkilltree){
        return (
            <div className="w-full h-full flex flex-col">
                <TopbarComponent currentSkilltree={currentSkilltree} newSkilltree={newSkilltree} />
                <div className="text-center align-middle m-auto"><NoSkilltreeComponent /></div>
                {showScreen()}
            </div>
        );
        }
    };

    return (
        <div className="flex-row flex w-screen">
            <SidebarComponent />
            <div className="flex flex-col w-full h-screen">
                {skilltreeSelected()}
            </div>
        </div>
    );
}

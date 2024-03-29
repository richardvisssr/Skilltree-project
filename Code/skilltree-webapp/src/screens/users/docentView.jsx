import React from "react";
import { useSelector } from "react-redux";

import SidebarComponentDocent from "../../components/users/docent/SidebarComponent";
import TopbarComponentDocent from "../../components/users/docent/TopbarComponent";
import ReactFlowComponentDocent from "../../components/users/docent/ReactFlowComponent";
import NoSkilltreeComponent from "../../components/NoSkilltreeComponent";
import UpdateNodeComponent from "../../components/node/users/docent/UpdateNodeComponent";
import DeleteSkilltreeComponent from "../../components/users/docent/DeleteSkilltreeComponent";

export default function DocentView() {
    const currentSkilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const newSkilltree = useSelector((state) => state.skilltree.newSkilltree);
    const showNodeCard = useSelector((state) => state.node.showCard);
    const showDeleteSkilltreeCard = useSelector((state) => state.skilltree.showDeleteSkilltreeCard)

    const showScreen = () => {
        if (showNodeCard) {
            return (
                <UpdateNodeComponent />
            )
        }
        else if (showDeleteSkilltreeCard) {
            return (
                <DeleteSkilltreeComponent />
            )
        }

        else if (currentSkilltree !== null) {
            return (
                <ReactFlowComponentDocent />
            );
        }
        return null;
    }

    const skilltreeSelected = () => {
        if (currentSkilltree !== null) {
            return (
                <div className="w-full h-full flex flex-col">
                    <TopbarComponentDocent />
                    {showScreen()}
                </div>
            );
        } else if (currentSkilltree == null && newSkilltree){
            return (
                <div className="w-full h-full flex flex-col">
                    <TopbarComponentDocent currentSkilltree={currentSkilltree} newSkilltree={newSkilltree} />
                    <div className="text-center align-middle m-auto"><NoSkilltreeComponent /></div>
                    {showScreen()}
                </div>
            );
        }
        return null;
    };

    return (
        <div className="flex-row flex w-screen">
            <SidebarComponentDocent />
            <div className="flex flex-col w-full h-screen">
                {skilltreeSelected()}
            </div>
        </div>
    );
}

import { useDispatch, useSelector } from "react-redux";

import {
    hideDeleteCard,
    fetchDeleteSkilltreeActionAsync,
    setCurrentSkilltreeAction,
} from "../../../actions/SkilltreeAction";

import "../../../styles/styles.css";

function DeleteSkilltreeComponent() {
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const skilltreeTitle = skilltree.title;
    const skilltreeId = skilltree.id;

    const dispatch = useDispatch();

    const hideCard = () => {
        dispatch(hideDeleteCard())
    };

    const handleDelete = () => {
        hideCard();
        dispatch(fetchDeleteSkilltreeActionAsync(skilltreeId))
        dispatch(setCurrentSkilltreeAction(null));
    };

    return (
        <div className="flex h-full items-center justify-center">
            <div className="w-2/3">
                <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                    <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-5/6">
                        <div className="bg-white px-4 pb-4 pt-5">
                            <p className="text-center">Weet u zeker dat u skilltree "{skilltreeTitle}" wil verwijderen?</p>
                            <div className="mt-6 flex items-center justify-center gap-x-6">
                                <button
                                    type="button"
                                    className="text-m font-semibold leading-6 text-gray-900"
                                    onClick={hideCard}
                                >
                                    Terug
                                </button>
                                <button
                                    type="submit"
                                    className="
                                        rounded-md
                                        bg-indigo-600
                                        px-3
                                        py-2
                                        text-m
                                        font-semibold
                                        text-white
                                        shadow-sm
                                        hover:bg-indigo-500
                                        focus-visible:outline
                                        focus-visible:outline-2
                                        focus-visible:outline-offset-2
                                        focus-visible:outline-indigo-600"
                                    onClick={handleDelete}
                                >
                                    Verwijderen
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default DeleteSkilltreeComponent;

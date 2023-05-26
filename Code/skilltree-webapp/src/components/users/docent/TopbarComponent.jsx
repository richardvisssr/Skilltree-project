import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Disclosure} from '@headlessui/react' // Menu, Transition
import { Bars3Icon, XMarkIcon } from '@heroicons/react/24/outline' // BellIcon
import { fetchCreateSkillTreeActionAsync, fetchUpdateSkillTreeActionAsync } from '../../../actions/SkilltreeAction';
import { fetchLinkStudentsToSkilltreeActionAsync, clearStudentCardAction, showStudentCard } from '../../../actions/StudentAction';

export default function TopbarComponent() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [firstRenderDone, setFirstRenderDone] = useState(false);

    const userId = useSelector((state) => state.user.userId);
    const newSkilltree = useSelector((state) => state.skilltree.newSkilltree);
    const currentSkilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const selectedStudents = useSelector((state) => state.student.selectedStudents);

    const dispatch = useDispatch();

    const handleButton = () => {
        if (firstRenderDone) {
            dispatch(fetchLinkStudentsToSkilltreeActionAsync(currentSkilltree.id, selectedStudents));
        }
        setFirstRenderDone(!firstRenderDone);
        dispatch(showStudentCard());
    };

    const onDragStart = (event, nodeType) => {
        event.dataTransfer.setData('application/reactflow', nodeType);
        event.dataTransfer.effectAllowed = 'move';
    };

    const handleSave = () => {
        if (title === '') {
            return;
        }
        else if (description === '') {
            return;
        }

        if (newSkilltree) {
            dispatch(fetchCreateSkillTreeActionAsync(title, description, userId));
        } else if (currentSkilltree !== null) {
            dispatch(fetchUpdateSkillTreeActionAsync(currentSkilltree.id, title, description, userId));
        }
    };

    useEffect(() => {
        if (newSkilltree) {
            setTitle("");
            setDescription("");
        } else {
            setTitle(currentSkilltree.title);
            setDescription(currentSkilltree.description);
        }
        dispatch(clearStudentCardAction());
    }, [currentSkilltree, newSkilltree]);

    const koppelButton = () => {
        if (!newSkilltree) {
            return (
                <div className="hidden lg:ml-6 lg:block">
                    <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                        <button
                            className="bg-pink-700 dark:bg-pink-700 text-white hover:bg-pink-900 hover:text-white rounded-md w-fit mx-3 px-5 py-2 text-sm font-bold"
                            type="button"
                            onClick={handleButton}
                        >
                            Student Koppelen
                        </button>
                    </div>
                </div>
            )
        }
        return null;
    }

    return (
        <Disclosure as="nav" className="bg-gray-50 dark:bg-gray-800">
            {({ open }) => (
                <>
                    <div className="mr-auto px-2 sm:px-6 lg:px-8">
                        <div className="relative flex h-16 items-center justify-between">
                            <div className="inset-y-0 left-0 flex items-center lg:hidden">
                                {/* Mobile menu button */}
                                <Disclosure.Button
                                    className="
                                        inline-flex
                                        items-center
                                        justify-center
                                        rounded-md
                                        p-2
                                        text-gray-400
                                        hover:bg-gray-700
                                        hover:text-white
                                        focus:outline-none
                                        focus:ring-2
                                        focus:ring-inset
                                        focus:ring-white"
                                >
                                    <span className="sr-only">Open menu</span>
                                    {open ? (
                                        <XMarkIcon className="block h-6" aria-hidden="true" />
                                    ) : (
                                        <Bars3Icon className="block h-6" aria-hidden="true" />
                                    )}
                                </Disclosure.Button>
                            </div>
                            <div className="flex flex-1 items-center justify-between">
                                <div className="flex w-3/5">
                                    <div className="flex items-center">
                                        <img
                                            className="block h-8 lg:hidden"
                                            src="https://www.stipbike.nl/wp-content/uploads/2020/02/HAN-logo.png"
                                            alt="HAN"
                                        />
                                        <img
                                            className="hidden h-8 lg:block"
                                            src="https://www.stipbike.nl/wp-content/uploads/2020/02/HAN-logo.png"
                                            alt="HAN"
                                        />
                                    </div>
                                    <div className="hidden lg:ml-6 lg:block">
                                        <div className="flex space-x-4">
                                            <div className="relative rounded-md shadow-sm">
                                                <input
                                                    type="text"
                                                    value={title}
                                                    name="title"
                                                    id="title"
                                                    className="
                                                        rounded-md
                                                        mx-3 py-2
                                                        text-sm
                                                        font-medium
                                                        bg-gray-100
                                                        text-black
                                                        hover:bg-gray-200
                                                        hover:text-black
                                                        dark:bg-gray-
                                                        dark:text-gray-300
                                                        dark:hover:bg-gray-700
                                                        dark:hover:text-white
                                                        px-3 w-2/5"
                                                    placeholder="Titel"
                                                    onChange={(e) => setTitle(e.target.value)}
                                                />
                                                <input
                                                    type="text"
                                                    value={description}
                                                    name="description"
                                                    id="description"
                                                    className="
                                                        rounded-md
                                                        mx-3
                                                        py-2
                                                        text-sm
                                                        font-medium
                                                        bg-gray-100
                                                        text-black
                                                        hover:bg-gray-200
                                                        hover:text-black
                                                        dark:bg-gray-900
                                                        dark:text-gray-300
                                                        dark:hover:bg-gray-700
                                                        dark:hover:text-white
                                                        px-3
                                                        w-2/5"
                                                    placeholder="Beschrijving"
                                                    onChange={(e) => setDescription(e.target.value)}
                                                />
                                            </div>
                                            <button
                                                className="
                                                    rounded-md
                                                    mx-3
                                                    py-1
                                                    text-sm
                                                    font-bold
                                                    text-white
                                                    hover:bg-green-600
                                                    dark:hover:text-white
                                                    px-3
                                                    w-fit
                                                    bg-green-500
                                                    border-2
                                                    border-green-500
                                                    hover:border-green-600"
                                                onClick={handleSave}
                                                type="button"
                                            >
                                            Opslaan
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div className="hidden lg:ml-6 lg:block">
                                        <div
                                            style={{cursor: "grab"}}
                                            className="
                                                dndnode
                                                input
                                                rounded-md
                                                mx-3
                                                py-2
                                                text-sm
                                                font-bold
                                                bg-slate-200
                                                text-black
                                                hover:bg-gray-300
                                                hover:text-black
                                                dark:bg-slate-200
                                                dark:text-black
                                                dark:hover:bg-gray-300
                                                px-3
                                                w-fit
                                                border-dotted
                                                border-black
                                                border-2"
                                            onDragStart={(event) => onDragStart(event, 'custom')} draggable
                                        >
                                            Node Aanmaken
                                        </div>
                                    </div>
                                </div>
                            </div>
                            {koppelButton()}
                        </div>
                    </div>
                    {/* Mobile dropdown menu */}
                    <Disclosure.Panel className="lg:hidden">
                        <div className="flex flex-row">
                            <div className="space-y-1 px-2 pb-3 pt-2">
                                <input
                                    type="text"
                                    value={title}
                                    name="title"
                                    id="title"
                                    className="
                                        rounded-md
                                        mx-3
                                        py-2
                                        text-sm
                                        font-medium
                                        bg-gray-100
                                        text-black
                                        hover:bg-gray-200
                                        hover:text-black
                                        dark:bg-gray-900
                                        dark:text-gray-300
                                        dark:hover:bg-gray-700
                                        dark:hover:text-white
                                        px-3"
                                    placeholder="Titel"
                                    onChange={(e) => setDescription(e.target.value)}
                                />
                                <input
                                    type="text"
                                    value={description}
                                    name="description"
                                    id="description"
                                    className="
                                        rounded-md
                                        mx-3
                                        py-2
                                        text-sm
                                        font-medium
                                        bg-gray-100
                                        text-black
                                        hover:bg-gray-200
                                        hover:text-black
                                        dark:bg-gray-900
                                        dark:text-gray-300
                                        dark:hover:bg-gray-700
                                        dark:hover:text-white
                                        px-3"
                                    placeholder="Beschrijving"
                                    onChange={(e) => setDescription(e.target.value)}
                                />
                                <button
                                    className="
                                        rounded-md
                                        mx-3
                                        py-2
                                        text-sm
                                        font-medium
                                        bg-gray-100
                                        text-black
                                        hover:bg-gray-200
                                        hover:text-black
                                        dark:bg-gray-900
                                        dark:text-gray-300
                                        dark:hover:bg-gray-700
                                        dark:hover:text-white
                                        px-3
                                        w-fit
                                        border-solid
                                        border-green-500
                                        border-2"
                                    onClick={handleSave}
                                    type="button"
                                >
                                    Opslaan
                                </button>
                            </div>
                            <div>
                            <div
                                className="
                                    dndnode
                                    input
                                    rounded-md
                                    mx-3
                                    py-2
                                    text-sm
                                    font-bold
                                    bg-gray-100
                                    text-black
                                    hover:bg-gray-200
                                    hover:text-black
                                    dark:bg-gray-900
                                    dark:text-gray-300
                                    dark:hover:bg-gray-700
                                    dark:hover:text-white
                                    px-3
                                    w-fit
                                    border-dotted
                                    border-black
                                    border-2
                                    dark:border-white"
                                style={{cursor: "grab"}}
                                onDragStart={(event) => onDragStart(event, 'custom')}
                                draggable
                            >
                                Node Aanmaken
                            </div>
                                <div className="flex items-center justify-center pb-1 pt-1 ">
                                    <button
                                        className="
                                            rounded-md
                                            mx-3
                                            py-2
                                            text-sm
                                            font-medium
                                            bg-gray-100
                                            text-black
                                            hover:bg-gray-200
                                            hover:text-black
                                            dark:bg-gray-900
                                            dark:text-gray-300
                                            dark:hover:bg-gray-700
                                            dark:hover:text-white
                                            px-3
                                            w-fit"
                                        type="button"
                                    >
                                        Koppelen
                                    </button>
                                </div>
                            </div>
                        </div>
                    </Disclosure.Panel>
                </>
            )}
        </Disclosure>
    );
}

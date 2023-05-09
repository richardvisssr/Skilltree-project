import React, { useState, useEffect } from 'react';
import { Disclosure} from '@headlessui/react' // Menu, Transition
import { Bars3Icon, XMarkIcon } from '@heroicons/react/24/outline' // BellIcon
import { useDispatch } from 'react-redux';
import { fetchCreateSkillTreeActionAsync } from '../actions/SkilltreeAction';

export default function TopbarComponent({ currentSkilltree, newSkilltree }) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const dispatch = useDispatch();

    const handleSave = () => {
        if (title === "") {
            return;
        }
        if (description === "") {
            return;
        }
        dispatch(fetchCreateSkillTreeActionAsync(title, description, 1));
    };

    useEffect(() => {
        if (newSkilltree) {
            setTitle("");
            setDescription("");
        } else {
            setTitle(currentSkilltree.title);
            setDescription(currentSkilltree.description);
        }
    }, [currentSkilltree, newSkilltree]);

    const buttonClass = `
        rounded-md mx-3 py-2 text-sm font-medium
        bg-gray-100 text-black hover:bg-gray-200 hover:text-black
        dark:bg-gray-900 dark:text-gray-300 dark:hover:bg-gray-700 dark:hover:text-white 
    `
    const buttonTitle = buttonClass + "px-3 w-2/5"
    const buttonDescription = buttonClass + "px-3 w-2/5"
    const buttonNodeAanmaken = buttonClass + "px-3 w-fit"
    const buttonKoppelen = buttonClass + "px-3 w-fit"
    const buttonOpslaan = buttonClass + "px-3 w-fit border-solid border-green-600 border-2"
    const buttonTitleMobile = buttonClass + "px-3"
    const buttonDescriptionMobile = buttonClass + "px-3"
    const buttonKoppelenMobile = buttonClass + "px-3 w-fit"
    const buttonNodeAanmakenMobile = buttonClass + "px-3 w-fit"

    return (
        <Disclosure as="nav" className="bg-gray-50 dark:bg-gray-800">
            {({ open }) => (
                <>
                    <div className="mr-auto px-2 sm:px-6 lg:px-8">
                        <div className="relative flex h-16 items-center justify-between">
                            <div className="inset-y-0 left-0 flex items-center lg:hidden">
                                {/* Mobile menu button */}
                                <Disclosure.Button className="inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white">
                                    <span className="sr-only">Open menu</span>
                                    {open ? (
                                        <XMarkIcon className="block h-6" aria-hidden="true" />
                                    ) : (
                                        <Bars3Icon className="block h-6" aria-hidden="true" />
                                    )}
                                </Disclosure.Button>
                            </div>
                            <div className="flex flex-1 items-center justify-center">
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
                                                    className={buttonTitle}
                                                    placeholder="Titel"
                                                    onChange={(e) => setTitle(e.target.value)}
                                                />
                                                <input
                                                    type="text"
                                                    value={description}
                                                    name="description"
                                                    id="description"
                                                    className={buttonDescription}
                                                    placeholder="Beschrijving"
                                                    onChange={(e) => setDescription(e.target.value)}
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="hidden lg:ml-6 lg:block">
                                    <div className="flex items-center justify-center">
                                        <button className={buttonNodeAanmaken} type="button">
                                            Node Aanmaken
                                        </button>
                                    </div>
                                </div>
                                <div className="hidden lg:ml-6 lg:block">
                                    <div className="flex items-center justify-center">
                                        <button className={buttonKoppelen} type="button">
                                            Koppelen
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div className="hidden lg:ml-6 lg:block">
                                <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                                    <button
                                        className={buttonOpslaan}
                                        onClick={handleSave}
                                        type="button"
                                    >
                                        Opslaan
                                    </button>
                                </div>
                            </div>
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
                                    className={buttonTitleMobile}
                                    placeholder="Titel"
                                    onChange={(e) => setDescription(e.target.value)}
                                />
                                <input
                                    type="text"
                                    value={description}
                                    name="description"
                                    id="description"
                                    className={buttonDescriptionMobile}
                                    placeholder="Beschrijving"
                                    onChange={(e) => setDescription(e.target.value)}
                                />
                                <button
                                    className={buttonOpslaan}
                                    onClick={handleSave}
                                    type="button"
                                >
                                    Opslaan
                                </button>
                            </div>
                            <div>
                                <div className="flex items-center justify-center pb-1 pt-1 ">
                                    <button
                                        className={buttonNodeAanmakenMobile}
                                        type="button"
                                    >
                                        Node Aanmaken
                                    </button>
                                </div>
                                <div className="flex items-center justify-center pb-1 pt-1 ">
                                    <button
                                        className={buttonKoppelenMobile}
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

import React, { useEffect } from "react";
import { useDispatch ,useSelector } from "react-redux";
import "../styles/styles.css";
import {fetchAllUsersActionAsync, fetchCurrentUsersActionAsync} from "../actions/userAction";

function LoginComponent() {

    const dispatch = useDispatch();

    const users = useSelector((state) => state.user.users);

    useEffect(() => {
        dispatch(fetchAllUsersActionAsync());
    }, []);

    const handleSave = () => {
        // dispatch(fetchCurrentUsersActionAsync(userEmailList));
        // showRoleView();
      };
    
    const accountList = () => {
        const userEmailList = users.map((user) => (
            <option key={user.email} value={user.email}>{user.email}</option>
        ));

        return (
            <select
                name="Kies een account"
                className="block w-full px-4 py-2 placeholder-gray-400 border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            >
                <option value="" selected disabled>Selecteer een gebruiker</option>
                {userEmailList}
            </select>
        );
    };


    return (
        <div className="flex min-h-screen items-center justify-center bg-gray-100">
            <div className="w-5/6 max-w-md">
                <div className="bg-white rounded-lg shadow-xl p-6">
                    <h2 className="text-2xl font-semibold mb-4">Login</h2>
                    {accountList()}
                    <div className="mt-6 flex items-center justify-center">
                        <button
                            type="submit"
                            className="w-full bg-indigo-600 text-white font-semibold py-2 px-4 rounded hover:bg-indigo-500 focus:outline-none focus:ring-2 focus:ring-indigo-500"
                            onClick={handleSave}
                        >
                            Inloggen
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginComponent;
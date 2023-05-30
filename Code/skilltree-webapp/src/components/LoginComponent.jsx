import React, { useEffect, useState } from "react";
import { useDispatch ,useSelector } from "react-redux";
import "../styles/styles.css";
import {fetchAllUsersActionAsync, currentUserSelectedAction} from "../actions/userAction";
import { Link } from 'react-router-dom';

function LoginComponent() {

    const dispatch = useDispatch();

    const [currentUser, setCurrentUser] = useState('');

    const users = useSelector((state) => state.user.users);

    useEffect(() => {
        dispatch(fetchAllUsersActionAsync());
    }, []);

    const handleSave = () => {
        const selectedCurrentUser = users.find((user) => user.email === currentUser);
        if (selectedCurrentUser) {
          dispatch(currentUserSelectedAction(selectedCurrentUser));
        }
      };

      const handleAccountChange = (event) => {
        setCurrentUser(event.target.value);
      };
      
      const accountList = () => {
        if (!users || users.length === 0) {
          return null;
        }
      
        const userEmailList = users.map((user) => (
          <option key={user.email} value={user.email}>
            {user.email}
          </option>
        ));
      
        return (
          <select
            name="Kies een account"
            className="block w-full px-4 py-2 placeholder-gray-400 border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            onChange={handleAccountChange}
          >
            <option value={currentUser} defaultValue>
              Selecteer een gebruiker
            </option>
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
                    <Link to="/home">
                        <button 
                            type="submit"
                            className="w-full bg-indigo-600 text-white font-semibold py-2 px-4 rounded hover:bg-indigo-500 focus:outline-none focus:ring-2 focus:ring-indigo-500"
                            onClick={handleSave}
                        >
                            Inloggen
                        </button>
                    </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginComponent;
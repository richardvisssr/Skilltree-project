import {AiOutlineLogout} from "react-icons/ai";
import React from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

function LogoutComponent() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  function logout() {
    dispatch({type: "skilltree/reset"});
    dispatch({type: "node/reset"});
    dispatch({type: "user/reset"});
    dispatch({type: "students/reset"});
    sessionStorage.clear();
    navigate("/login");
  }

  return(
    <button
        type="button"
        className="
            flex z-50
            items-center
            w-full
            p-2
            text-gray-900
            transition
            duration-75
            rounded-lg
            hover:bg-gray-100
            dark:hover:bg-gray-700
            dark:text-white group"
        onClick={() => logout()}
    >
      <div className="flex" >
        <AiOutlineLogout size={20} />
        <span className="ml-6">Log uit</span>
      </div>
    </button>
  );
}

export default LogoutComponent;

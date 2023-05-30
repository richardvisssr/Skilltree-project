import { useSelector } from "react-redux";
import DocentView from "./users/docentView";
import StudentView from "./users/studentView";
import { Navigate } from 'react-router-dom';

export default function HomeScreen() {
  const user = useSelector((state) => state.user.currentUser);

  let view;
  if (!user === null) {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const userRole = useSelector((state) => state.user.currentUser.roleId);
    switch (userRole) {
      case 1:
        view = <DocentView />
        break;
      case 2:
        view = <StudentView />
        break;
      default:
        view = 'Onbekende userID'
        // console.log(userRole)
      break;
    }
    return (
      <div>
        {view}
      </div>
    );
  } else {
    return (
       <Navigate to="/login" />
    );
  }


}

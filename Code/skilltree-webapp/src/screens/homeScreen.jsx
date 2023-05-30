import { useSelector } from "react-redux";
import DocentView from "./users/docentView";
import StudentView from "./users/studentView";
import { Navigate } from 'react-router-dom';

export default function HomeScreen() {
  const user = useSelector((state) => state.user.currentUser);
  const userRole = useSelector((state) => state.user.currentUser.roleId);
  let view;

  if (user !== null) {
    console.log('user');
    // console.log(user);
    // console.log(userRole);
    switch (userRole) {
      case 1:
        view = <DocentView />;
        break;
      case 2:
        view = <StudentView />;
        break;
      default:
        view = 'Onbekende userID';
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

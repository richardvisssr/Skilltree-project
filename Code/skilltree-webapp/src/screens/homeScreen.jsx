import { useSelector } from "react-redux";
import DocentView from "./users/docentView";
import StudentView from "./users/studentView";

export default function HomeScreen() {
  const userRole = useSelector((state) => state.user.roleId);

  let view;
  switch (userRole) {
    default:
      view = 'Onbekende userID'
      break;
    case 1:
      view = <DocentView />
      break;
    case 2:
      view = <StudentView />
      break;
  }
  
  return (
    <div>
      {view}
    </div>
  );
}

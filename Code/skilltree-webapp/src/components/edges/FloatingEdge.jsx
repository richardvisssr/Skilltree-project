import { useCallback, useState } from 'react';
import {useStore, getStraightPath, BaseEdge, EdgeLabelRenderer} from 'reactflow';

import { getEdgeParams } from './EdgesUtils.js';
import {useDispatch} from "react-redux";

import { fetchDeleteEdgeActionAsync } from "../../actions/EdgeAction";
function FloatingEdge({ id, source, target, markerEnd, style }) {
  const dispatch = useDispatch();
  const sourceNode = useStore(useCallback((store) => store.nodeInternals.get(source), [source]));
  const targetNode = useStore(useCallback((store) => store.nodeInternals.get(target), [target]));
  const [showDeleteEdge, setShowDeleteEdge] = useState(false);

  if (!sourceNode || !targetNode) {
    return null;
  }

  const { sx, sy, tx, ty } = getEdgeParams(sourceNode, targetNode);

  const [edgePath, labelX, labelY] = getStraightPath({
    sourceX: sx,
    sourceY: sy,
    targetX: tx,
    targetY: ty,
  });

  const deleteEdge = () => {
    dispatch(fetchDeleteEdgeActionAsync(id));
    setShowDeleteEdge(!showDeleteEdge);
  }

  const cartComponent = () => {
    if(showDeleteEdge) {
      return (
          <div className="absolute w-60 h-32 position left-7 top-0 bottom-0 m-auto border-2 border-black bg-white">
            <div className="flex flex-col justify-around items-center h-full">
                <p className="text-center">Weet u zeker dat u deze koppeling wil verwijderen?</p>
                <div className="flex justify-center items-center">
                  <button onClick={() => setShowDeleteEdge(!showDeleteEdge)} className="bg-slate-300 text-white rounded-md px-2 py-1 m-2">Annuleren</button>
                  <button onClick={deleteEdge} className="bg-blue-700 text-white rounded-md px-2 py-1 m-2">Verwijderen</button></div>
            </div>
          </div>
      )
    }
  }

  return (
      <>
        <EdgeLabelRenderer>
          <div
              style={{
                position: 'absolute',
                transform: `translate(-50%, -50%) translate(${labelX}px,${labelY}px)`,
                pointerEvents: 'all',
                zIndex: showDeleteEdge ? 2000 : 0,
              }}
          >
            <button onClick={() => setShowDeleteEdge(!showDeleteEdge)} className="text-red-700 bg-black rounded-full w-5 h-5 leading-none font-bold">
              X
            </button>
            {cartComponent()}
          </div>
          </EdgeLabelRenderer>
        <BaseEdge path={edgePath} markerEnd={markerEnd} style={style} id={id} />
        </>
  );
}

export default FloatingEdge;
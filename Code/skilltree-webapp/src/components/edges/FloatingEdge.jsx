import { useCallback, useState } from 'react';
import {useStore, getStraightPath, BaseEdge, EdgeLabelRenderer} from 'reactflow';

import { getEdgeParams } from './EdgesUtils.js';
function FloatingEdge({ id, source, target, markerEnd, style }) {
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
    console.log("delete edge");
  }

  const cartComponent = () => {
    if(showDeleteEdge) {
      return (
          <div className="fixed w-60 h-32 position left-7 top-0 bottom-0 m-auto border-2 border-black bg-white">
            <div className="flex flex-col justify-center items-center">
                <p className="text-center">Weet u zeker dat u deze koppeling wil verwijderen?</p>
                <div className="flex justify-center items-center">
                    <button onClick={deleteEdge} className="bg-black text-white rounded-md px-2 py-1 m-2">Ja</button>
                    <button onClick={() => setShowDeleteEdge(!showDeleteEdge)} className="bg-black text-white rounded-md px-2 py-1 m-2">Nee</button>
                </div>
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
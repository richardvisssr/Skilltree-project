import {useCallback, useEffect, useState} from 'react';
import {useStore, getStraightPath, BaseEdge, EdgeLabelRenderer} from 'reactflow';

import { getEdgeParams } from '../../EdgesUtils.js';
import {useDispatch, useSelector} from "react-redux";

import { fetchDeleteEdgeActionAsync } from "../../../../actions/users/docent/EdgeAction.js";
function FloatingEdge({ id, source, target, markerEnd, style, data}) {
  const dispatch = useDispatch();
  const sourceNode = useStore(useCallback((store) => store.nodeInternals.get(source), [source]));
  const targetNode = useStore(useCallback((store) => store.nodeInternals.get(target), [target]));

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
    data.setDeletedEdge(true);
    data.deleteEdge(id);
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
            <button onClick={() => deleteEdge()} className="text-red-700 bg-black rounded-full w-5 h-5 leading-none font-bold">
              X
            </button>
          </div>
          </EdgeLabelRenderer>
        <BaseEdge path={edgePath} markerEnd={markerEnd} style={style} id={id} />
        </>
  );
}

export default FloatingEdge;

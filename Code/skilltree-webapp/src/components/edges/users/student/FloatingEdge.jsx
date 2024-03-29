import {useCallback} from 'react';
import {useStore, getStraightPath, BaseEdge, EdgeLabelRenderer} from 'reactflow';

import { getEdgeParams } from '../../EdgesUtils.js';

function FloatingEdge({ id, source, target, markerEnd, style, data}) {
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
          </div>
          </EdgeLabelRenderer>
        <BaseEdge path={edgePath} markerEnd={markerEnd} style={style} id={id} />
        </>
  );
}

export default FloatingEdge;

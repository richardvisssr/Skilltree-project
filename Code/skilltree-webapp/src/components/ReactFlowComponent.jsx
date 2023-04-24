import {ReactFlow} from "reactflow";

export default function ReactFlowComponent() {
    const nodes = [
        {
            id: '1',
            data: { label: 'Input Node' },
            position: { x: 250, y: 5 },
        }]
  return (
    <div className="w-full h-full">
        <ReactFlow
        nodes={nodes}
        />
      </div>
  )

}
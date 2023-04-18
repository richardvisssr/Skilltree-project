import React from 'react';
import '../styles/styles.css';

export default function testComponent() {
  return (
    <div>
    <p className="text-slate-700">The quick brown fox...</p>
<p className="text-sky-400/75">The quick brown fox...</p>
<p className="text-sky-400/50">The quick brown fox...</p>
<p className="text-sky-400/25">The quick brown fox...</p>
<p className="text-sky-400/0">The quick brown fox...</p>
</div>
  )
}
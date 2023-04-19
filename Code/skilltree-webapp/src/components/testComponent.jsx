import React from 'react';

function TopBar({ onSave }) {
  return (
      <div>
        <input type="text" placeholder="Naam" />
        <input type="text" placeholder="Beschrijving" />
        <button onClick={onSave}>Opslaan</button>
      </div>
  );
}

export default TopBar;

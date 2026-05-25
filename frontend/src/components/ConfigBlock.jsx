
import React from "react";

function ConfigBlock({ category, subtypes, onSelect }) {
  return (
    <div className="config-block">
      <h3>{category}</h3>
      {subtypes.map((sub, i) => (
        <div key={i} className="subtype-block">
          <label>{sub.name}</label>
          <select
            onChange={(e) => {
              const selectedOption = sub.options.find(
                (opt) => opt.name === e.target.value
              );
              onSelect(category, sub.name, selectedOption);
            }}
          >
            <option value="">Select {sub.name}</option>
            {sub.options.map((opt) => (
              <option key={opt.name} value={opt.name}>
                {opt.name} - ₹{opt.price}
              </option>
            ))}
          </select>
        </div>
      ))}
    </div>
  );
}

export default ConfigBlock;

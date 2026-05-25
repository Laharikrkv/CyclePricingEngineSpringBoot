import { useState } from "react";
import componentsData from "./data/componentsData";
import ConfigBlock from "./components/ConfigBlock";
import "./styles/config.css";

function App() {
  const [selectedParts, setSelectedParts] = useState({});
  const [pricingDate, setPricingDate] = useState("");

  const handleSelect = (category, subtype, option) => {
    setSelectedParts((prev) => ({
      ...prev,
      [category]: {
        ...prev[category],
        [subtype]: option,
      },
    }));
  };

  // ✅ Calculate component-level totals
  const componentTotals = Object.entries(selectedParts).map(([category, subs]) => {
    const subtotal = Object.values(subs).reduce(
      (sum, opt) => sum + (opt?.price || 0),
      0
    );
    return { category, subtotal };
  });

  // ✅ Grand total
  const grandTotal = componentTotals.reduce((sum, comp) => sum + comp.subtotal, 0);
   const allSelected = Object.entries(componentsData).every(([category, { subtypes }]) =>
    subtypes.every((sub) => selectedParts[category]?.[sub.name])
  );
  return (
    <div>
      <h1>Cycle Price Configurator</h1>

      {/* Date Picker */}
      <div className="date-picker">
        <label>Select Pricing Date: </label>
        <input
          type="date"
          value={pricingDate}
          onChange={(e) => setPricingDate(e.target.value)}
        />
      </div>

      {/* Config Blocks */}
      {Object.entries(componentsData).map(([category, { subtypes }]) => (
        <ConfigBlock
          key={category}
          category={category}
          subtypes={subtypes}
          onSelect={handleSelect}
        />
      ))}

<div className="summary">
  <h2>Total Price Summary</h2>
  <p>Pricing Date: {pricingDate || "Not selected"}</p>

  {!allSelected ? (
    <p style={{ color: "red" }}>⚠️ Please select all options before viewing totals.</p>
  ) : (
    <>
      <ul>
        {componentTotals.map(({ category, subtotal }) => (
          <li key={category}>
            {category}: ₹{subtotal}
          </li>
        ))}
      </ul>
      <h3>Grand Total: ₹{grandTotal}</h3>
    </>
  )}
</div>


    </div>
);
}
export default App;

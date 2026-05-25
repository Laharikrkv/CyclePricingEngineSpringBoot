const componentsData = {
  Frame: {
    subtypes: [
      {
        name: "Frame",
        options: [
          { name: "Steel Frame", price: 4000 },
          { name: "Aluminium Frame", price: 7500 },
        ],
      },
    ],
  },

  "Handle Bar & Brakes": {
    subtypes: [
      {
        name: "Handlebar",
        options: [
          { name: "Standard Handlebar", price: 800 },
          { name: "Drop Handlebar", price: 1800 },
          { name: "Flat Handlebar", price: 1200 },
        ],
      },
      {
        name: "Brakes",
        options: [
          { name: "V-Brakes", price: 1200 },
          { name: "Disc Brakes", price: 3000 },
        ],
      },
    ],
  },

  Seating: {
    subtypes: [
      {
        name: "Saddle",
        options: [
          { name: "Basic Saddle", price: 600 },
          { name: "Ergonomic Saddle", price: 1500 },
        ],
      },
      {
        name: "Seat Post",
        options: [
          { name: "Standard Seat Post", price: 500 },
          { name: "Adjustable Seat Post", price: 1200 },
        ],
      },
    ],
  },

  Wheels: {
    subtypes: [
      {
        name: "Rim",
        options: [
          { name: "Alloy Rim", price: 2000 },
          { name: "Steel Rim", price: 1500 },
        ],
      },
      {
        name: "Tyre",
        options: [
          { name: "Road Tyre", price: 1200 },
          { name: "Mountain Tyre", price: 2000 },
        ],
      },
      {
        name: "Tube",
        options: [
          { name: "Standard Tube", price: 300 },
          { name: "Puncture Resistant Tube", price: 700 },
        ],
      },
      {
        name: "Spokes",
        options: [
          { name: "Steel Spokes", price: 400 },
          { name: "Aluminium Spokes", price: 900 },
        ],
      },
    ],
  },

  "Chain Assembly": {
    subtypes: [
      {
        name: "Chain",
        options: [
          { name: "Single Speed Chain", price: 700 },
          { name: "Multi-Speed Chain", price: 1500 },
        ],
      },
      {
        name: "Gear Assembly",
        options: [
          { name: "Single Gear", price: 1000 },
          { name: "Double Gear", price: 4000 },
        ],
      },
      {
        name: "Pedals",
        options: [
          { name: "Standard Pedals", price: 500 },
          { name: "Grip Pedals", price: 1000 },
        ],
      },
      {
        name: "Crank Set",
        options: [
          { name: "Single Crank Set", price: 1500 },
          { name: "Multi-Speed Crank Set", price: 3500 },
        ],
      },
    ],
  },
};
export default componentsData;
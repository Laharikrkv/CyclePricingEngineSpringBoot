

# PART 1 - Problem Breakdown

## The Problem
User: A salesperson at a cycle showroom.

What she needs:
- Quick, accurate price calculations when assembling custom cycles for customers.
- Clear breakdown showing individual component costs and total price.
- Time-based pricing to quote reliable results.
  
What would frustate her: 
- If output misses any specific component price leading to non-reliable total price.
- Undefined date boundaries lead to unexpected increase/decrease in price.
- Sudden app crashes or latency issues or unsaved changes while editing choices.

## Edge Cases due to time-sensitivity
- When future time (price change is unknown) or past time (at which price comparison is irrelavent) is given.
- Clear date boundaries for accurate calculation - invoice and data should match.
- When pricing date overlaps for a part, it would be ambituous to pick one.

## My Plan
We are sending list of parts as input, and a date.
- I create an entity for part, which has a list of prices and the high-level component it belongs to.
- I create another entity for price history, it stores price of any part that are valid in between two dates.
- I create entity for cycle configuration, it stores component-wise pricing.

How I handle price changes over time.
- From input, I get a particular time. 
- Initially, at input, I check the validity of the date.
- Price history entity has price of all parts, for every change of price over time.
- Later, I filter out the price by comparing the duration between valid dates.

How I structure the output. (MY INITIAL PLAN)
- I initialize cycle configuration object, it has cycle components as fields that stores price of each and also total price.
- I calculate price for each component, by iterating the input list and fetching price as per date.
- After loop, I calculate the sum and return the configuration instance - component-wise pricing and total price.

-------------------------------------------------------------------------------------------------------------------------------------------------

##Extendable Idea 
- I can store multpiple cycle configurations per user, so that they can compare.
- Logic - From Input, we send userId, configuration table records the row with userId.
- So Salesperson can show clearly different options to customers.

-----------------------------------------------------------------------------------------------------------------------------------------
# Part 2: Conceptual Solution and Code

## 2a. Data Model

### Core Entities

The core entities in this solution are:

- `Part` — represents a part of the cycle.
- `Price` — represents the price history of a part over a specific period.

---

## Entity Structure

### Part

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Unique identifier of the part |
| `name` | `String` | Name of the part |
| `component` | `String` | Component/category the part belongs to |
| `priceHistory` | `List<Price>` | List of historical prices for the part |

### Price

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Unique identifier of the price record |
| `partId` | `Long` | Reference to the related part |
| `validFrom` | `Date` | Start date from which the price is valid |
| `validTill` | `Date \| null` | End date until which the price is valid |
| `amount` | `BigDecimal` | Price amount of the part |

---

## Relationships Between Entities

- One `Part` can have many `Price` records over time.
- Each `Price` record belongs to one `Part`.

---

## Design Decision

My approach is to check each part listed in the input.

For every input part:

1. Fetch the part by name.
2. Fetch its price history.
3. Check which price is valid for the given input date.
4. Based on the component of the part, add the price to the respective field in the cycle configuration object.
5. After processing all parts, calculate the total price and return the final response.

I chose this approach because it is simple and easy to understand. Iterating over the input list limits unnecessary checks, and fetching by part name keeps the flow clear.

In implementation, I can also write a query that directly returns the correct price for a part on a given date. After fetching the price, I can use a `switch` statement on the part component to add the price component-wise and calculate the final total.

This approach ensures that price fetching and component-wise calculation happen in a single flow.

------------------------------------------------------------------------------------------------------------------------------------------
# Cycle Components with Reasonable Prices

| Category | Subtype | Price (₹) |
|---|---|---|
| Frame | Steel Frame | 4,000 |
| Frame | Aluminium Frame | 7,500 |
| Handle Bar & Brakes | Standard Handlebar | 800 |
| Handle Bar & Brakes | Drop Handlebar | 1,800 |
| Handle Bar & Brakes | Flat Handlebar | 1,200 |
| Handle Bar & Brakes | V-Brakes | 1,200 |
| Handle Bar & Brakes | Disc Brakes | 3,000 |
| Seating | Basic Saddle | 600 |
| Seating | Ergonomic Saddle | 1,500 |
| Seat Post | Standard Seat Post | 500 |
| Seat Post | Adjustable Seat Post | 1,200 |
| Rim | Alloy Rim | 2,000 |
| Rim | Steel Rim | 1,500 |
| Tyre | Road Tyre | 1,200 |
| Tyre | Mountain Tyre | 2,000 |
| Tube | Standard Tube | 300 |
| Tube | Puncture Resistant Tube | 700 |
| Spokes | Steel Spokes | 400 |
| Spokes | Aluminium Spokes | 900 |
| Chain | Single Speed Chain | 700 |
| Chain | Multi-Speed Chain | 1,500 |
| Gear Assembly | Single Gear | 1,000 |
| Gear Assembly | Multi Gear | 4,000 |
| Pedals | Standard Pedals | 500 |
| Pedals | Grip Pedals | 1,000 |
| Crank Set | Single Crank Set | 1,500 |
| Crank Set | Multi-Speed Crank Set | 3,500 |


--------------------------------------------------------------------------------------------------------------------------

# Assumptions
- Salesperson have pre-defined text for parts, she just pastes, so there is no spelling mistakes.
- They have catalog for high-level components and parts.
- Company has only 1 year-warranty schemes.

## ADDED PLAN
# Validation
I have validation logic that check for whether parts required for cycle configuration is given.
I explained the code flow with the help of comments.

# Error Handling
- If any required part is missed, duplicate entry, we will get error message with details.

# Constraints with Date
-  I have strictly constrained to record with data only within a year time. If date is given out of range, we get error message.


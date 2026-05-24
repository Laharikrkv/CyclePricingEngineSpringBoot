#-------------------------------------INITIAL PLAN---------------------------------------------------

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
#Part 2 Conceptual Solution and Code
# 2a Data Model

My core entities:
- Part -> represents a part of the cycle.
- Price -> represents price of a part over every change for a period.


Part
	- id - Long
	- name - String
	- component - String
	- price_history -  List<Price>

Price
	- id - Long
	- part_id - Long
	- valid-from - date
	- valid-till - date | null
	- amount - BigDecimal


Relations between entities
	- Part has many prices over the time.
	- Each price record belongs to one part.


Design Decision
	- My approach is that I will check for each part listed in input.
	- Since it is easier to fetch the part by name and by performing 
		lazy loading, I will fetch list of price history.
	- I check the input date in the list, and I will fetch the price.
	- Then, by check the component of the part, I will add prices of 
		all parts to the cycle configuration instance, it represent 
		the output format.
* I choose this approach because it is easier to iterate over input list
as it limits the number of checks and easier to fetch by part name. i will write 
a query that gives correct price at a date. Later I switch over part component,
add the price component-wise and calculate the sum. This approach ensures one go
for both price fetching and component-wise price calculation.*

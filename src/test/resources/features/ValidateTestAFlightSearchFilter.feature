Feature: User searches cheapest flight in next 15 day
Scenario: User searches cheapest flight in next 15 day
  Given the user is on the MakeMyTrip flight search page
  When the user selects Pune as the departure city
  And selects Chennai as the destination city
  Then cheapest flight in next 15 days is picked

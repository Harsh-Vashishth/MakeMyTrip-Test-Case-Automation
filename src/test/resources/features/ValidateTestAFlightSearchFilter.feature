Feature: Flight filtering by location

Scenario: User filters flights by departure and destination location
  Given the user is on the MakeMyTrip flight search page
  When the user selects Pune as the departure city
  And selects Chennai as the destination city
  Then cheapest flight in next 15 days is picked

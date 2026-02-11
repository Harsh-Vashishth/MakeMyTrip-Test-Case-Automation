Feature: Flight filtering by location

Scenario: User filters flights by departure and destination location
  Given the user is on the MakeMyTrip flight search page
  When the user selects "Pune" as the departure city
  And selects "Chennai" as the destination city
  And date is picked
  And clicks on the search button
  Then all listed flights should match the selected locations

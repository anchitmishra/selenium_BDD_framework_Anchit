@makeMyTripTesting

Feature: Exploring makeMyTrip application

    Background: Launch makeMyTrip website
        Given user launches MMT website "https://www.makemytrip.com/"

    @viewPackages
    Scenario Outline: Check for <typeOfDestination> packages
        Given the user clicks on "<tabName>" with title "Where2Go by MakeMyTrip - Explore Destinations | Plan Your Trip | Read Blogs" tab on homepage
        And the user searches for "<typeOfDestination>" with title "The Best Beach Places for an Exotic Holiday| TripIdeas by MakeMyTrip" on destination selection page
        And the user selects "<location>" with title "Bali - Indonesia |Tourist Places, Things to Do & Best Time to Visit |MakeMyTrip" on location selection page
        And the user clicks on packages
        Then the user should be able to see all available packages under "Bali Packages"
        Examples:
            | tabName  | typeOfDestination  | location |
            | Where2Go | Beach Destinations | Bali     |


# BookMyShow


## v1 Design Issues
- Booking is having Shows & Shows has CinemaHall which has the seat details. 
How does the system find out the booked & leftover seats?
- Since Shows has the CinemaHall, and CinemaHall has SeatDetails, 
to book shows for each seat of CinemaHall, that much entry will be created for Shows. 
Ideally Shows should be single for each CinemaHall. But there has to be mapping of that show to seat mapping.
- CinemaHall has SeatDetails, now to store the details of all seats, 
it has to have the list of all seats which is incorrect. Ideally it has to be the other way around.
- Models shouldn't have data which it isn't supposed to have. Follow Single-Responsibility-Principle.


## Solution


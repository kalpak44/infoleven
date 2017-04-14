Implement a simple command line Java application for booking bus seats. For simplicity suppose there is only one bus, initially all seats (lets say 50) are free. Users can book one seat at time through the application API, the application itself should try to take the first available seat from 1 to 50, if any left. Make sure your application is thread-safe (i.e. many users can book tickets at the same time, but cases such booking the same seat from more than one user must be avoided) using the conventional locking mechanisms available in Java.

If you are familiar with the Spring framework, use any of its concepts that you think would be appropriate.


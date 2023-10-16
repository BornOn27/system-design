# Singleton
This pattern helps in restricting the creation of object of a class more than once.
In other words Singleton Pattern enables to maintain only 1 object of any class.


# Pitfalls
Singleton scope changes with multiple-pods on a server, multiple-servers under an environment.

With Double-Checked Locking where we check whether the instance created 2 times, this also ensure 
Singleton in single server scenario. In multi-server real-world scenario this is not solution.


# CASE : Multiple Server  
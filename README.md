# CountriesToCities-Clean-Architecture-Android
A clean architecture example. Using Kotlin Flow, Retrofit and Dagger Hilt, etc.

# Intro

Architecture means the overall design of the project. It’s the organization of the code into classes or files or components or modules. And it’s how all these groups of code relate to each other.
The architecture defines where the application performs its core functionality and how that functionality interacts with things like the database and the user interface.
Clean architecture refers to organizing the project so that it’s easy to understand and easy to change as the project grows. This doesn’t happen by chance. It takes intentional planning.

# Project Description
**In this project i have get the data from api of Countries, Zone, Region and Area. i have filtered Area according to the region selected. i have also implemented SearchView as well on the AreaFragment just for making search easy.**

**MVVM Best Pratice:**
- Avoid references to Views in ViewModels.
- Instead of pushing data to the UI, let the UI observe changes to it.
- Distribute responsibilities, add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
- Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.

## 🌟 Features
- **ViewModel**
- **DataBinding**
- **Navigation**
- **RoomDB**
- **RetroFit2.0**
- **Material Component**
- **OkHttp**
- **Dagger-Hilt**
- **Flow**
- **Coroutines**
- **Kotlin**
- **Clean Architecture (MVVM)**

**What is Coroutines ?**
-------------------

 **Coroutines :**
Is light wight threads for asynchronous programming, Coroutines not only open the doors to
asynchronous programming, but also provide a wealth of other possibilities such as concurrency, actors, etc.

----------

**How does Coroutines concept work ?**
------------
 - Kotlin coroutine is a way of doing things asynchronously in a sequential manner. Creating a coroutine is a lot cheaper vs creating a thread.

**What is the Coroutines benefits?**
-----------------------------

 - Writing an asynchronous code is sequential manner.
 - Costing of create coroutines are much cheaper to crate threads.
 - Don't be over engineered to use observable pattern, when no need to use it.
 - parent coroutine can automatically manage the life cycle of its child coroutines for you.


## Main Picture
There are 3 layer in this app.  
| Presentation Layer      | Domain Layer          | Data Layer                         |
| ----------------------- | --------------------- | ---------------------------------- |
| your ui/view            | entity                | data source, dto                   |
| your viewmodel          | usecase               | repository implementation          |
| probably your extension | repository interface  | your library config(retrofit/room) |
| etc...                  | etc..                 | etc...                             |


## App Level Example

| Presentation Layer             | Something in Between  | Domain Layer                      | Data Layer                         | Outer data layer |
| ------------------------------ | --------------------- | --------------------------------- | ---------------------------------- | ---------------- |
| MainActivity,Fragments & ViewModel | UseCase |  Repository (interface) | LoginRepositoryImplementation | DataSource       |

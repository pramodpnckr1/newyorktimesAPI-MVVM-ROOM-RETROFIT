# Android MVVM Base Architecture for Enterprise Mobile Application using Architectural Components

# Highlights

1. MVVM Architectural pattern
2. Offline Support
3. Unit test demonstration using JUnit and Mockito
4. UI unit test demonstartion using Espresso
5. Gradle scripts for running sonarqube static code analysis, code coverage, etc.


The application has been built with **offline support**. It has been designed using **Android Architecture components** with **Room** for offline data caching. The application is built in such a way that whenvever there is a service call, the result will be stored in local database.

The whole application is built based on the MVVM architectural pattern.

# Application Architecture
![alt text](https://cdn-images-1.medium.com/max/1600/1*-yY0l4XD3kLcZz0rO1sfRA.png)

The main advatage of using MVVM, there is no two way dependency between ViewModel and Model unlike MVP. Here the view can observe the datachanges in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).

# Screenshots
<img src="/screenshots/main_screen.png" width="346" height="615" alt="Home"/> 
<img src="/screenshots/details_screen.png" width="346" height="615" alt="Home"/>
<img src="/screenshots/search_screen.png" width="346" height="615" alt="Home"/>

# TEST REPORT
<img src="/screenshots/test.png" width="346" height="615" alt="Home"/>

# Programming Practices Followed
a) Android Architectural Components <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
e) Room for data caching <br/>
f) JUnit and Mockito for Unit testing <br/>
d) Repository pattern <br/>
e) JSoup for HTML Parsing




# Thales Employee
 Android app, that shows a list of employees and the capability to search an employee with the ID. Project created with MVVM patter, S.O.L.I.D principles, and Dependency injection.

## Overview
Thales Employee project will be built using the principles of Clean Architecture - S.O.L.I.D as one single project (not modular) using the MVVM pattern. 
The general idea is to have the app divided like this

IMAGE HERE

## Architecture and Pattern Description

The project, as is using clean architecture will be divided in:

- Data (Repository) - Remote data source (Retrofit)
- Domain (Use cases)
- UI (View) - MVVM - ViewModels, States, Model Presentation, Mappers

### Advantages of Using Clean Architecture

- Our code will be even more easily testable than with plain MVVM.
- Our code will be further decoupled (the biggest advantage.)
- The package structure is even easier to navigate.
- The project is even easier to maintain.
- Our team can add new features even more quickly.

## Unit Test

Thales Employee project, has unit-test that covers the complete pattern and architecture of the project
- Data (Unit tests for Repository)
- Domain (Unit tests for the Use Cases)
- UI (Unit tests for the ViewModels)

IMAGE HERE

### Thales Project Packages Distribution

The architecture of the application is based, apply and strictly complies with each of the following 5 points:

<img src="arch diagram.png" width="300" align="right" hspace="20">

-   A single-activity architecture, using the [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) to manage fragment operations.
-   [Android architecture components](https://developer.android.com/topic/libraries/architecture/), part of Android Jetpack for give to project a robust design, testable and maintainable.
-   Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (MVVM) facilitating a [separation](https://en.wikipedia.org/wiki/Separation_of_concerns) of development of the graphical user interface.
-   [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) design principles intended to make software designs more understandable, flexible and maintainable.
-   [Packages architecture](https://proandroiddev.com/build-a-modular-android-app-architecture-25342d99de82) allows to be developed features in isolation, independently from other features.


IMAGE HERE

### Version Control

Thales Employee project, has been worked using git flow

- Every single branch represents a feature
- Nomenclature used -> [name]/[No.Feature]_[short_description] Ex. juansandoval/000_project_setup

### Github Action as CI

Thales Employee project is using github actions, to be able to take advantages of the continous integration automation tools.

- Using ANDROID CI the repository builds the project automatically every time a new PR is created and pointed to dev branch. 
- Using ANDROID CI the repository builds the unit tests that are in the project and evaluate if a build has been succesfully created or not depending on the result of the test passed

IMAGE HERE


The branch feature/assets_exchange_rates shows Multi-module Clean Architecture Android application project structure that consist of:

1 - Domain module, that includes Domain Data Models, UseCases (atomic app business logic that implemented as a Command pattern)
    and Repository Interfaces (that are the facade pattern implementation for data sources).
    Domain doesn't know about other modules in terms of dependency.
2 - Data module has DTO Models, all data sources (Backend REST Api, Database in this implementation 
    plus Files, Shared Prefs, Assets etc as possible sources) and Repository interfaces.
    Data knows about Domain only.
3 - Presentation (Feature) layer can consist of several separated modules to have isolated features.
    There ase Presentation and Presentation2 modules in the example just to present this approach but classes for the task are in Presentation module only.
    Presentation is implemented by View(Compose UI) + ViewModel. Also we have a facade is called Interactor that is a single class per VM and manages multi UseCases logic.
    Each Presentation module knows about Domain only.
4 - App module is for DI, Libs initialisation and for Navigation.
    App knows about all other modules

  This architecture isn't a statement, it has pros/cons. 
  The advantages are visible boundaries between layers that present spagetti code in terms of arch layers,
  features that allows to support Feature teams,
  ability to substitute/refactor implementation of data and presentation modules without rewriting the whole app,
  testable code;

  Disadvantages are a large project,
  longer onboarding,
  multimodel class class access complexity

  In terms of test task : 
  - https://openexchangerates.org/ api was used
  - Room datebase
  - Backend data stores in Db and isn't provided to the UI directly
  - After the first successful data loading we have a copy of assets for the offline mode
  - The app tries to update rates each 5 secs when Home page or Add new assets page are visible
  - The user can add a new assets from Add new assets page and remove them by swipe on Home
  - Unit and UI tests are planned but haven't implemented yet
  - Data layer Exceptions are intercepted by handleErrors() function and can be displayed to the user on the UI by Error info popup   
   
  
  

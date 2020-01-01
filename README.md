# TakeNotes

## Disclaimer
This project uses FireBase's new FireStore for cloud based storage. Some older devices in AVD might not come shipped with the correct Google dependencies. The app might not be able to connect to FireStore due to this.

We are also using an older version of Retrofit 2 because they ended support of API level 16 some time ago in their newest releases.

## Design pattern
This project *tried* to follow the MVVM design pattern. This was mainly achieved by using [Dagger 2](https://dagger.dev/) for dependency injection, [RxJava 2](https://github.com/ReactiveX/RxJava) and [RxBus](https://github.com/AndroidKnife/RxBus) for event driven functions (yay!).

## Requirements
You might require the [project Lombok](https://projectlombok.org/) [plugin](https://plugins.jetbrains.com/plugin/6317-lombok/) within your IDE for this project to work.

## Dependencies
- Dagger 2: dependency injection https://dagger.dev/
- javax inject: for dependency injection [https://docs.oracle.com/javaee/7/api/javax/inject/package-summary.html](https://docs.oracle.com/javaee/7/api/javax/inject/package-summary.html)
- RxJava 2: event driven programming https://github.com/ReactiveX/RxJava
- RxBus: RxJava event bus [https://github.com/AndroidKnife/RxBus](https://github.com/AndroidKnife/RxBus)
- Room: SQLite ORM [https://developer.android.com/topic/libraries/architecture/room](https://developer.android.com/topic/libraries/architecture/room)
- FireBase FireStore: cloud based NoSQL database [https://firebase.google.com/docs/firestore](https://firebase.google.com/docs/firestore)
- Lombok: library to automate boilerplate Java code [https://projectlombok.org/](https://projectlombok.org/)
- Various AndroidX libraries: Android support libraries [https://developer.android.com/jetpack/androidx](https://developer.android.com/jetpack/androidx)
- Google Material: material components for android! [https://material.io/develop/android/](https://material.io/develop/android/)
- Multidex: so we can support apps with over 64K methods [https://developer.android.com/studio/build/multidex](https://developer.android.com/studio/build/multidex)
- Parcler: making Android parcelables easier with annotations [https://github.com/johncarl81/parceler](https://github.com/johncarl81/parceler)
- Retrofit 2: easy HTTP APIs with Java interfaces [https://square.github.io/retrofit/](https://square.github.io/retrofit/)
- Picasso: image loader for Android with caching [https://square.github.io/picasso/](https://square.github.io/picasso/)

## Technical minimal app requirements
(This functions more as a checklist for myself.)

- 2 activities that exchange data using Intents
	- SplashscreenActivity -> MainActivity for storage provider choice
	- MainActivity -> NoteFragment to pass along a Note object
- Usage of fragments that communicate within 1 activity
	- NoteFragment/NoteListViewModel-> MainActivity for onNoteClicked handling
	OR
	- NoteDetailFragment -> NoteDetailTextCount for text size/count updating
- 'Complex' layout
	- Usage of a master detail flow layout depending on screen width & orientation
- Usage of threading to invoke web resources/and or usage of 1 service
	- Usage of FireBase FireStore (?)
	- Usage of Retrofit2 to create HTTP APIs to fetch the daily bing wallpaper for a banner background
- Presence of action bar or navigation drawer
	- Should be there
- ORM/SQLite usage
	- Used Room for local storage
- API level 16 support
	- Supports API level 16 + libraries are validate/tested to work under API level 16
- Support of hdpi, xhdpi, xxhdpi screens
	- Support should be there for the icons & launcher icon
- Support for landscape/portrait mode
	- See 'complex' layout (a certain layout is forced in landscape mode)
- Usage og JUnit
	- Created a basic JUnit unit test for Room CRUD operations
- Navigation design patterns (Android)
	- Back button/arrow is present
- Support for Dutch & Enlgish
	- Present


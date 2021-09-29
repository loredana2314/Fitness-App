# Shortcut android template

This repo serves as a starting point for many applications.  
It is here to solve common problems and implement common features that we have in most applications.


## Features:
* Simple CI with github actions
* Dependency injection
* Splash screen
* Version lock
* Onboarding
* Preferences
* Navigation architecture
* Logging and crash reporting
* Messaging / Notifications
* Analytics
* Versioning
* A starting point for using and manipulating android material design
* Release configuration (Just add a keystore and signing.properties to `keys/release`.)

This template assumes that you will use:
- Kotlin
- Data binding
- Single activity architecture with NavController

Configured with:
- Kotlin DSL gradle
- Firebase messaging
- Firebase analytics.
- Firebase Crashlytics.
- Dependency injection with Koin
- androidx Datastore Preferences
- Timber for logging
- Versioning with https://github.com/tslamic/versioning
- Several commonly used data binding libraries

The services are loosely coupled and it should be easy to swap Firebase out and use a different analytics provider, crash reporting tool etc.

## Setup

After cloning the project:
1. From android studio right click on `app/src/main/java/shortcut/androidtemplate` and refactor the package name to your own package name.
2. Create a firebase project and replace `app/google-services.json` with your own.
3. Several layouts have used `@drawable/ic_launcher_background` as a placeholder image. You probably want to replace those with your own images.
4. AFragment and BFragment exist to give you an example of navigation. Feel free to rename or delete them. You also have to update `nav_graph.xml` to reflect the changes you did.
5. `shortcut` has been used in many places, for example in naming of styles. You might want to rename the styles, as you will have to change them quite a lot to get your own design anyway. They are only there to give you an idea of how to implement your own styles.

CI tests and builds the code, then uses spotless&ktlint to check formatting etc. You can run `./gradlew spotlessApply` to automatically format the code.

It is recommended to add a precommit hook that automatically formats the code and aborts a commit if it is not correctly formatted.
You can add this to `.git/hooks/pre-commit`:
```
#!/bin/sh
set -e
STATUS="$(git status)"
./gradlew -PdisableSpotlessCheck spotlessApply
if [[ $STATUS != "$(git status)" ]]; then
  echo "$(tput setaf 1)Failed to commit, spotlessApply had changes$(tput sgr0)"
  exit 1
fi

```

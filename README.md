# SafePoster

A simple tool to make **post** and **postDelayed** safety.

## Install ![version-tag](https://img.shields.io/badge/latest%20version-0.1.0-blue)

```groovy
// root build.gradle
buildscript {
    repositories {
        mavenCentral()
    }
}
```

```groovy
// module's build.gradle
dependencies {
  implementation 'com.github.boybeak:safe-poster:latest-version'
}
```



## Usage

```kotlin
SafePoster.by(view)
    .liveWith(activity)
    .onThrow {
      // Handle error here
    }.postDelayed(4000L) {
      view.setBackgroundColor(Color.CYAN)
    }
```

or use extension function.

```kotlin
view.postSafety {
            
}

view.postDelayedSafety(3000L) {

}
```



## License

```
Copyright [2022] [boybeak]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


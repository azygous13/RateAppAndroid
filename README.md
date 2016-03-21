# RateAppAndroid
RateAppAndroid is a lightweight library to help you promote your android application by monitors and show a dialog to user to rate the app in Google Play.

![screen shot 2559-03-21 at 1 00 43 am](https://cloud.githubusercontent.com/assets/3615979/13905956/1ac0b304-ef00-11e5-912d-9acd928f7eb8.png)


## Installation
To use this library in your android project, just simply add the following dependency into your build.gradle

```
dependencies {
    compile 'com.cakii:rate-app-android:0.1.0'
}
```

## Usage
Just call RateApp.init().monitor(this) in your launcher activity's onCreate() method.

```
@Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      RateApp.init()
                .setDebug(true) // default false
                .useUntilPrompt(5) // default 10
                .dayUntilPrompt(3) // default 7
                .cancelable(true) // default false
                .monitor(this);
  }
```

The default condition to show the rating dialog is as below:

- App is launched more than 10 times. Change via `RateApp.useUntilPrompt(int)`

- App is launched more than 7 days later than installation. Change via `RateApp.dayUntilPrompt(int)`

- Above conditoin will be reset when user select 'Remind me later'

- Setting `RateApp.setDebug(boolean)` will ensure that the rating request is shown each time the app is launched. This feature is only development!.

## Custom dialog text
If you want to use your own dialog labels.

Just override default values in your `strings.xml`

```
<resources>
    <string name="rate_app_title">Rate Us</string>
    <string name="rate_app_description">If you enjoy using this app, would you mind taking a moment to rate it? It won\'t take more than a minute. Thank you for your support!</string>
    <string name="rate_app_later">REMIND ME LATER</string>
    <string name="rate_app_never">NO, THANKS</string>
    <string name="rate_app_now">RATE NOW</string>
</resources>
```

## Custom dialog style
If you want to use your own dialog style.

Just override default values in your `styles.xml`

```
<resources>

    <style name="RateAppStyle">
        <item name="android:background">@android:color/white</item>
    </style>

    <style name="RateAppStyle.TextView" parent="RateAppStyle.Widget">
        <item name="android:textColor">@android:color/black</item>
    </style>

    <style name="RateAppStyle.Button" parent="RateAppStyle.Widget">
        <item name="android:textColor">?attr/colorAccent</item>
    </style>

</resources>
```


## Support
RateAppAndroid supports API level 8 and up.


## License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

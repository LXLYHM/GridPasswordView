# GridPasswordView
[ ![Download](https://api.bintray.com/packages/jungerr/maven/gridPasswordView/images/download.svg) ](https://bintray.com/jungerr/maven/gridPasswordView/_latestVersion)

An android password view that looks like the pay password view in wechat app and alipay app.
Apache License 2.0.

## Quick Overview

 - Download [demo-0.4.apk][2]
 - Screenshots
 
### before

![demo][1]

### now

![platenumber][3]

## Getting Started

 - Add the dependency to your build.gradle.
 
```
dependencies {
    compile 'com.jungly:gridPasswordView:0.3'
}
```
or Maven:
```
<dependency>
  <groupId>com.jungly</groupId>
  <artifactId>gridPasswordView</artifactId>
  <version>0.3</version>
  <type>aar</type>
</dependency>
```
 - Add the GridPasswordView to your layout.

```xml
    <com.jungly.gridpasswordview.GridPasswordView
        android:id="@+id/pswView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" 
        
        app:gpvTextColor="#2196F3"
        app:gpvTextSize="25sp"
        app:gpvLineColor="#2196F3"
        app:gpvLineWidth="1dp"
        app:gpvGridColor="#ffffff"
        app:gpvPasswordLength="6"
        app:gpvPasswordTransformation="$"
        app:gpvPasswordType="numberPassword / textPassword / textVisiblePassword / textWebPassword"/>
        
        <com.kw.lib.ui.keyboardview.XKeyboardView
            android:id="@+id/view_keyboard"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_gravity="bottom"
            android:background="#d1d5db"
            android:focusable="true"
            android:focusableInTouchMode="true"
            android:keyBackground="@drawable/selector_key_background"
            android:keyPreviewHeight="43dp"
            android:keyPreviewLayout="@layout/key_preview_layout"
            android:keyPreviewOffset="-43dp"
            android:keyTextColor="#333333"
            android:keyTextSize="16dp"
            android:paddingBottom="5dp"
            android:paddingTop="5dp"
            android:shadowColor="#00000000"
            android:shadowRadius="0.0"
            android:visibility="gone"
            app:deleteBackground="@drawable/selector_key_backspace_background"
            app:deleteDrawable="@drawable/sym_keyboard_delete" />
```

## How to use
`getPassWord()`    get the text the PasswordView is displaying  
`clearPassword()`:  clear password in the view  
`setPassword(String password)`    just like the setText(String s) method of textview  
`togglePasswordVisibility()`  Toggle the visibility state of this view.  
`setPasswordVisibility(boolean visible)`  Set the visibility  state of this view.  
`setOnPasswordChangedListener(OnPasswordChangedListener listener)`   Register a callback to be invoked when password changed.  
`setPasswordType(PasswordType passwordType)`  one of numberPassword 、textPassword 、TEXtextVisiblePassword、textWebPassword  

## Other
If you use this library in your app, please let me know : jungly.ik@gmail.com

## Contributing

Yes:) If you found a bug, have an idea how to improve library or have a question, please create new issue or comment existing one. If you would like to contribute code fork the repository and send a pull request.

License
---

    Copyright 2015 jungly

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

  [1]: http://jungerr.qiniudn.com/gridpasswordview_0.2.gif
  [2]: https://github.com/Jungerr/GridPasswordView/blob/master/demo/demo-0.4.apk
  [3]: demo/platenumber_demo.gif

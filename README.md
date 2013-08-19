# SelectorChapek for Android

This Android Studio automatically generates drawable selectors from appropriately named Android resources.

## How to install it:

 - open 'Preferences-Plugins' in Android Studio
 - click 'Browse repositories' and search for 'SelectorChapek'

## How to use it:

1) Right-click folder with your resources e.g 'drawable-xhdpi'

 ![](https://bitbucket.org/inmite/android-autoselectors/raw/master/img/select_folder.png)

2) Select 'Generate Android Selectors'

 ![](https://bitbucket.org/inmite/android-autoselectors/raw/master/img/select_option.png)

3) All selectors _automagically_ appear in the 'drawable' folder!

 ![](https://bitbucket.org/inmite/android-autoselectors/raw/master/img/selectors_generated.png)

## Naming convention:
In order of plugin to work, resources need to be appropriately named. The plugin supports png or nine-patch files with certain suffices anywhere in the file name. You can also combine any suffixes together.

### Mapping 

| File name suffix 		| Drawable state 		|
| --------------------- | --------------------- |  
| _normal 		   		| (default state)		|
| _pressed         		| state_pressed			|
| _focused         		| state_window_focused	|
| _disabled        		| state_enabled (false) |
| _checked		   		| state_checked   		|    
| _selected		   		| state_selected  		|   
| _hovered         		| state_hovered   	   	|
| _checkable	   		| state_checkable 		|   
| _activated	   		| state_activated 		|   
| _windowfocused   		| state_window_focused 	|


### Wishlist

 - UI which will tell you which selectors will be generated
 - possibility to change naming mapping

_Pull requests are welcomed!_

### Why 'Chapek'?

<div style="float: right"><img src="http://upload.wikimedia.org/wikipedia/commons/b/bd/Karel-capek.jpg" width="30" /></div>

[Karel Čapek](http://en.wikipedia.org/wiki/Karel_%C4%8Capek) was a famous Czech writer, inventor of the word 'robot'. The word comes from Czech word 'robota', which means 'manual hard labor'. As with Čapek's robots, this plugin will remove your unnecessary manual labor. 

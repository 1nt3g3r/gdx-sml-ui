# gdx-xml-ui
Library to create 2d scenes in libgdx using xml files

# Main purpose
Provide comfortable way of creating UI using xml files in libgdx

# Features
* **Very easy usage.** Make complex UI scene in some xml file and just call XUI.inflate("your_scene_name", parentGroup).
* **Math evalutation support** It's killer-feature. It's very easy to support different screens. Just don't hardcode your actor size/position but provide them via math expressions. For example, set width of your actor to half of screen looks like this - **width="screenWidth*0.5"**.
* **No runtime needed.** Your xml layout is parsed into native scene2d widgets. So no any additonal complexity/overhead. 
* **No additional dependencies.** XUI is pretty small library without additional dependencies.
* **Clean and extendable architecture.** It's very easy to add support of your own actor type. All that your need - it's write your own XUICreator class and register it via XUI.registerXUICreator().
* **Flexibility.** You can create some basic UI elements in separate xml files. After it you can make complex UI scenes using previously created elements. Just use *linkedActor path="previously_created_xml"*
* **Good documentation.** All classes have javadocs. There is wiki that describes main aspects of usage. There is separate repository with examples.
* **Good code.** All classes are pretty small (average size is near 200 lines of code). Each core class has only one task. All utility methods are in their utility classes.

# Example code and result

``` xml
<!-- Root group. Always fill whole screen -->
<group
    width="screenWidth"
    height="screenHeight"
    debug="true">
    
    <!-- Child of root group. 
    Size of this child is half of parent size. 
    It always situated in the center of screen -->
    <group
       width="parentWidth/2"
       height="parentHeight/2"
       position="centerHorizontal | centerVertical"
       debug="true"/>
    
</group>
```

![Example actor](/docs/images/simple-xui-example.png?raw=true)

# More info
* [See WIKI] (https://github.com/1nt3g3r/gdx-xml-ui/wiki)
* [See repository with examples] (https://github.com/1nt3g3r/gdx-xml-ui-examples)
    

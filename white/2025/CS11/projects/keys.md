## Example of reading keyboard

Do this inside the while loop to change velocity when up arrow is pressed (for example)

```java
if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
    v_y = 400;
}
```

and you'll need to import this
```java
import java.awt.event.KeyEvent;
```

You can look up java KeyEvent for other key codes.
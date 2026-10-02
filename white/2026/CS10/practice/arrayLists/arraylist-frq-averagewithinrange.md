---
title: "ArrayList FRQ: ItemInventory"
layout: single
classes:
  - wide
geometry: margin=0.5in, letterpaper
header-includes:
  - \usepackage{fullpage}
---

# ArrayList FRQ: ItemInventory

The `ItemInfo` class stores information about an item at a store. A partial declaration is shown.

```java
public class ItemInfo
{
    /** Returns the name of the item */
    public String getName()
    { /* implementation not shown */ }

    /** Returns a value greater than 0.0 that represents the cost of a single unit of the item,
     * in dollars */
    public double getCost()
    { /* implementation not shown */ }

    /** Returns true if the item is currently available and returns false otherwise */
    public boolean isAvailable()
    { /* implementation not shown */ }

    /* There may be instance variables, constructors, and methods that are not shown. */
}
```

The `ItemInventory` class maintains an `ArrayList` named `inventory` that contains all items at the store. A partial declaration is shown.

```java
public class ItemInventory
{
    /** The list of all items at the store */
    private ArrayList<ItemInfo> inventory;

    /**
     * Returns the average cost of the available items whose cost is between
     * lower and upper, inclusive
     * Precondition: lower <= upper
     *               At least one available element of inventory has a cost
     *               between lower and upper, inclusive.
     *               No elements of inventory are null.
     */
    public double averageWithinRange(double lower, double upper)
    { /* to be implemented */ }

    /* There may be instance variables, constructors, and methods that are not shown. */
}
```

Write the `ItemInventory` method `averageWithinRange`. The method should return the average cost of the available items in `inventory` whose cost is between the parameters `lower` and `upper`, inclusive.

Suppose `inventory` contains the following seven `ItemInfo` objects.

| Name | action figure | hair brush | frying pan | dish sponge | coffee mug | scarf | watch |
|------|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| **Cost** | 20.0 | 7.99 | 45.0 | 2.0 | 10.0 | 59.0 | 45.0 |
| **Is Available** | true | true | true | false | true | true | false |

For the inventory shown, `averageWithinRange(10.0, 50.0)` should return `25.0`, which is equal to the average cost of the available items within the specified range (a \$20 action figure, a \$45 frying pan, and a \$10 coffee mug). Although the watch is within the specified range, it is not available.

Complete method `averageWithinRange`.

# Multiple balls etc

You can manage multiple balls by keeping an array with a visible[] flag. For example

```java
boolean[] visible = new boolean[10];
double[] x = new double[10];
double[] y = new double[10];
double[] xv = new double[10];
double[] yx = new double[10];
double[] xa = new double[10];
double[] ya = new double[10];

for (int i = 0; i < 10; i++) {
	visible[i] = false;
	x[i] = 0;
	y[i] = 0;
	xv[i] = 0;
	yv[i] = 0;
	xa[i] = 0;
	ya[i] = 0;
	// you can also define radius, color, etc for each
}
```

Now in your loop you update all the `x` and `y` positions the same way.

```java
for (int i = 0; i < 10; i++) {
	x[i] = x[i] + vx[i] * delta_t;
	y[i] = y[i] + vy[i] * delta_t;
	// also update vx and vy if there is acceleration
}
```

But when you **draw** you only draw visible ones

```java
for (int i = 0; i < 10; i++) {
	if (visible[i]) {
		StdDraw.circle(x[i], y[i], radius)
	}
}
```
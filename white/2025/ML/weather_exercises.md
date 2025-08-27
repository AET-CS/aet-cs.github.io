# Leesburg Weather Data Analysis, Exercises

## Beginner Level (Building Basic Skills)

### Exercise 1: Basic Visualization Practice
- Create a line plot showing temperature (`tmpf`) over time
- Make a histogram of wind speeds (`sknt`)
- Create a box plot comparing temperatures between weekdays and weekends
- **Bonus**: Add proper titles, axis labels, and legends to your plots

### Exercise 2: Temperature Analysis
- Find the hottest and coldest days in the dataset
- Calculate the average temperature for each day of the week
- Create a bar chart showing average temperature by day of week
- **Question**: Are weekends significantly warmer or cooler than weekdays?

### Exercise 3: Simple Filtering Practice
- Determine if any observations occur during nighttime.
- Find all observations where it was both clear (`skyc1 == 'CLR'`) AND windy (`sknt > 15`)
- Count how many observations had temperatures above 70°F. What percentage of observations is this? What percentage of days contain a temperature above 70°F?
- **Challenge**: Find the windiest clear day in the dataset

## Intermediate Level (Data Analysis Skills)

### Exercise 4: Seasonal Patterns
- Extract the month from the `valid_ET` column and analyze seasonal patterns
- Create a monthly breakdown of precipitation and cloud cover
- Plot how average temperature changes month by month
- **Research Question**: Is there a month that's particularly cloudy or clear?

### Exercise 5: Weather Condition Deep Dive
- Analyze all sky condition codes (`skyc1`, `skyc2`, `skyc3`, `skyc4`)
- Create a "cloudiness index" that combines all four sky condition layers
- Compare your cloudiness index between weekdays and weekends

### Exercise 6: Wind Analysis
- Create a scatter plot of wind speed vs temperature
- Analyze wind direction patterns (`drct`) - are certain directions more common?
- Find correlations between wind speed and precipitation

### Exercise 7: Time-Based Analysis
- Analyze hourly patterns - extract hour from `valid_ET`
- Are certain times of day more likely to be clear?
- Do weather patterns differ between morning and afternoon observations?
- **Statistical Question**: Is there a statistically significant difference?

## Advanced Level (Research Projects)

### Exercise 8: Pressure and Weather Prediction
- Analyze the relationship between atmospheric pressure (`alti`, `mslp`) and weather conditions
- Can you predict rain based on pressure changes?
- Create a "pressure trend" by calculating differences between consecutive observations
- **Research Goal**: Build a simple weather prediction model

### Exercise 9: Comprehensive Weekend Analysis
- Define your own "good weather" criteria (temperature range, clear skies, low wind, etc.)
- Calculate percentage of "good weather days" by day of week
- Perform a statistical test (chi-square) to determine if weekend differences are significant
- **Extension**: How would changing your "good weather" definition affect results?

### Exercise 10: Data Quality Investigation
- Identify missing data patterns - are certain weather parameters missing more often?
- Find potential data quality issues (impossible values, outliers)
- Create a data quality report with recommendations
- **Professional Skill**: Learn to critically evaluate data sources

### Exercise 11: Comparative Analysis
- Research weather data from a nearby location and compare patterns
- Do other airports in Virginia show the same weekend effect?
- Analyze whether this pattern holds for different seasons
- **Scientific Method**: Attempt to replicate the findings

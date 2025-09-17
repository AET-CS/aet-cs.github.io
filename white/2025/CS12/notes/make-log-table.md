# Creating a Log-Transformed Table in Google Sheets

Here's how to create a new table where every cell is the logarithm of your original data (courtesy of Claude AI)

## Step 1: Enter the Formula

**Choose your starting cell** (e.g., if original data starts at A1, start your log table at C1)

**Enter the LOG formula:**
- Click on your target cell
- Type: `=LOG(A1)` (replace A1 with your first data cell)
- Press **Enter**

**Note:** `LOG()` gives base-10 logarithm. Use `LN()` for natural log, or `LOG(A1, base)` for custom base.

## Step 2: Copy the Formula

**Method 1: Fill Handle (Drag)**
- Click the cell with your formula
- Look for the small blue square at the bottom-right corner (fill handle)
- Drag it across the row to copy horizontally
- Then select the entire row of formulas and drag down to copy vertically

**Method 2: Copy-Paste**
- Select the cell with your formula
- **Ctrl+C** (or Cmd+C on Mac) to copy
- Select the entire range where you want the log values
- **Ctrl+V** (or Cmd+V) to paste

**Method 3: Auto-fill with Double-click**
- Enter your formula in the first cell
- Click the cell to select it
- Double-click the fill handle (blue square) - this auto-fills down to match adjacent data

## Pro Tips

**For entire table at once:**
1. Select the entire destination range first
2. Type `=LOG(A1:B10)` (adjust range as needed)
3. Press **Ctrl+Shift+Enter** for array formula

**Handle errors gracefully:**
Use `=IF(A1>0, LOG(A1), "")` to avoid errors with zero/negative values

The formulas will automatically reference the correct cells as you copy them (A1 becomes A2, B1, etc.).
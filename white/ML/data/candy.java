public boolean moveCandyToFirstRow(int col) {
	if(box[0][col] != null) {
			return true;
	} else {
			for(int i = 0; i < box.length; i++) {
					for(int y = 0; y < box[0].length; y++) {
							if(y == col && box[i][y] != null) {
									Candy temp = box[i][y];
									box[0][col] = temp;
									box[i][y] = null;
									return true;
							}
					}
			}
	}
	return false;
}

public Candy removeNextByFlavor(String flavor) {
	Candy candy;
	for(int i = box.length - 1; i >= 0; i--) {
			for(int y = 0; y < box[0].length; y++) {
					if(box[i][y] != null && box[i][y].getFlavor().equals(flavor)) {
							candy = box[i][y];
							box[i][y] = null;
							return candy;
					}
			}
	}
	return null;
}
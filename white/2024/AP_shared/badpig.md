---
title: "Java No"
---


Please don't code like this

```java
public static String pig(String s)
	{
			if(s.length() == 0)
				return "";

			int len = s.length();
			char first = s.charAt(0), last = s.charAt(len - 1);
			if(".,-:;'!?/<>[]{}\\\"".indexOf(last) != -1)
				return pig(s.substring(0, len - 1)) + last;
			if(".,-:;'!?/<>[]{}\\\"".indexOf(first) != -1)
				return first + pig(s.substring(1));
			if("aeiouAEIOU".indexOf(first) != -1)
				return s + "way";
			int k = 1;
			boolean cap = (int)first >= 65 && (int)first <= 90;
			while(k < s.length() && "aeiouyAEIOUY".indexOf(s.charAt(k)) == -1)
				k++;
			if(k == s.length())
				return "**** INVALID ***** ";
			if(s.charAt(k) == 'u' && s.charAt(k-1) == 'q')
				++k;
			if(!cap)
				return s.substring(k) + s.substring(0, k) + "ay";
			else
				return s.substring(k, k+1).toUpperCase() + s.substring(k+1) + \
									s.substring(0, 1).toLowerCase() + s.substring(1, k) + "ay";
	}
```
## Dictionary Parser ##

This program parses the GNU/Linux U.S. English dictionary to produce a JSON file for use in testing phone numbers for words.
This program truncates any words:
* greater than 10 characters, 
* less than 2 characters, 
* words with accent marked characters (no phone number representation), 
* and words with periods or apostrophe's.

This program also creates a numeric representation of all the words remaining after being trunacated.

The output file is in JSON format and is formatted similar to the following:

```json

[{
"word": "abyssinian",
"number": "2297746426"
},
{
"word": "adirondack",
"number": "2347663225"
},
{
"word": "afrikaners",
"number": "2374526377"
}]
```

This program was a bit of a slapdash effort and could use to be improved in multiple ways.
* The name/location of the input and output files are hardcoded and should not be.
* The List of accented characters to search for in words is also hardcoded, I'm sure there is a more robust means to test for these.
* The majority of the body of this program is in one large try-catch block, this should be reformatted to have more meaningful use of try-catch blocks.
* There is a complete and utter dearth of comments.
* The output file technically isn't proper JSON format as there is a trailing comma on the last line of the file (I just manually removed this).
* The function to convert from word to keypad numeric format is just one massive switch-case, I feel there must be a terser way to code this.
* The body need refactoring, I'm sure I could break that out into 3 or 4 more functions at least.

As an aside, I don't see any reason off the top of my head that this program shouldn't work on different input files than the Linux U.S. English dictionary.
The program is just assuming that there will be only one word per line and that there won't be any non ASCII characters that need to be kept.

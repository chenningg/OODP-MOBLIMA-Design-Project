Each cinema file will be titled: 'cinema_XXX_YYY'
XXX is the cineplex ID and YYY is the cinema hall number appended with 0s at the front
An example is 'cinema_DTE_004' for hall 4 of Downtown East's cineplex.

In each cinema file, please follow the format below to create it in text.

==========
Format
==========
Hall number
Cinema type (PLATINUM/GOLD/STANDARD) // Note: In CAPS
Total Seats No
Seat Layout (ASCII symbols, using legends below)

==========
Legend:
==========
$ = Screen	// Just put first symbol, screen takes up whole row
- = Aisle space // Aisle space, must have at the sides, front and back
E = Exit door	// Entrance only need to put the first index, it takes up the whole row
S = Seat

==========
Example:
==========
cinema_DTE_004 <---- FILENAME

4
STANDARD
168
$
------------------
SS---SSSSSSSS---SS
SS-SSSSSSSSSSSS-SS
SS-SSSSSSSSSSSS-SS
SS-SSSSSSSSSSSS-SS
SS-SSSSSSSSSSSS-SS
SS-SSSSSSSSSSSS-SS
SS-SSSSSSSSSSSS-SS
SS-SSSSSSSSSSSS-SS
SS-SSSSSSSSSSSS-SS
SS-SSSSSSSSSSSS-SS
SS---SSSSSSSS---SS
------------------
E
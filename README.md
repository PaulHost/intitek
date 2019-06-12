Project : Android Numbers Application (Light)

Description :
The smartphone test application will be divided in two parts.
A master part that displays items from the json array available at
http://dev.tapptic.com/test/json.php .
Each item cell will contain a text (name fields from ws) and an image (image fields from ws).
Each cell takes as much width as possible. The image is on the left of the text. The content is
aligned to the left of the cell.
When a cell is focused, its background will be green with a white text.
A touched cell will have a blue background with white text (for the time of being touched).
A selected cell will have a red background with white text
Normal cells will have a transparent background with black text.
When the user clicks on a cell it will launch a second screen displaying :
A detail part containing an image and a text.
the text will display the "text" field of the json object available at
http://dev.tapptic.com/test/json.php?name=xxx where xxx is the name field of the selected item
from the list. The image will display the "image" field of the same json object.
When running on a tablet in landscape mode, the master and detail will be displayed in the
same screen (taking 50% of width each). In that case, the displayed item of the detail part will
be selected in the master part. The first item of the master part should be selected by default.
When running on a tablet in portrait mode the app will work the same as on a smartphone.
Mandatory guidelines:
Warning : failing any of those following guidelines will disqualify you.
● The component displaying the items in the master part will be a RecyclerView.
● The data access layer should be synchronous and deported to a background thread by
using Android support loaders.
● The application will support Android api 4.0+.
● The application should support any screen sizes / densities and support portrait and
landscape mode.
● Everything should be written by you : do not copy / paste library or snippet from another
developer.
General guidelines:
● Everything should be written in java code or xml resources. Prefer xml resources when
possible.
● The application should never hang or crash.
● Prefer quality over speed : although the time taken will be part of the evaluation it’s
always better to take a little more time and deliver a perfect result than rushing an half
baked solution.
● Although the application is very small, do not take shortcuts. Architecture the application
like a normal size application. The evaluation will be based on your architecture choice,
correctness, completeness, robustness and respect of the state of the art.
Bonus guidelines:
● The application will test the connectivity and ask for a retry if network is not available.
Third party libraries :
Only the following third party libraries will be tolerated :
support­v4
appcompat­v7
recyclerview­v7
picasso
okhttp
Use cases :
The following use cases WILL BE tested against your application. Failing one of those
will disqualify you.
1) Launch the application on a tablet. Turn the tablet in portrait. The master part is displayed
alone in portrait. Click on a cell. The detail screen should be displayed in portrait. Turn the tablet
in landscape. The master and detail part should be displayed with the previously selected cell
selected and displayed.
typical failure : the detail part is displayed alone in landscape
2) App will restore its state without crashing.
Connect your device to your computer and launch DDMS. Navigate in the app. For each screen,
click on the android home button. kill the application process with DDMS. Launch back the
application via the recent applications screen. The app should be restored where it was left and
with the same data displayed.
typical failure : the app crash or is not displaying the previously displayed information
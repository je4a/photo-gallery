# photo-gallery
Use placeholder api sample to create a simple album/photo browser.

We will use the API endpoints here:
https://jsonplaceholder.typicode.com/albums
https://jsonplaceholder.typicode.com/photos

Requirements
-------------------- 
Create the following viewcontrollers and flow:

1. Main viewcontroller 
- The Main viewcontroller should display a tableview of Albums.  
- Use this api as data source: https://jsonplaceholder.typicode.com/albums
- List should refresh on startup.  Call should be asynchronous
- Each cell should display the title
- On cell tap, open up the Album Photos tableview for a corresponding album id

2. Album Photos viewcontroller 
- The Album Photos viewcontroller should contain a collectionview that displays a list of photo items for a corresponding album id.  
- Use this api as a data source: https://jsonplaceholder.typicode.com/photos
- Each tableview cell should display the title and thumbnail.  
- thumbnail images should be loaded asynchronously
- on tap, open a the Image Detail viewcontroller

3. Image Detail viewcontroller
- Display the title and the full size image (in "url" field)
- The image should be scaled to fit on any device without scrolling.  The image should maintain its size ratio when scaled.  Fixed sizing should NOT be used.
- Image should be loaded asynchronously


Other Requirements
--------------------------
- All images should be loaded asynchronously
- Do not use 3rd party libraries or Cocoapods.

# PixelateX

## Introduction
Inspired by the YouTube video of coding train, in which this particular software is built using __javascript__ library `p5.js`. So I decided to give it a shot in our "beloved" java.

## Usage
To use this program download the `jar` from the releases.

```bash
java -jar PixelateX.jar <src image path>
```

* Its usage is `java -jar PixelateX.jar <options> <pixels> <path to source image>`
* Available options :
  * `-i : Disables the pop up of a 400x400 scaled version of selected image`
  * `-s : Adding this option renders and saves the ASCII art as .png image`
  * `-a : Character set 1`
  * `-b : Character set 2`
  * `-c : Character set 3`
  * `-d : Character set 4`
* Note that use **only one** of the a, b, c or d option at a time otherwise the behaviour is undefined
* ``<pixels>``  refer to the number of pixels to which the image is to be scaled

## Sources
+ [This Image is Made of Text](https://youtu.be/55iwMYv8tGI) by [The Coding Train](https://www.youtube.com/c/TheCodingTrain/)

## Sample

| input                                             | output                                            |
|---------------------------------------------------|---------------------------------------------------|
| <img src="res/sample/elizabeth.png" height="400"> | <img src="res/sample/elizabeth-ASCII.png" height="400"> |


___
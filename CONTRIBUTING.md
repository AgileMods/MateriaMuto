Contribution Guidelines
=======================
We are always happy to see that people wish to contribute to MateriaMuto. There is no such thing as a perfect project and things can always be improved. If you are a developer and are interested in helping then please do not hesitate. Just make sure you follow our guidelines.

Overall, our guidelines strongly follow those of [Google's Java Style Guidelines](https://google-styleguide.googlecode.com/svn/trunk/javaguide.html) with a few modifications.

You can also download our formatting xml files [here](https://dl.dropboxusercontent.com/u/218544836/pixelight/intellij-PLStyle.xml) and [here](https://dl.dropboxusercontent.com/u/218544836/pixelight/checkstyle_pl.xml)

## Modifications & Comments
* Line endings
  * Use Unix line endings when committing (\n)
    * Windows users of Git can do: `git config --global core.autocrlf true`
* Column width
  * 80 for Javadocs
  * 150 for code
  * Feel free to wrap when it will help with readability
* Import order
  * Use Google’s, You will have to configure this in your IDE.
    * IntelliJ IDEA: `Settings → Project Settings → Code Style → Java → Imports`
* Indentation
  * Use only four spaces for indentations, do not use two spaces
* Vertical whitespace
  * Place a blank line before the first member of a class (i.e. after `class Example {`)
  * A line after the last member of a class is encouraged but not required
  * Applies to interfaces, enums, etc. as well
* File headers
  * File headers must be surrounded in a block comment (`/* */`) and follow the style in other code files.
* Exceptions
  * For exceptions that are to be ignored, name the exception variable ignored or ignore
* Javadocs
  * Do NOT use `@author`
  * End paragraphs with `</p>`
  * Capitalize the first letter in the descriptions within each "at clause", i.e. `@param name Player to affect`

## The Gist
While we urge that you read Google's Java conventions particularly, the two are fairly long documents. To get you started quickly, here is an example of properly formatted code...

```
package com.example.java;
/* imports */
public class Example {
   private static final Logger log = Logger.getLogger(ExampleClass.class);
   private static final Random random = new Random();
   private final String id = "test";

   /**
    * Returns an identifier approximately half of the time.
    *
    * <p>A static instance of {@link Random} is used to calculate the
    * outcome with a 50% chance. If the outcome is to not return the ID,
    * the given fallback ID is returned.</p>
    *
    * @param fallback An optional fallback name to return
    * @return The ID half of the time, the given fallback the other half
    */
   @Nullable
   public String resolveId(@Nullable String fallback) {
       log.log(Priority.INFO, "ID requested");

       if (random.nextBoolean()) {
           return id;
       } else {
           return fallback;
       }
   }
}
```
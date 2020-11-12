![Maven Central](https://img.shields.io/maven-central/v/org.jresearch.gwt.time/org.jresearch.gwt.time?style=plastic)

## GWT java.util.time emulation based on ThreeTen backport project
JSR-310 provides a new date and time library for Java SE 8.
This project is the port to GWT.

For more information of the base project see the [main home page](https://www.threeten.org/threetenbp/) of the project.

This project is NOT an implementation of JSR-310, as that would require
jumping through lots of unnecessary hoops.
Instead, this is a simple adaptation of "ThreeTen" backport intended to allow users to quickly use the JSR-310 API on GWT client side.
This project should be referred to using the "gwt-time" name and the base backport should be referred to using the "ThreeTen" name.

Active development on JSR-310 is at [OpenJDK](http://openjdk.java.net/):

That repository used the BSD 3-clause license as the base project.

Issues about the adaptation should be reported here at GitHub.
Pull requests and issues will only be considered so far as matching the behavior of the real Java SE. Additional requested features will be rejected.

### Time-zone data
The time-zone database is stored as a pre-compiled dat file that is included in the built jar. The actual time-zone data is located in the base project and updated manually.

### Localization data
#### Time zone names
The impementation takes it from the browser with fallback to ZoneId from TZDB
#### Localized date/time parts (months, weekdays, eras, am/pm)
The impementation takes localized parts from the browser with falback to English.
#### Localized number formating (zero digit, positive sign, negative sign, decimal separator)
The impementation takes it from the browser with falback to `0`, `+`, `-` and `.`.
#### Date/Time formating
The impementation contains all actual data from CLDR 

### Using

* For GWT 2.8.2 add the following project dependency to pom.xml
```
<dependency>
    <groupId>org.jresearch.gwt.time</groupId>
    <artifactId>org.jresearch.gwt.time</artifactId>
    <version>1.4.12</version>
</dependency>
```
* For GWT 2.9.0 add the following  project dependency to pom.xml
```
<dependency>
    <groupId>org.jresearch.gwt.time</groupId>
    <artifactId>org.jresearch.gwt.time</artifactId>
    <version>2.0.0</version>
</dependency>
```
* Add `<inherits name="org.jresearch.threetenbp.gwt.module"/>` to your module.gwt.xml, if you use gwt-maven-plugin form Thomas Broyer (https://github.com/tbroyer/gwt-maven-plugin) it will be done automatically

### Unimplemented or partial implemented features
* Compatibility with J2CL
* Updates from Java 9-14
* Localization for IsoFields.QUARTER_OF_YEAR (hard code English quarter text)
* Chronology prints as ID (take it from browser)
* Implementation of `JapaneseChronology`/`JapaneseEra`/`JapaneseDate` in the original project based on `java.util.Calendar` and doesn't works right now.
* new `JapaneseEra.REIWA` required Java 13 to build (current is Java 8)
* java.util.Locale implementation may clash with original GWT implementation. See [dicussion on Google Groups](https://groups.google.com/forum/#!msg/Google-Web-Toolkit/D0I1-Oao_V8/k5FEBrxNBQAJ) and similar [issue](https://github.com/gwtproject/gwt/issues/9682) with gwt-commons-lang3 (the class will separate to another project)
* Convert to/from `java.util.Calendar`, to/from `java.util.TimeZone`, to/from `java.text.Format` is out of scope of this project


#### FAQs

1. What version of Java SE does this project map to?
This project currently maps to the contents of release Java SE 8u20.

2. Will the backport be kept up to date?
There are no plans for further releases.
However if security issues or bugs are found, or pull requests received then a release may occur.

3. Is this project derived from OpenJDK?
No. This project is derived from the Reference Implementation previously hosted on GitHub.
That project had a BSD license, which has been preserved here.
Thus, this project is a fork of the fork of the original code before entry to OpenJDK.

### Releases
Available in the [Maven Central repository](https://search.maven.org/search?q=a:org.jresearch.gwt.time)

### Support
GitHub [issues](https://github.com/foal/gwt-time/issues) and [pull requests](https://github.com/foal/gwt-time/pulls)
should be used when you want to help advance the project.

Note that pull requests and issues will only be considered so far as matching the behavior of Java SE releases.
Additional requested features will be rejected.

Pull requests must _not_ be copied from the JDK, because the GPL license is incompatible with the BSD license used here.


### Building from sources
* check out [APT project](https://github.com/foal/gwt-time-apt) and INSTALL (`mvn clean install`) it.
* check out this project
* `mvn clean install`
* The project use the parent pom located on Sonatype snapshot repository.
```
<repositories>
    <repository>
        <id>oss.sonatype.org-snapshot</id>
        <url>http://oss.sonatype.org/content/repositories/snapshots</url>
        <releases><enabled>false</enabled></releases>
        <snapshots><enabled>true</enabled></snapshots>
    </repository>
</repositories>
```
or download directly https://oss.sonatype.org/content/repositories/snapshots/org/jresearch/org.jresearch.pom/29-SNAPSHOT/

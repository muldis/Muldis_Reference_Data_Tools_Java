# NAME

Muldis Checkstyle: Java (MCS) - Checkstyle Configuration For Muldis Java Projects

# VERSION

The fully-qualified name of what this document describes is
`com.muldis:muldis-checkstyle:0.1` or alternately
`Muldis_Checkstyle https://muldis.com 0.1`.

# DESCRIPTION

This distribution features the Checkstyle configuration file
[src/main/resources/Muldis_Checkstyle.xml](
src/main/resources/Muldis_Checkstyle.xml) and its support structure.

This distribution empowers generating a same-versioned JAR file for
`Muldis_Checkstyle.xml` which is suitable for consumption by other Java
projects that want their source code to conform to the Checkstyle rules it
defines, and it is used by all of the other main Muldis Java projects.

See <https://checkstyle.sourceforge.io> for the canonical Checkstyle
reference documentation; we use version 8.41.1.

# USAGE

To install the Muldis Checkstyle JAR as a managed plugin external
dependency of a Java project that is managed with Maven, add Maven
Checkstyle Plugin configuration to its `pom.xml` file like the content of
the file [examples/pom-snippet.xml](examples/pom-snippet.xml).

The same Java project will also require a file named `import-control.xml`
at its root directory whose content is like the file
[examples/import-control.xml](examples/import-control.xml).

When using the example Maven Checkstyle Plugin configuration, the simplest
and fastest way to test Checkstyle conformance of the project is to run
`mvn clean validate`.  Running most other commands such as `mvn clean
install` will also do it as an early part of their regular actions.

# AUTHOR

Darren Duncan - darren@DarrenDuncan.net

# LICENSE AND COPYRIGHT

**Muldis Checkstyle: Java** (MCS) is Copyright © 2020-2021, Muldis Data Systems, Inc.

<https://muldis.com>

MCS is free software;
you can redistribute it and/or modify it under the terms of the Artistic
License version 2 (AL2) as published by the Perl Foundation
(<https://www.perlfoundation.org>).  You should have received a copy of the
AL2 as part of the MCS distribution, in the file named
[LICENSE/artistic-2_0.txt](LICENSE/artistic-2_0.txt); if not, see
<https://www.perlfoundation.org/artistic-license-20.html>.

Any versions of MCS that you modify and distribute must carry prominent
notices stating that you changed the files and the date of any changes, in
addition to preserving this original copyright notice and other credits.
MCS is distributed in the hope that it will be
useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

While it is by no means required, the copyright holder of MCS
would appreciate being informed any time you create a modified version of
MCS that you are willing to distribute, because that is a
practical way of suggesting improvements to the standard version.

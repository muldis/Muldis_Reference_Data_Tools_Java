# NAME

Muldis Object Notation Processor Reference: Java (MUONP) - Reference implementation of MUON processing utilities

# VERSION

The fully-qualified name of what this document describes is
`Muldis_Object_Notation_Processor_Reference_Java https://muldis.com 0.1`.

# DESCRIPTION

**IMPORTANT NOTICE:
The current project "Muldis Object Notation Processor Reference"
has been partially superseded by the other project "Muldis Data Library".
Any further work on the current project is blocked until
"Muldis Data Library" has been sufficiently completed, after which the
latter is intended to be a foundation for the current project.**

MUONP is a work in progress.
It is not yet ready to use for its intended purpose, nor is it yet even a
minimum viable product.
This documentation will be rewritten or updated as the project develops.

## What This Is Intended To Be

MUONP is fundamentally a library that provides a fully functional and
scalable reference implementation of a Muldis Object Notation parser and
generator that can be used as a library dependency of any other library or
application.  It is intended to handle the 2 main portable MUON formats
Plain Text and Packed Plain Text, as well as the host-native Java format.

MUONP has a multi-layered architecture so library consumers have more
choice on where to integrate with it in order to get the best performance,
as they can use more of the provided reference functionality or substitute
more of their own, such as their own value-representing types.

The architecture is optimized for memory efficiency and its lower layers
operate in a streaming manner, so it can parse or generate arbitrarily
large MUON artifacts, including those that don't fit in memory, so it is
suitable for bulk import or export with database engines, or for use with
streaming real-time work loads.

The multiple layers allow consumer choice on whether to retain or discard
meta-data involving exact input formatting that is not logically required
but can be useful.  For example when reporting to users the exact source
location and context of syntax errors or other errors, or when there is a
desire to re-serialize a file that is identical to the last source octet.

MUONP also includes a utility library and a utility application intended to
help with bulk processing of a hierarchical folder tree of MUON source
files, including bulk syntax validation, bulk structural analysis for
debuggers, bulk source linting / tidying, bulk conversion of files between
different formats, bulk import/export of data files with in-memory objects.

In particular these utilities are intended to help with creating and using
file sets when a library or application is performing *snapshot testing*.

MUONP is expressly designed to have no dependencies besides a modern
version of the core Java language and standard library,
and be a low-overhead leaf dependency for other projects.
Since Java is cross-platform, MUONP should work on any
operating system that it supports.

## What This Is Today

The above-mentioned utility library and utility application have their
scaffolding done, and are capable of doing the simple `duplicate` task that
recursively duplicates a hierarchy of nested folders and files from a
specified `in` location to a specified `out` location.  The scaffolding
implements the user interface for the application, which runs on the
command-line and is controlled with command-line arguments, and does all
file processing in a streaming manner, so should handle files or
directories of any size.

The implemented parts have robust checking of user inputs or file system
matters, provide basic on-line help documentation, and report helpfully
specific error messages.  They are designed for safety and explicitly will
block attempts to overwrite existing files or follow symbolic links or
process overlapping `in`/`out` locations.  One command-line option controls
whether a failure to handle a file means to abort everything or to instead
just skip to the next file.  Another option controls whether is verbose,
reporting a list of each file worked on, or if it is mostly silent.

## How To Use It

In order to run the MUONP utility application, you first have to compile
it, which can be done in the usual manner using JetBrains' IntelliJ IDEA,
which can be obtained here (with no-cost Community licensing an option):

<https://www.jetbrains.com/idea>

MUONP is implemented with Java 21 (2023) which is cross-platform so you can
use either the Apple MacOS or Microsoft Windows version of IntelliJ IDEA.
I have done all development and testing so far on MacOS however.

There are also a variety of command-line alternative compilers and runtimes
specific to modern Java versions, instead of needing a full IntelliJ IDEA;
for example, Amazon Corretto:

<https://aws.amazon.com/corretto>

This project includes Maven build files, so you would want to have a modern
Maven version on your system if you want to build it on the command line:

```
mvn clean install
```

Once the MUONP Java project has been compiled to a JAR, you
can run it on the command line, such as MacOS Terminal or Windows Console.

A thin wrapper UNIX shell script has been provided that can be used once
the project is compiled.

Here is an example of its use:

```
sh muonp.sh duplicate --in=corpora --out=test_files_out
```

Simply running it without arguments or with `help` will display a *Usage* message.

```
sh muonp.sh help
```

I have not yet produced an alternate thin wrapper Windows shell script,
but that is intended to be done soon.

## What Comes Next

I intend within the next few days or so to implement the actual MUONP
parser and generator functionality, and enable the utility application
tasks `analyze`, `validate`, `format` such that each one will, for each
MUON source file in `in` produce a new MUON file in `out` that represents
either a detailed parse tree, or a terse ok-yes-no, or a linted version of
the input, respectively.  And I would create a set of correct and malformed
MUON files that serves as an automated test suite for MUONP that the parser
correctly parses or reports failures etc.  And then I would update or
create other projects to consume this.

# AUTHOR

Darren Duncan - darren@DarrenDuncan.net

# LICENSE AND COPYRIGHT

**Muldis Object Notation Processor Reference: Java** (MUONP) is Copyright © 2015-2024, Muldis Data Systems, Inc.

<https://muldis.com>

MUONP is free software;
you can redistribute it and/or modify it under the terms of the Apache
License, Version 2.0 (AL2) as published by the Apache Software Foundation
(<https://www.apache.org>).  You should have received a copy of the
AL2 as part of the MUONP distribution, in the file
[LICENSE/Apache-2.0.txt](../LICENSE/Apache-2.0.txt); if not, see
<https://www.apache.org/licenses/LICENSE-2.0>.

Any versions of MUONP that you modify and distribute must carry prominent
notices stating that you changed the files and the date of any changes, in
addition to preserving this original copyright notice and other credits.
MUONP is distributed in the hope that it will be
useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

While it is by no means required, the copyright holder of MUONP
would appreciate being informed any time you create a modified version of
MUONP that you are willing to distribute, because that is a
practical way of suggesting improvements to the standard version.
